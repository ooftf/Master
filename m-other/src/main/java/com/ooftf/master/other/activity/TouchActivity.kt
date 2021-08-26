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
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.R
import com.ooftf.master.other.databinding.ActivityTouchBinding
import com.ooftf.master.other.databinding.AppBarTouchBinding
import com.ooftf.master.other.databinding.ContentTouchBinding
import com.ooftf.master.other.databinding.ContentTouchNavigationBinding
import com.ooftf.service.constant.RouterPath

@Route(path = RouterPath.OTHER_ACTIVITY_TOUCH)
class TouchActivity : BaseViewBindingActivity<ActivityTouchBinding>(), CompoundButton.OnCheckedChangeListener {
    lateinit var appBarBinding : AppBarTouchBinding
    lateinit var contentTouchBinding : ContentTouchBinding
    lateinit var contentTouchNavigationBinding : ContentTouchNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBarBinding = AppBarTouchBinding.bind(binding.root)
        contentTouchBinding = ContentTouchBinding.bind(binding.root)
        contentTouchNavigationBinding = ContentTouchNavigationBinding.bind(binding.root)
        appBarBinding.fab.setOnClickListener { view: View ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, appBarBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        initClick()
    }

    private fun initClick() {
        contentTouchNavigationBinding.firstIsModify.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbFirstDispatchTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbFirstOnInterceptTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbFirstOnTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.secondIsModify.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbSecondDispatchTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbSecondOnInterceptTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbSecondOnTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.thirdIsModify.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbThirdDispatchTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbThirdOnInterceptTouchEvent.setOnCheckedChangeListener(this)
        contentTouchNavigationBinding.sbThirdOnTouchEvent.setOnCheckedChangeListener(this)
        //同步
        contentTouchNavigationBinding.firstIsModify.isChecked = false
        contentTouchNavigationBinding.secondIsModify.isChecked = false
        contentTouchNavigationBinding.thirdIsModify.isChecked = false
        contentTouchNavigationBinding.sbFirstDispatchTouchEvent.isChecked = false
        contentTouchNavigationBinding.sbFirstOnInterceptTouchEvent.isChecked = false
        contentTouchNavigationBinding.sbFirstOnTouchEvent.isChecked = false
        contentTouchNavigationBinding.sbSecondDispatchTouchEvent.isChecked = false
        contentTouchNavigationBinding. sbSecondOnInterceptTouchEvent.isChecked = false
        contentTouchNavigationBinding. sbSecondOnTouchEvent.isChecked = false
        contentTouchNavigationBinding. sbThirdDispatchTouchEvent.isChecked = false
        contentTouchNavigationBinding. sbThirdOnInterceptTouchEvent.isChecked = false
        contentTouchNavigationBinding.sbThirdOnTouchEvent.isChecked = false
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
            contentTouchBinding.first.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_first_onInterceptTouchEvent) {
            contentTouchBinding.first.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_first_onTouchEvent) {
            contentTouchBinding.first.onTouchEvent = isChecked
        } else if (i == R.id.sb_second_dispatchTouchEvent) {
            contentTouchBinding.second.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_second_onInterceptTouchEvent) {
            contentTouchBinding.second.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_second_onTouchEvent) {
            contentTouchBinding.second.onTouchEvent = isChecked
        } else if (i == R.id.sb_third_dispatchTouchEvent) {
            contentTouchBinding.third.dispatchTouchEvent = isChecked
        } else if (i == R.id.sb_third_onInterceptTouchEvent) {
            contentTouchBinding.third.onInterceptTouchEvent = isChecked
        } else if (i == R.id.sb_third_onTouchEvent) {
            contentTouchBinding.third.onTouchEvent = isChecked
        } else if (i == R.id.first_isModify) {
            contentTouchBinding.first.isModifyResult = isChecked
        } else if (i == R.id.second_isModify) {
            contentTouchBinding.second.isModifyResult = isChecked
        } else if (i == R.id.third_isModify) {
            contentTouchBinding.third.isModifyResult = isChecked
        }
    }
}