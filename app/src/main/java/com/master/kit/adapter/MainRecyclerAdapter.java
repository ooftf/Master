package com.master.kit.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.master.kit.R;
import com.master.kit.base.BaseActivity;
import com.master.kit.base.BaseRecyclerAdapter;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by master on 2017/9/25 0025.
 */

public class MainRecyclerAdapter extends BaseRecyclerAdapter<Class,MainRecyclerAdapter.RecyclerHolder> {
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
        ((Button) holder.itemView).setText(getList().get(position).getSimpleName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Activity.class.isAssignableFrom(getList().get(position))) {
                    baseActivity.startActivity(getList().get(position));
                } else if (Dialog.class.isAssignableFrom(getList().get(position))) {
                    try {
                        Dialog dialog = (Dialog) getList().get(position).getConstructor(Context.class).newInstance(baseActivity);
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

        public RecyclerHolder(View itemView) {
            super(itemView);
        }
    }
}
