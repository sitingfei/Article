package cc.wudoumi.article.moudle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.Article;
import cc.wudoumi.article.common.util.CommonCache;

/**
 * Created by Wasabeef on 2015/01/03.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context mContext;
    private List<Article> mDataSet;

    private CommonCache commonCache;

    public ArticleAdapter(Context context, List<Article> dataSet) {
        mContext = context;
        mDataSet = dataSet;
        commonCache = CommonCache.get();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_article_, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = mDataSet.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvAuthor.setText(article.getAuthor());
        //tvDate.setText(article.getName());
        holder.tvSummary.setText(article.getSummary());

        holder. root.setBackgroundColor(commonCache.getColorByType(article.getIdType()));
    }

    @Override public int getItemCount() {
        return mDataSet.size();
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Article article, int position) {
        mDataSet.add(position, article);
        notifyItemInserted(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvAuthor,tvDate,tvSummary;

        RelativeLayout root;

        public ViewHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.tvAuthor = (TextView) view.findViewById(R.id.author);
            this.tvDate = (TextView) view.findViewById(R.id.date);
            this.tvSummary = (TextView) view.findViewById(R.id.summary);
            this.root = (RelativeLayout) view.findViewById(R.id.title_root);
        }
    }
}
