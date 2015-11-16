package cc.wudoumi.article.common.util;

import cc.wudoumi.framework.net.RequestParem;

/**
 * author: qianjujun on 2015/11/12 13.
 * email:  qianjujun@imcoming.cn
 */
public class ParemsTool implements UrlTool{
    private ParemsTool(){}


    private static RequestParem getBaseRequestParem(String url){
        RequestParem requestParem = new RequestParem(url);
        requestParem.setRequestMethod(RequestParem.POST);
        return requestParem;
    }

    public static final RequestParem getArticleType(){
        return getBaseRequestParem(ARTICLE_TYPE);
    }


    public static final RequestParem getArticleList(int typeId,int page){
        String url =  ARTICLE_LIST;
        RequestParem requestParem = getBaseRequestParem(url);
        requestParem.put("subTypeId",typeId+"");
        requestParem.put("page",page+"");
        return requestParem;
    }


    public static final RequestParem getArticleDefaultImage(){
        return getBaseRequestParem(ARTICLE_DEFAULT_IAMGE);
    }

}
