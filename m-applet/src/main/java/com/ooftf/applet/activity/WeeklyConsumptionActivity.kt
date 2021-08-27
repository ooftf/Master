package com.ooftf.applet.activity

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.applet.adapter.PersonRecordAdapter
import com.ooftf.applet.bean.OrderRecordBean
import com.ooftf.applet.bean.PersonRecordBean
import com.ooftf.applet.databinding.ActivityWeeklyConsumptionBinding
import com.ooftf.applet.engine.text_watcher.*
import com.ooftf.applet.net.MobService
import com.ooftf.master.widget.dialog.ui.ListBlurDialog
import com.ooftf.master.widget.toolbar.official.ToolbarPlus
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.typer.TyperFactory
import com.ooftf.master.session.net.MobBaseBean
import com.trello.rxlifecycle4.kotlin.bindToLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * 周饭计算器
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/25 0025
 */
@Route(path = RouterPath.APPLET_ACTIVITY_WEEKLY_CONSUMPTION)
@AndroidEntryPoint
class WeeklyConsumptionActivity : BaseViewBindingActivity<ActivityWeeklyConsumptionBinding>() {
    @Inject
    lateinit var mobApi:MobService
    lateinit var adapter: PersonRecordAdapter
    lateinit var orderRecord: OrderRecordBean
    lateinit var operationPanel: ListBlurDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initOperationPanel()

        //获取到本地数据
        orderRecord = getData()
        //将本地数据赋值到View上
        adapter = PersonRecordAdapter(this, orderRecord)
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.monday.setText(orderRecord.monday.toString())
        binding.tuesday.setText(orderRecord.tuesday.toString())
        binding.wednesday.setText(orderRecord.wednesday.toString())
        binding.thursday.setText(orderRecord.thursday.toString())
        binding.friday.setText(orderRecord.friday.toString())
        //监听TextView
        addTextChangedListener();

        //添加人员功能
        changePerson()
    }

    fun requestSaveToServer() {
        mobApi
                .put(getItemName(), Base64.encodeToString(Gson().toJson(orderRecord).toByteArray(), Base64.DEFAULT))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(DialogAction<MobBaseBean>(this))
                //.compose(ErrorAction<MobBaseBean>(this))
                .bindToLifecycle(window.decorView)
                .subscribe(object : com.ooftf.applet.net.action.MobObserver<MobBaseBean>() {
                    override fun onSuccess(bean: MobBaseBean?) {
                        Toast.makeText(this@WeeklyConsumptionActivity, bean?.msg, Toast.LENGTH_LONG).show()
                    }
                })
    }

    fun requestFromServer() {
        mobApi
                .query(getItemName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(window.decorView)
                //.compose(DialogAction<ItemDataBean>(this))
                //.compose(ErrorAction<ItemDataBean>(this))
                .subscribe(object : com.ooftf.applet.net.action.MobObserver<com.ooftf.applet.net.bean.ItemDataBean>() {
                    override fun onSuccess(bean: com.ooftf.applet.net.bean.ItemDataBean) {
                        Log.e("onNext", "。。。。。。。。。。。" + System.currentTimeMillis())
                        bean.result.let {
                            orderRecord = Gson().fromJson(String(Base64.decode(it, Base64.DEFAULT)), OrderRecordBean::class.java)
                            adapter.data = orderRecord
                            binding.monday.setText(orderRecord.monday.toString())
                            binding.tuesday.setText(orderRecord.tuesday.toString())
                            binding.wednesday.setText(orderRecord.wednesday.toString())
                            binding.thursday.setText(orderRecord.thursday.toString())
                            binding.friday.setText(orderRecord.friday.toString())
                            adapter.notifyDataSetChanged()
                        }
                    }
                })
    }

    private fun initOperationPanel() {
        operationPanel = ListBlurDialog(this)
        var data = ArrayList<String>()
        data.add("从服务器拉取数据")
        data.add("保存数据到服务器")
        data.add("全选")
        data.add("清空")
        operationPanel.setOnItemClickListener { dialog, position, item ->

            when (position) {
                0 -> {
                    requestFromServer()
                    operationPanel.dismiss()
                }
                1 -> {
                    requestSaveToServer()
                    operationPanel.dismiss()
                }
                2 -> {
                    selectAll()
                    operationPanel.dismiss()
                }
                3 -> {
                    operationPanel.dismiss()
                    AlertDialog.Builder(this@WeeklyConsumptionActivity)
                            .setMessage("确定清空？")
                            .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
                            .setPositiveButton("确定") { _, _ -> clearData() }
                            .show()
                }

            }

        }
        operationPanel.setList(data)
    }

    private fun initToolbar() {
        binding.toolbar.title = "周饭计算器"
        binding.toolbar.addMenuItem(ToolbarPlus
                .MenuItem(this)
                .layoutRight()
                .setImage(R.drawable.ic_more_horiz)
                .setOnClickListenerChain { operationPanel.show() })
    }

    private fun getItemName(): String {
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR).toString()
        var dateKey = if (binding.weeks.text.isEmpty()) {
            year + instance.get(Calendar.WEEK_OF_YEAR)
        } else {
            year + binding.weeks.text.toString()
        }
        return "WeeklyConsumption_$dateKey"
    }

    private fun selectAll() {
        orderRecord.persons.forEach {
            it.monday = true
            it.tuesday = true
            it.wednesday = true
            it.thursday = true
            it.friday = true
        }
        adapter.notifyDataSetChanged()
    }

    private fun clearData() {
        orderRecord.persons.forEach {
            it.monday = false
            it.tuesday = false
            it.wednesday = false
            it.thursday = false
            it.friday = false
        }
        orderRecord.monday = 0.0
        orderRecord.tuesday = 0.0
        orderRecord.wednesday = 0.0
        orderRecord.thursday = 0.0
        orderRecord.monday = 0.0
        orderRecord.friday = 0.0
        binding.monday.setText(orderRecord.monday.toString())
        binding.tuesday.setText(orderRecord.tuesday.toString())
        binding.wednesday.setText(orderRecord.wednesday.toString())
        binding.thursday.setText(orderRecord.thursday.toString())
        binding.friday.setText(orderRecord.friday.toString())
        adapter.notifyDataSetChanged()
    }

    private fun addTextChangedListener() {
        binding.monday.addTextChangedListener(MondayTextWatcher(orderRecord, adapter))
        binding.tuesday.addTextChangedListener(TuesdayTextWatcher(orderRecord, adapter))
        binding.wednesday.addTextChangedListener(WednesdayTextWatcher(orderRecord, adapter))
        binding.thursday.addTextChangedListener(ThursdayTextWatcher(orderRecord, adapter))
        binding.friday.addTextChangedListener(FridayTextWatcher(orderRecord, adapter))
    }

    private fun changePerson() {
        binding.add.setOnClickListener {
            if (binding.addName.text.isEmpty()) {
                return@setOnClickListener
            }
            val person = PersonRecordBean()
            person.name = binding.addName.text.toString()
            adapter.data.persons.add(person)
            adapter.notifyDataSetChanged()
            binding.addName.text.clear()

        }
        binding.remove.setOnClickListener {
            if (binding.addName.text.isEmpty()) {
                return@setOnClickListener
            }
            orderRecord.persons.removeAll { it.name.equals(binding.addName.text.toString()) }
            adapter.notifyDataSetChanged()
        }
    }

    private fun getData(): OrderRecordBean {
        val json = TyperFactory.getDefault().getString(SP_KEY_DATA, null)
        if (json != null) {
            var gson = Gson()
            return gson.fromJson(json, OrderRecordBean::class.java)
        }
        return OrderRecordBean()
    }

    override fun onPause() {
        TyperFactory.getDefault().put(SP_KEY_DATA, Gson().toJson(orderRecord))
        super.onPause()
    }

    companion object {
        val SP_KEY_DATA = "sp_key_data"
    }
}
