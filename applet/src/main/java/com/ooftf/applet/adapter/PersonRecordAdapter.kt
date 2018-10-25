package com.ooftf.applet.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ooftf.applet.R
import com.ooftf.applet.bean.OrderRecordBean

/**
 * Created by master on 2017/8/11 0011.
 */
class PersonRecordAdapter(context:Context,data: OrderRecordBean) : RecyclerView.Adapter<PersonRecordViewHolder>() {
    var context = context
    var data = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRecordViewHolder {
        return PersonRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_person_record,parent,false))
    }

    override fun onBindViewHolder(holder: PersonRecordViewHolder, position: Int) {
            var item = data.persons[position]
            holder.name.text = item.name
            holder.name.setOnClickListener {
                AlertDialog.Builder(context)
                        .setMessage("确定删除${item.name}的数据吗")
                        .setPositiveButton("确定"){
                            p1,p2->
                            data.persons.removeAt(position)
                            notifyDataSetChanged()
                        }
                        .setNegativeButton("取消"){p1,p2->p1.dismiss()}
                        .show()

            }
            holder.monday.isChecked = item.monday
            holder.monday.text = item.getMondayMoney(data).toString()
            holder.monday.setOnClickListener {
                item.monday = holder.monday.isChecked
                notifyDataSetChanged()

            }
            holder.tuesday.isChecked = item.tuesday
            holder.tuesday.text = item.getTuesdayMoney(data).toString()
            holder.tuesday.setOnClickListener {
                item.tuesday =  holder.tuesday.isChecked
                notifyDataSetChanged()
            }
            holder.wednesday.isChecked = item.wednesday
            holder.wednesday.text = item.getWednesdayMoney(data).toString()
            holder.wednesday.setOnClickListener {
                item.wednesday = holder.wednesday.isChecked
                notifyDataSetChanged()
            }
            holder.thursday.isChecked = item.thursday
            holder.thursday.text = item.getThursdayMoney(data).toString()
            holder.thursday.setOnClickListener {
                item.thursday =  holder.thursday.isChecked
                notifyDataSetChanged()
            }
            holder.friday.isChecked = item.friday
            holder.friday.text = item.getFridayMoney(data).toString()
            holder.friday.setOnClickListener {
                item.friday =  holder.friday.isChecked
                notifyDataSetChanged()
            }
            holder.personTotal.text = item.getTotal(data).toString()

    }

    override fun getItemCount(): Int {
        return data.persons.size
    }





}