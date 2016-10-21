package cn.heyl.weituangou.entity;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ImageUrl {
    private String url;
    private String pic_url;
    private String sid;

    public ImageUrl() {
    }

    public ImageUrl(String url, String pic_url, String sid) {
        this.url = url;
        this.pic_url = pic_url;
        this.sid = sid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "ImageUrl{" +
                "url='" + url + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
