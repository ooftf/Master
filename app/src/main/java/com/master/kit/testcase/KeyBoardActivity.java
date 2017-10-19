package com.master.kit.testcase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.master.kit.R;
import com.master.kit.widget.keyboard.KeyBoard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyBoardActivity extends AppCompatActivity {

    @BindView(R.id.edit_test)
    EditText editTest;
    KeyBoard keyBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        ButterKnife.bind(this);
        keyBoard = new KeyBoard(KeyBoardActivity.this);
        keyBoard.bindEditText(editTest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }

}
