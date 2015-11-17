package cc.wudoumi.article.common.response;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * author: qianjujun on 2015/11/17 12.
 * email:  qianjujun@imcoming.cn
 */
public class TipsLodingRequestListner extends LodingRequestListner{


    public TipsLodingRequestListner(Context context, boolean loding, boolean cancelAble) {
        super(context, loding, cancelAble,true);
    }

    public TipsLodingRequestListner(Context context, boolean loding) {
        this(context, loding, true);
    }

    public TipsLodingRequestListner(Context context) {
        this(context, true, true);
    }



    public TipsLodingRequestListner(Fragment v4Fragment, boolean loding, boolean cancelAble) {
        super(v4Fragment, loding, cancelAble, true);
    }

    public TipsLodingRequestListner(Fragment v4Fragment, boolean loding) {
        this(v4Fragment, loding, true);
    }

    public TipsLodingRequestListner(Fragment v4Fragment) {
        this(v4Fragment, true, true);
    }
}
