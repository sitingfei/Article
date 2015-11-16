package cc.wudoumi.article.common.util;

/**
 * author: qianjujun on 2015/11/12 13.
 * email:  qianjujun@imcoming.cn
 */
public interface UrlTool {

    String DOMAIN_NAME = "http://wudoumi.cc/jiujiu/";


    String IMAGE_BASE_URL = "http://wudoumi.cc/file/";

    String ARTICLE_API = DOMAIN_NAME + "article/api/";

    String ARTICLE_TYPE = ARTICLE_API + "getArticleTypes.do";



    String ARTICLE_LIST = ARTICLE_API  + "getArticle.do";

    String ARTICLE_DEFAULT_IAMGE = ARTICLE_API  + "getAllDefaultImage.do";
}
