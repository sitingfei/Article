package cc.wudoumi.framework.views.loadview;


import cc.wudoumi.framework.net.ErrorMessage;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface ILoadable {

    void showLoading();

    void showFail(ErrorMessage error);

    void showSuccees();
}
