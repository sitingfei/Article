package cc.wudoumi.article.common.response;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * author: qianjujun on 2015/10/22 16.
 * email:  qianjujun@imcoming.cn
 */
public class TipsLodingResposeListner extends LodingResposeListner{
    public TipsLodingResposeListner(Context context) {
        super(true, context);
    }

    public TipsLodingResposeListner(Fragment v4Fragment) {
        super(true, v4Fragment);
    }

    public TipsLodingResposeListner(Fragment v4Fragment, boolean needShow, boolean cancelAble) {
        super(true, v4Fragment, needShow, cancelAble);
    }

    public TipsLodingResposeListner(Context context, boolean needShow, boolean cancelAble) {
        super(true, context, needShow, cancelAble);
    }

    public TipsLodingResposeListner(Fragment v4Fragment, boolean needShow) {
        super(true, v4Fragment, needShow);
    }

    public TipsLodingResposeListner( Context context, boolean needShow) {
        super(true, context, needShow);
    }
}
