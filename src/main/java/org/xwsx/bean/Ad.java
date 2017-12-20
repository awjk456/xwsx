package org.xwsx.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
@JsonInclude(Include.NON_NULL) 是springmvc中的标注，是为了控制返回的json字符串显示哪些字段。这里的设置是为null的字段不显示
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad extends BaseBean{
    private String title;
    private String link;
    private String id;
    private String imgFileName;
    private String weight;
    private String weights;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", id='" + id + '\'' +
                ", imgFileName='" + imgFileName + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
