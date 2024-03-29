package cc.wudoumi.framework.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * author: qianjujun on 2015/9/21 17.
 * email:  qianjujun@imcoming.cn
 */
public abstract class BaseTAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;
    protected LayoutInflater inflater;
    public BaseTAdapter(List<T> list,Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public T getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
