package com.master.kit.testcase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.crash.FirebaseCrash;
import com.master.kit.testcase.banner.BannerActivity;
import com.master.kit.R;
import com.master.kit.base.BaseActivity;
import com.master.kit.testcase.annotation.AnnotationActivity;
import com.master.kit.testcase.cpb.CPBActivity;
import com.master.kit.testcase.design.DesignDispatchActivity;
import com.master.kit.testcase.filedownload.DownloadActivity;
import com.master.kit.testcase.listview.ListViewActivity;
import com.master.kit.testcase.net.NetActivity;
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity;
import com.master.kit.testcase.touchevent.TouchActivity;
import com.master.kit.testcase.viewpager.ViewPagerActivity;
import com.master.kit.testcase.webview.WebViewActivity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDatas();
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = inflater.inflate(R.layout.item_recyclerview_main,parent,false);
                RecyclerView.ViewHolder holder = new RecyclerHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ((Button)holder.itemView).setText(list.get(position).getSimpleName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Activity.class.isAssignableFrom(list.get(position))){
                            startActivity(list.get(position));
                        }else if(Dialog.class.isAssignableFrom(list.get(position))){
                            try {
                                Dialog dialog = (Dialog) list.get(position).getConstructor(Context.class).newInstance(MainActivity.this);
                                dialog.show();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
            }
            @Override
            public int getItemCount() {
                return list.size();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    List<Class> list;
    private void initDatas() {
        list = new ArrayList();
        list.add(CalendarActivity.class);
        list.add(PageLayoutActivity.class);
        list.add(GesturePasswordActivity.class);
        list.add(TouchActivity.class);
        list.add(ViewPagerActivity.class);
        list.add(DesignDispatchActivity.class);
        list.add(ListViewActivity.class);
        list.add(SoftKeyboardActivity.class);
        list.add(CPBActivity.class);
        list.add(WebViewActivity.class);
        list.add(CameraActivity.class);
        list.add(NetActivity.class);
        list.add(AlbumActivity.class);
        list.add(NewInstanceActivity.class);
        list.add(DialogDemo.class);
        list.add(PullToRefreshActivity.class);
        list.add(KeyBoardActivity.class);
        list.add(AnnotationActivity.class);
        list.add(DownloadActivity.class);
        list.add(BannerActivity.class);

    }

    class RecyclerHolder extends RecyclerView.ViewHolder{

        public RecyclerHolder(View itemView) {
            super(itemView);
        }
    }
}
