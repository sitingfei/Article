package cc.wudoumi.article.moudle.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.Article;
import cc.wudoumi.article.common.util.CommonCache;

/**
 * Created by Wasabeef on 2015/01/03.
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private Context mContext;
    private List<Article> mDataSet;

    private CommonCache commonCache;

    private int scrrenWidth;

    private int childTypeId;

    public ArticleAdapter(Context context, List<Article> dataSet,int childTypeId) {
        mContext = context;
        mDataSet = dataSet;
        commonCache = CommonCache.get();
        scrrenWidth = commonCache.getScreenWidth((Activity) context);
        this.childTypeId = childTypeId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_VIEW_TYPE_HEADER){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, scrrenWidth * 3 / 5));
            return new ImageHolder(imageView);
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_article_, parent, false);
        return new ViewHolder1(v);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if(position==0){
            ImageHolder holder = (ImageHolder) holder1;
            Glide.with(mContext)
                    .load(CommonCache.get().getImageUrl(childTypeId))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
        }else{
            ViewHolder1 holder = (ViewHolder1) holder1;
            Article article = mDataSet.get(position);
            holder.tvTitle.setText(article.getTitle());
            holder.tvAuthor.setText(article.getAuthor());
            //tvDate.setText(article.getName());
            holder.tvSummary.setText(article.getSummary());

            holder.root.setBackgroundColor(commonCache.getColorByType(article.getIdType()));
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_VIEW_TYPE_HEADER;
        }
        return ITEM_VIEW_TYPE_ITEM;
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Article article, int position) {
        mDataSet.add(position, article);
        notifyItemInserted(position);
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {

        TextView tvTitle, tvAuthor, tvDate, tvSummary;

        RelativeLayout root;

        public ViewHolder1(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.tvAuthor = (TextView) view.findViewById(R.id.author);
            this.tvDate = (TextView) view.findViewById(R.id.date);
            this.tvSummary = (TextView) view.findViewById(R.id.summary);
            this.root = (RelativeLayout) view.findViewById(R.id.title_root);
        }
    }

    static class ImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
