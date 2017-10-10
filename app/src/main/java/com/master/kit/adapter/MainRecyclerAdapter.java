package com.master.kit.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.master.kit.R;
import com.master.kit.bean.ScreenItemBean;

import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseSlidingActivity;
import tf.oof.com.service.base.BaseRecyclerAdapter;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by master on 2017/9/25 0025.
 */

public class MainRecyclerAdapter extends BaseRecyclerAdapter<ScreenItemBean,MainRecyclerAdapter.RecyclerHolder> {
    LayoutInflater inflater;
    BaseActivity baseActivity;
    public MainRecyclerAdapter(BaseActivity context){
        this.baseActivity = context;
        inflater = LayoutInflater.from(context);
    }
    //LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview_main, parent, false);
        RecyclerHolder holder = new RecyclerHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        final ScreenItemBean bean = getItem(position);
        holder.describe.setText(bean.getDescribe());
        holder.name.setText(bean.getName()+"("+bean.getClz().getSimpleName()+")");
        holder.icon.setImageResource(bean.getIcon());
        if(bean.isIssue()){
            holder.issue.setVisibility(View.VISIBLE);
        }else{
            holder.issue.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Activity.class.isAssignableFrom(bean.getClz())) {
                    baseActivity.startActivity(bean.getClz());
                } else if (Dialog.class.isAssignableFrom(bean.getClz())) {
                    try {
                        Dialog dialog = (Dialog) bean.getClz().getConstructor(Context.class).newInstance(baseActivity);
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
    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView describe;
        public ImageView icon;
        public ImageView issue;
        public RecyclerHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            describe = itemView.findViewById(R.id.describe);
            icon = itemView.findViewById(R.id.icon);
            issue = itemView.findViewById(R.id.issue);
        }
    }
}
