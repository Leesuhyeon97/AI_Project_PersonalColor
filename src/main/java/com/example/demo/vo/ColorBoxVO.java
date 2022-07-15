package com.example.demo.vo;

public class ColorBoxVO {
	private String id,pColor;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id ==null || id.equals("")) {
//			System.out.println("id 값 없음");
			this.id="error";
		}else {
			this.id = id;	
		}
	}

	public String getPColor() {
		return pColor;
	}

	public void setPColor(String pColor) {
		if(pColor ==null || pColor.equals("")) {
//			System.out.println("pColor 값 없음");
			this.pColor = "error";
		}else {
			this.pColor = pColor;
			
		}
	}


	
	@Override
	public String toString() {
		return "ColorBoxVO [id=" + id + ", pColor=" + pColor + "]";
	}
	

}
