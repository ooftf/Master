package com.master.kit.widget.keyboard

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.widget.masterpopupwindow.MasterPopupWindow
import java.util.*

/**
 * Created by master on 2016/11/9.
 */

class KeyBoard(context: Context) : MasterPopupWindow(context) {
    lateinit var data: MutableList<String>

    lateinit var editText: EditText

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_key_board, null)
        setContentView(view)
        ButterKnife.bind(view)
        setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        initData()
        view.findViewById<GridView>(R.id.gridKey).adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return data.size
            }

            override fun getItem(position: Int): Any {
                return data.get(position)
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var textView: TextView = if (convertView == null) {
                    TextView(context)
                } else {
                    convertView as TextView
                }
                textView.setBackgroundResource(R.drawable.selector_key_background)
                textView.layoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 200)
                textView.gravity = Gravity.CENTER
                textView.setOnClickListener { v ->
                    val textView = (v as TextView).getText().toString()
                    if (textView == "<") {
                        if (editText.getText().length > 0) {
                            editText.getText().delete(editText.getText().length - 1, editText.getText().length)
                        }
                    } else if (textView == "确定") {
                        dismiss()
                    } else {
                        editText.getText().append(textView)
                    }
                }
                textView.setText(data.get(position))
                return textView
            }
        }
    }

    private fun initData() {
        data = ArrayList<String>()
        upsetOrder()
    }

    private fun upsetOrder() {
        val data = ArrayList<String>()
        data.add("1")
        data.add("2")
        data.add("3")
        data.add("4")
        data.add("5")
        data.add("6")
        data.add("7")
        data.add("8")
        data.add("9")
        data.add("0")
        val random = Random()
        for (i in 10 downTo 1) {
            val randomInt = random.nextInt(i)
            this.data.add(data.get(randomInt))
            data.removeAt(randomInt)
        }
        this.data.add(9, "确定")
        this.data.add(11, "<")
    }

    fun bindEditText(editText: EditText) {
        this.editText = editText
        editText.setInputType(InputType.TYPE_NULL)
        this.editText.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) = if (hasFocus) {
                showAtLocation(this@KeyBoard.editText, Gravity.BOTTOM, 0, 0)
            } else {
                dismiss()
            }
        }
        this.editText.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                showAtLocation(this@KeyBoard.editText, Gravity.BOTTOM, 0, 0)
            }
        })
    }
}
