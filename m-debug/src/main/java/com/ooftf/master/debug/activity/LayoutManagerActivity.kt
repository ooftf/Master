package com.ooftf.master.debug.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.R
import com.ooftf.master.debug.engine.layoutmanager.CustomLayoutManager
import com.ooftf.master.debug.databinding.ActivityLayoutManagerBinding

/**
 * @author 99474
 */
@Route(path = "/debug/activity/LayoutManager")
class LayoutManagerActivity : BaseViewBindingActivity<ActivityLayoutManagerBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.layoutManager = CustomLayoutManager()
        binding.recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_text, parent, false)) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            }

            override fun getItemCount(): Int {
                return 100
            }
        }
    }
}