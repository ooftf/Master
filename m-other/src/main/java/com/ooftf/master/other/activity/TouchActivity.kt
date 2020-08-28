package com.ooftf.master.other.activity

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.ooftf.master.other.R
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.constant.RouterPath
import kotlinx.android.synthetic.main.activity_touch.*
import kotlinx.android.synthetic.main.app_bar_touch.*
import kotlinx.android.synthetic.main.content_touch.*
import kotlinx.android.synthetic.main.content_touch_navigation.*

@Route(path = RouterPath.OTHER_ACTIVITY_TOUCH)
class TouchActivity : BaseSlidingActivity(), CompoundButton.OnCheckedChangeListener {
    var third_isModify: Switch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)
        fab!!.setOnClickListener { view: View? ->
            Snackbar.make(view!!, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout!!.addDrawerListener(toggle)
        toggle.syncState()
        initClick()
    }

    private fun initClick() {
        first_isModify!!.setOnCheckedChangeListener(this)
        sb_first_dispatchTouchEvent!!.setOnCheckedChangeListener(this)
        sb_first_onInterceptTouchEvent!!.setOnCheckedChangeListener(this)
        sb_first_onTouchEvent!!.setOnCheckedChangeListener(this)
        second_isModify!!.setOnCheckedChangeListener(this)
        sb_second_dispatchTouchEvent!!.setOnCheckedChangeListener(this)
        sb_second_onInterceptTouchEvent!!.setOnCheckedChangeListener(this)
        sb_second_onTouchEvent!!.setOnCheckedChangeListener(this)
        third_isModify!!.setOnCheckedChangeListener(this)
        sb_third_dispatchTouchEvent!!.setOnCheckedChangeListener(this)
        sb_third_onInterceptTouchEvent!!.setOnCheckedChangeListener(this)
        sb_third_onTouchEvent!!.setOnCheckedChangeListener(this)
        //同步
        first_isModify!!.isChecked = false
        second_isModify!!.isChecked = false
        third_isModify!!.isChecked = false
        sb_first_dispatchTouchEvent!!.isChecked = false
        sb_first_onInterceptTouchEvent!!.isChecked = false
        sb_first_onTouchEvent!!.isChecked = false
        sb_second_dispatchTouchEvent!!.isChecked = false
        sb_second_onInterceptTouchEvent!!.isChecked = false
        sb_second_onTouchEvent!!.isChecked = false
        sb_third_dispatchTouchEvent!!.isChecked = false
        sb_third_onInterceptTouchEvent!!.isChecked = false
        sb_third_onTouchEvent!!.isChecked = false
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        val i = buttonView.id
        if (i == R.id.sb_first_dispatchTouchEvent) {
            first!!.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_first_onInterceptTouchEvent) {
            first!!.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_first_onTouchEvent) {
            first!!.onTouchEvent = isChecked
        } else if (i == R.id.sb_second_dispatchTouchEvent) {
            second!!.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_second_onInterceptTouchEvent) {
            second!!.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_second_onTouchEvent) {
            second!!.onTouchEvent = isChecked
        } else if (i == R.id.sb_third_dispatchTouchEvent) {
            third!!.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_third_onInterceptTouchEvent) {
            third!!.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_third_onTouchEvent) {
            third!!.onTouchEvent = isChecked
        } else if (i == R.id.first_isModify) {
            first!!.isModifyResult = isChecked
        } else if (i == R.id.second_isModify) {
            second!!.isModifyResult = isChecked
        } else if (i == R.id.third_isModify) {
            third!!.isModifyResult = isChecked
        }
    }
}