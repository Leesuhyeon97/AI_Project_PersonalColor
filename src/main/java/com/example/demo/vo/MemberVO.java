package com.example.demo.vo;

public class MemberVO {
	
	private String id,pw,phone,email,gender,age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id ==null || id.equals("")) {
			System.out.println("id 값 없음");
			this.id = "error";
		}else {
			this.id = id;	
		}
		
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		if(pw ==null || pw.equals("")) {
			System.out.println("pw 값 없음");
			this.pw = "error";
		}else {
			this.pw = pw;	
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(phone ==null || phone.equals("")) {
			System.out.println("phone 값 없음");
			this.phone = "error";
		}else {
			this.phone = phone;	
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email ==null || email.equals("")) {
			System.out.println("email 값 없음");
			this.email = "error";
		}else {
			this.email = email;	
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if(gender ==null || gender.equals("")) {
			System.out.println("gender 값 없음");
			this.gender = "error";
		}else {
			this.gender = gender;	
		}
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		if(age ==null || age.equals("")) {
			System.out.println("age 값 없음");
			this.age = "error";
		}else {
			this.age = age;	
		}
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", phone=" + phone + ", email=" + email + ", gender=" + gender
				+ ", age=" + age + "]";
	}

	public MemberVO(String id, String pw, String phone, String email, String gender, String age) {
		super();
		setId(id);
		setPw(pw);
		setPhone(phone);
		setEmail(email);
		setGender(gender);
		setAge(age);
	}
	
	

	

}
