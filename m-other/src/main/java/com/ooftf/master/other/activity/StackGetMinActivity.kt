package com.ooftf.master.other.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_stack_get_min.*
import java.util.*

/**
 * 获取到栈的最小值
 */
@Route(path = "/other/activity/stackGetMin")
class StackGetMinActivity : BaseActivity() {
    private val stack = Stack<Int>()
    private var stackMin = Stack<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_get_min)
        pushButton.setOnClickListener {
            push(pushEdit.text.toString().toInt())
            print()
        }
        popButton.setOnClickListener {
            popText.text = pop().toString()
            print()
        }
    }

    private fun print() {
        printMainStack.text = "主堆栈：$stack"
        printMinStack.text = "最小值堆栈：$stackMin"
        printMin.text = "最小值：${getMin()}"
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
