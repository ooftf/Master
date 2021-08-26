package com.ooftf.master.other.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.databinding.ActivityStackGetMinBinding
import java.util.*

/**
 * 获取到栈的最小值
 */
@Route(path = "/other/activity/stackGetMin")
class StackGetMinActivity : BaseViewBindingActivity<ActivityStackGetMinBinding>() {
    private val stack = Stack<Int>()
    private var stackMin = Stack<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.pushButton.setOnClickListener {
            push(binding.pushEdit.text.toString().toInt())
            print()
        }
        binding.popButton.setOnClickListener {
            binding.popText.text = pop().toString()
            print()
        }
    }

    private fun print() {
        binding.printMainStack.text = "主堆栈：$stack"
        binding.printMinStack.text = "最小值堆栈：$stackMin"
        binding.printMin.text = "最小值：${getMin()}"
    }

    private fun push(i: Int) {
        if (stackMin.empty()) {
            stackMin.push(i)
        } else if (i <= stackMin.peek()) {
            stackMin.push(i)
        }
        stack.push(i)
    }

    private fun pop(): Int {
        if (stack.empty()) return 0
        val pop = stack.pop()
        if (pop == stackMin.peek()) {
            stackMin.pop()
        }
        return pop
    }

    fun getMin(): Int = if (stackMin.empty()) {
        0
    } else {
        stackMin.peek()
    }
}
