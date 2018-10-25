package com.ooftf.applet.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.applet.adapter.PersonRecordAdapter
import com.ooftf.applet.bean.OrderRecordBean
import com.ooftf.applet.bean.PersonRecordBean
import com.ooftf.applet.engine.text_watcher.*
import com.ooftf.hihttp.action.DialogAction
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.typer.TyperFactory
import com.ooftf.service.net.ServiceHolder
import com.ooftf.service.net.mob.action.ErrorAction
import com.ooftf.service.net.mob.action.MobObserver
import com.ooftf.service.net.mob.bean.ItemDataBean
import com.ooftf.service.net.mob.bean.MobBaseBean
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_weekly_consumption.*
import java.util.*

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_consumption)
        //获取到本地数据
        orderRecord = getData()
        //将本地数据赋值到View上
        adapter = PersonRecordAdapter(this, orderRecord)
        recyclerView.layoutManager = LinearLayoutManager(this)
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
        //全选
        all.setOnClickListener {
            selectAll()
        }
        clear.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("确定清空？")
                    .setNegativeButton("取消") { p0, p1 -> p0.dismiss() }
                    .setPositiveButton("确定") { p0, p1 -> clearData() }
                    .show()
        }
        save_to_server.setOnClickListener {

            ServiceHolder
                    .mobService
                    .put(getItemName(), Base64.encodeToString(Gson().toJson(orderRecord).toByteArray(),Base64.DEFAULT))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(DialogAction<MobBaseBean>(this))
                    .compose(ErrorAction<MobBaseBean>(this))
                    .bindToLifecycle(save_to_server)
                    .subscribe(object : MobObserver<MobBaseBean>() {
                        override fun onSuccess(bean: MobBaseBean?) {
                            Toast.makeText(this@WeeklyConsumptionActivity, bean?.msg, Toast.LENGTH_LONG).show()
                        }
                    })

        }
        query_from_server.setOnClickListener {
            ServiceHolder
                    .mobService
                    .query(getItemName())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .bindToLifecycle(query_from_server)
                    .compose(DialogAction<ItemDataBean>(this))
                    .compose(ErrorAction<ItemDataBean>(this))
                    .subscribe(object : MobObserver<ItemDataBean>() {
                        override fun onSuccess(bean: ItemDataBean) {
                            Log.e("onNext", "。。。。。。。。。。。" + System.currentTimeMillis())
                            bean.result.let {
                                orderRecord = Gson().fromJson(String(Base64.decode(it,Base64.DEFAULT)), OrderRecordBean::class.java)
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
