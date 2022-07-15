package com.example.demo.vo;

public class ItemVO {
	String pColor,itemName,imgUrl,siteLink;

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}

	public String getItemNames() {
		return itemName;
	}

	public void setItemNames(String itemName) {
		this.itemName = itemName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSiteLink() {
		return siteLink;
	}

	public void setSiteLink(String siteLink) {
		this.siteLink = siteLink;
	}

	@Override
	public String toString() {
		return "ItemVO [pColor=" + pColor + ", itemName=" + itemName + ", imgUrl=" + imgUrl + ", siteLink=" + siteLink
				+ "]";
	}

	public ItemVO(String pColor, String itemName, String imgUrl, String siteLink) {
		super();
		this.pColor = pColor;
		this.itemName = itemName;
		this.imgUrl = imgUrl;
		this.siteLink = siteLink;
	}

	
}
