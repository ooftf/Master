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
import com.ooftf.applet.engine.text_watcher.*
import com.ooftf.applet.net.MobService
import com.ooftf.master.widget.dialog.ui.ListBlurDialog
import com.ooftf.master.widget.toolbar.official.ToolbarPlus
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.typer.TyperFactory
import com.ooftf.master.session.net.MobBaseBean
import com.trello.rxlifecycle4.kotlin.bindToLifecycle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_weekly_consumption.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * 周饭计算器
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/25 0025
 */
@Route(path = RouterPath.APPLET_ACTIVITY_WEEKLY_CONSUMPTION)
class WeeklyConsumptionActivity : BaseActivity() {
    lateinit var adapter: PersonRecordAdapter
    lateinit var orderRecord: OrderRecordBean
    lateinit var operationPanel: ListBlurDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_consumption)
        initToolbar()
        initOperationPanel()

        //获取到本地数据
        orderRecord = getData()
        //将本地数据赋值到View上
        adapter = PersonRecordAdapter(this, orderRecord)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.adapter = adapter
        monday.setText(orderRecord.monday.toString())
        tuesday.setText(orderRecord.tuesday.toString())
        wednesday.setText(orderRecord.wednesday.toString())
        thursday.setText(orderRecord.thursday.toString())
        friday.setText(orderRecord.friday.toString())
        //监听TextView
        addTextChangedListener();

        //添加人员功能
        changePerson()
    }

    fun requestSaveToServer() {
        MobService()
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
        MobService()
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
                            monday.setText(orderRecord.monday.toString())
                            tuesday.setText(orderRecord.tuesday.toString())
                            wednesday.setText(orderRecord.wednesday.toString())
                            thursday.setText(orderRecord.thursday.toString())
                            friday.setText(orderRecord.friday.toString())
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
        toolbar.title = "周饭计算器"
        toolbar.addMenuItem(ToolbarPlus
                .MenuItem(this)
                .layoutRight()
                .setImage(R.drawable.ic_more_horiz)
                .setOnClickListenerChain { operationPanel.show() })
    }

    private fun getItemName(): String {
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR).toString()
        var dateKey = if (weeks.text.isEmpty()) {
            year + instance.get(Calendar.WEEK_OF_YEAR)
        } else {
            year + weeks.text.toString()
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
        monday.setText(orderRecord.monday.toString())
        tuesday.setText(orderRecord.tuesday.toString())
        wednesday.setText(orderRecord.wednesday.toString())
        thursday.setText(orderRecord.thursday.toString())
        friday.setText(orderRecord.friday.toString())
        adapter.notifyDataSetChanged()
    }

    private fun addTextChangedListener() {
        monday.addTextChangedListener(MondayTextWatcher(orderRecord, adapter))
        tuesday.addTextChangedListener(TuesdayTextWatcher(orderRecord, adapter))
        wednesday.addTextChangedListener(WednesdayTextWatcher(orderRecord, adapter))
        thursday.addTextChangedListener(ThursdayTextWatcher(orderRecord, adapter))
        friday.addTextChangedListener(FridayTextWatcher(orderRecord, adapter))
    }

    private fun changePerson() {
        add.setOnClickListener {
            if (add_name.text.isEmpty()) {
                return@setOnClickListener
            }
            val person = PersonRecordBean()
            person.name = add_name.text.toString()
            adapter.data.persons.add(person)
            adapter.notifyDataSetChanged()
            add_name.text.clear()

        }
        remove.setOnClickListener {
            if (add_name.text.isEmpty()) {
                return@setOnClickListener
            }
            orderRecord.persons.removeAll { it.name.equals(add_name.text.toString()) }
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
