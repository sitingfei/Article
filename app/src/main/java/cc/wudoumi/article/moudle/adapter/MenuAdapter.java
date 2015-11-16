package cc.wudoumi.article.moudle.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.framework.base.BaseTAdapter;

/**
 * author: qianjujun on 2015/11/12 14.
 * email:  qianjujun@imcoming.cn
 */
public class MenuAdapter extends BaseTAdapter<ArticleType>{
    private int currentPosition = 0;

    public MenuAdapter(List<ArticleType> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_leftmenu,null);
            textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(textView);
        }else {
            textView = (TextView) convertView.getTag();
        }

        ArticleType articleType = getItem(position);
        textView.setText(articleType.getName());

        if(currentPosition==position){
            textView.setBackgroundColor(Color.parseColor("#D0000000"));
        }else{
            textView.setBackgroundColor(Color.parseColor("#60000000"));
        }
        return convertView;
    }


    public void notifyDataSetChanged(int currentPosition) {
        this.currentPosition = currentPosition;
        super.notifyDataSetChanged();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }


    public int getCurrentParentId(){
       return getItem(currentPosition).getId();
    }
}
