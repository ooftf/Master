package com.ooftf.applet.bean

class JXBean(var basic: Int, var damage: Int, var other: MutableList<Int>) {
    fun getBasicIncome() = basic * 2.625
    fun getDamageIncome() = damage * 1.75
    fun getOtherIncome(): Double {
        var result = 0
        other.forEach { result += it }
        return result * 1.4
    }

    fun getTotalIncome() = getBasicIncome() + getDamageIncome() + getOtherIncome()
}