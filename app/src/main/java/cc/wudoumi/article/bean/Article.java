package cc.wudoumi.article.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ARTICLE.
 */
public class Article {

    private Long _id;
    private Integer id;
    private Long time;
    private Integer idFrom;
    private String author;
    private String title;
    private String summary;
    private Integer praiseNum;
    private String themeImage;
    private Integer idType;

    public Article() {
    }

    public Article(Long _id) {
        this._id = _id;
    }

    public Article(Long _id, Integer id, Long time, Integer idFrom, String author, String title, String summary, Integer praiseNum, String themeImage, Integer idType) {
        this._id = _id;
        this.id = id;
        this.time = time;
        this.idFrom = idFrom;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.praiseNum = praiseNum;
        this.themeImage = themeImage;
        this.idType = idType;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(Integer idFrom) {
        this.idFrom = idFrom;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

}
