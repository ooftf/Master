package com.ooftf.widget.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.straight.StraightListView
import com.ooftf.widget.R
import com.ooftf.widget.adapter.BottomBarAdapter
import kotlinx.android.synthetic.main.activity_bottom_bar.*
@Route(path = "/widget/bottomBar")
class BottomBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_bar)
        navigation.setAdapter(BottomBarAdapter(context = this))
    }
}
