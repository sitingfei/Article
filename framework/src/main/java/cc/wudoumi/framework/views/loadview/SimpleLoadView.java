package cc.wudoumi.framework.views.loadview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import cc.wudoumi.framework.R;


/**
 * Created by Administrator on 2015/6/27.
 */
public class SimpleLoadView extends RelativeLayout implements ILoadAnimation{




    public SimpleLoadView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    protected void init(Context context){
        setBackgroundColor(Color.BLACK);
        LayoutInflater.from(context).inflate(R.layout.framework_view_loding,this,true);
    }
}
