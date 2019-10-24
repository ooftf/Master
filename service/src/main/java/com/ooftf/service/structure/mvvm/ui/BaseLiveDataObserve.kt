package com.ooftf.service.structure.mvvm.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ooftf.service.utils.extend.toast
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import retrofit2.Call

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/13 0013
 */
class BaseLiveDataObserve(private var liveData: BaseLiveData, private var owner: LifecycleOwner, activity: Activity) {
    constructor(liveData: BaseLiveData, activity: AppCompatActivity) : this(liveData, activity, activity)
    constructor(liveData: BaseLiveData, fragment: Fragment) : this(liveData, fragment, fragment.activity!!)

    private val loadingDialog by lazy {
        var dialog = ProgressDialog(activity);
        dialog.setOnCancelListener {
            (dialog.window.decorView.tag)?.let {
                (it as List<Call<Any>>).forEach { item ->
                    item?.cancel()
                }
            }
        }
        dialog
    }
    private val smarts = ArrayList<SmartRefreshLayout>()

    init {
        liveData.finishLiveData.observe(owner, Observer { integer ->
            activity.setResult(integer)
            activity.finish()
        })
        liveData.showLoading.observe(owner, Observer { calls ->
            loadingDialog.window?.decorView?.tag = calls
            if (calls.size > 0) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
        liveData.startActivityLiveData.observe(owner, Observer { postcard -> postcard.navigation(activity) })
        liveData.messageLiveData.observe(owner, Observer<String> { toast(it) })


        liveData.finishWithData.observe(owner, Observer {
            var intent = Intent()
            intent.putExtra("data", it.data)
            activity.setResult(it.code, intent)
            activity.finish()
        })
        setSmartObserve()
    }

    fun bindSmartRefreshLayout(smartRefreshLayout: SmartRefreshLayout): BaseLiveDataObserve {
        smarts.add(smartRefreshLayout)
        return this
    }

    private fun setSmartObserve() {
        liveData.smartRefresh.observe(owner, Observer { integer ->
            if (integer == 0) {
                smarts.forEach {
                    it.finishRefresh()
                    it.resetNoMoreData()
                }
            }
        })

        liveData.smartLoadMore.observe(owner, Observer { integer ->
            if (integer == UIEvent.SMART_LAYOUT_LOADMORE_FINISH) {
                smarts.forEach {
                    it.finishLoadMore()
                }
            } else if (integer == UIEvent.SMART_LAYOUT_LOADMORE_FINISH_AND_NO_MORE) {
                smarts.forEach {
                    it.finishLoadMoreWithNoMoreData()
                }
            }
        })
    }


}
