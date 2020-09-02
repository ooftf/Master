package com.ooftf.master.debug.activity

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.engine.layoutmanager.CustomLayoutManager
import com.ooftf.log.JLog
import kotlinx.android.synthetic.main.activity_layout_manager.*

/**
 * @author 99474
 */
@Route(path = "/debug/activity/LayoutManager")
class LayoutManagerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_manager)
        JLog.e(this, window.decorView.toString())
        recycler_view.layoutManager = CustomLayoutManager()
        recycler_view.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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