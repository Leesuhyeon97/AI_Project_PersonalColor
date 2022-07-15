package com.example.demo.vo;

public class ColorCombVO {

	private String id,top,bottom,shoes;
	private Integer no;
	
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getShoes() {
		return shoes;
	}

	public void setShoes(String shoes) {
		this.shoes = shoes;
	}

	@Override
	public String toString() {
		return "ColorCombVO [no="+no+"id="+id+"top=" + top + ", bottom=" + bottom + ", shoes=" + shoes + "]";
	}

	public ColorCombVO(Integer no,String id,String top, String bottom, String shoes) {
		super();
		setNo(no);
		setId(id);
		setTop(top);
		setBottom(bottom);
		setShoes(shoes);
	}

	public ColorCombVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
