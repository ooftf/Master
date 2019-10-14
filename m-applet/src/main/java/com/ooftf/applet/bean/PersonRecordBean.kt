package com.ooftf.applet.bean

/**
 * Created by master on 2017/8/11 0011.
 */
class PersonRecordBean {
    var name:String = ""
    var monday: Boolean = false

    var tuesday: Boolean = false

    var wednesday: Boolean = false

    var thursday: Boolean = false

    var friday: Boolean = false

    fun getTotal(order: OrderRecordBean):Double{
        return getMondayMoney(order)+getTuesdayMoney(order)+getWednesdayMoney(order)+getThursdayMoney(order)+getFridayMoney(order)
    }

    fun getMondayMoney(order: OrderRecordBean):Double{
        if(!monday)return 0.0
        var selectedNumber = 0
        order.persons.forEach {
            if (it.monday) {
                selectedNumber++
            }
        }
        var perMoney = order.monday / selectedNumber
        return perMoney
    }
    fun getTuesdayMoney(order: OrderRecordBean):Double{
        if(!tuesday)return 0.0
        var selectedNumber = 0
        order.persons.forEach {
            if (it.tuesday) {
                selectedNumber++
            }
        }
        var perMoney = order.tuesday / selectedNumber
        return perMoney
    }
    fun getWednesdayMoney(order: OrderRecordBean):Double{
        if(!wednesday)return 0.0
        var selectedNumber = 0
        order.persons.forEach {
            if (it.wednesday) {
                selectedNumber++
            }
        }
        var perMoney = order.wednesday / selectedNumber
        return perMoney
    }
    fun getThursdayMoney(order: OrderRecordBean):Double{
        if(!thursday)return 0.0
        var selectedNumber = 0
        order.persons.forEach {
            if (it.thursday) {
                selectedNumber++
            }
        }
        var perMoney = order.thursday / selectedNumber
        return perMoney
    }
    fun getFridayMoney(order: OrderRecordBean):Double{
        if(!friday)return 0.0
        var selectedNumber = 0
        order.persons.forEach {
            if (it.friday) {
                selectedNumber++
            }
        }
        var perMoney = order.friday / selectedNumber
        return perMoney
    }

}