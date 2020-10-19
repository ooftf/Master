package com.ooftf.master.debug.activity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.widget.KeyBoard
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import kotlinx.android.synthetic.main.content_key_board.*

@Route(path = "/debug/activity/keyBoard")
class KeyBoardActivity : BaseActivity() {

    lateinit var keyBoard: KeyBoard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_board)
        keyBoard = KeyBoard(this@KeyBoardActivity)
        keyBoard.bindEditText(editTest)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
