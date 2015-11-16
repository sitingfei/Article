package cc.wudoumi.framework.views.loadview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.wudoumi.framework.R;
import cc.wudoumi.framework.net.ErrorMessage;


/**
 * Created by Administrator on 2015/6/27.
 */
public class SimpleErrorView extends RelativeLayout implements IError{


    protected View handlerErrorView;

    private View clickAbleView;

    public SimpleErrorView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void handlerError(ErrorMessage excetion) {
        if(handlerErrorView instanceof TextView){
            if(excetion!=null){
                ((TextView)handlerErrorView).setText(excetion.getMessage());
            }else{
                ((TextView)handlerErrorView).setText("无更多数据");
            }
            //((TextView)handlerErrorView).setText(getResources().getString(R.string.batter_error)+excetion.getMessage());
        }
    }

    @Override
    public View getClickAbleViewForRetry() {
        return clickAbleView;
    }


    protected void init(Context context){
        //setBackgroundColor(Color.BLACK);

        View view = LayoutInflater.from(context).inflate(R.layout.framework_view_error,this,true);

        clickAbleView = view.findViewById(R.id.batter_retry);

        handlerErrorView = view.findViewById(R.id.tv_batter_error_info);

    }



}
