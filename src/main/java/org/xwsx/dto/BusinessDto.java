package org.xwsx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.web.multipart.MultipartFile;
import org.xwsx.bean.Business;

@JsonInclude(Include.NON_NULL)
public class BusinessDto extends Business{

	private String img;
	private MultipartFile imgFile;
	private String keyword;
	private Integer mumber;
	private Integer star;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getMumber() {
		return mumber;
	}

	public void setMumber(Integer mumber) {
		this.mumber = mumber;
	}

	@Override
	public String toString() {
		return "BusinessDto{" +
				"img='" + img + '\'' +
				", imgFile=" + imgFile +

				", keyword='" + keyword + '\'' +
				", number=" + mumber +
				", star=" + star +
				'}';
	}
}