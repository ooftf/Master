package com.master.kit.testcase

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.widget.keyboard.KeyBoard
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.content_key_board.*

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
