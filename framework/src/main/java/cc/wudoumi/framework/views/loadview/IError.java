package cc.wudoumi.framework.views.loadview;

import android.view.View;

import cc.wudoumi.framework.net.ErrorMessage;


/**
 * Created by Administrator on 2015/6/27.
 */
public interface IError{
    void handlerError(ErrorMessage excetion);

    View getClickAbleViewForRetry();
}
