package com.ooftf.master.debug.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import butterknife.ButterKnife
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.widget.KeyBoard
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.content_key_board.*

@Route(path = "/debug/keyBoard")
class KeyBoardActivity : BaseActivity() {

    lateinit var keyBoard: KeyBoard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_board)
        ButterKnife.bind(this)
        keyBoard = KeyBoard(this@KeyBoardActivity)
        keyBoard.bindEditText(editTest)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
