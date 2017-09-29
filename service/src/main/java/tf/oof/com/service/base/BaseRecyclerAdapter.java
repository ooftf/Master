package tf.oof.com.service.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 2017/9/25 0025.
 */

abstract public class BaseRecyclerAdapter<T,WH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<WH> {
    List<T> list = new ArrayList<>();

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public void addAll(List<T> list) {
        this.list.addAll(list);
    }
    public void add(T item) {
        this.list.add(item);
    }

    public void clear() {
        list.clear();
    }

    public List<T> getList() {
        return list;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
