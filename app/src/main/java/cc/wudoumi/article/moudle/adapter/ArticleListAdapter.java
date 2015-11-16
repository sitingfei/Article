package cc.wudoumi.article.moudle.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.Article;
import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.framework.base.BaseTAdapter;

/**
 * author: qianjujun on 2015/11/12 17.
 * email:  qianjujun@imcoming.cn
 */
public class ArticleListAdapter extends BaseTAdapter<Article>{
    private CommonCache commonCache;
    private int color;
    public ArticleListAdapter(List<Article> list, Context context) {
        super(list, context);
        commonCache = CommonCache.get();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        Holder mHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_article_,null);
            mHolder = new Holder(convertView);
        }else{
            mHolder = (Holder) convertView.getTag();
        }

        mHolder.bindData(article);
        return convertView;
    }

    private class Holder{
        TextView tvTitle,tvAuthor,tvDate,tvSummary;

        RelativeLayout root;

        public Holder(View view) {
            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.tvAuthor = (TextView) view.findViewById(R.id.author);
            this.tvDate = (TextView) view.findViewById(R.id.date);
            this.tvSummary = (TextView) view.findViewById(R.id.summary);
            this.root = (RelativeLayout) view.findViewById(R.id.title_root);
            view.setTag(this);
        }

        public void bindData(Article article){
            tvTitle.setText(article.getTitle());
            tvAuthor.setText(article.getAuthor());
            //tvDate.setText(article.getName());
            tvSummary.setText(article.getSummary());

            root.setBackgroundColor(commonCache.getColorByType(article.getIdType()));
        }
    }
}
