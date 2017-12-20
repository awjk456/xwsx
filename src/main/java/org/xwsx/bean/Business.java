package org.xwsx.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business extends BaseBean{
    private int id;
    private String imgFileName;
    private String title;
    private String subtitle;
    private Double price;
    private int distance;
    private int number;
    private int stat;
    private String desc;
    private String city;
    private String category;
    private int starTotalNum;
    private int commentTotalNum;
    private Dic cityDic;
    private Dic categoryDic;

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", imgFileName='" + imgFileName + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", price=" + price +
                ", distance=" + distance +
                ", number=" + number +
                ", stat=" + stat +
                ", desc='" + desc + '\'' +
                ", city='" + city + '\'' +
                ", category='" + category + '\'' +
                ", starTotalNum=" + starTotalNum +
                ", commentTotalNum=" + commentTotalNum +
                ", cityDic=" + cityDic +
                ", categoryDic=" + categoryDic +
                '}';
    }

    public Dic getCityDic() {
        return cityDic;
    }

    public void setCityDic(Dic cityDic) {
        this.cityDic = cityDic;
    }

    public Dic getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(Dic categoryDic) {
        this.categoryDic = categoryDic;
    }

    public int getStarTotalNum() {
        return starTotalNum;
    }

    public void setStarTotalNum(int starTotalNum) {
        this.starTotalNum = starTotalNum;
    }

    public int getCommentTotalNum() {
        return commentTotalNum;
    }

    public void setCommentTotalNum(int commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
