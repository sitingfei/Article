package cc.wudoumi.article.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DEFAULT_IMAGE.
 */
public class DefaultImage {

    private Long _id;
    private Integer id;
    private Integer typeId;
    private String url;

    public DefaultImage() {
    }

    public DefaultImage(Long _id) {
        this._id = _id;
    }

    public DefaultImage(Long _id, Integer id, Integer typeId, String url) {
        this._id = _id;
        this.id = id;
        this.typeId = typeId;
        this.url = url;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
