package com.example.demo.vo;

import java.util.Date;

public class CommentVO {
 
    private int cno;
    private int bno;
    private String content;
    private String writer;
    private Date reg_date;

    private String dataError = null;

    
 
    public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentVO(int cno, int bno, String content, String writer, Date reg_date) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.content = content;
		this.writer = writer;
		this.reg_date = reg_date;
	}

	public int getCno() {
        return cno;
    }
 
    public void setCno(int cno) {

    	if(cno<0) {
			this.cno= -1;
		}else {
			this.cno=cno;
		}

    }
 
    public int getBno() {
        return bno;
    }
 
    public void setBno(int bno) {

    	if(bno<0) {
			this.bno= -1;
		}else {
			this.bno=bno;
		}

    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {

    	if(content ==null || content.equals("")) {
			System.out.println("content 값 없음");
			this.content="error";
		}else {
			this.content = content;	
		}

    }
 
    public String getWriter() {
        return writer;
    }
 
    public void setWriter(String writer) {

    	if(writer ==null || writer.equals("")) {
			System.out.println("writer 값 없음");
			this.writer="error";
		}else {
			this.writer = writer;	
		}

    }
 
    public Date getReg_date() {
        return reg_date;
    }
 
    public void setReg_date(Date reg_date) {

    	if(reg_date ==null || reg_date.equals("")) {
			System.out.println("reg_date 값 없음");
			dataError = "error";
		}else {
			this.reg_date = reg_date;	
		}

    }

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", content=" + content + ", writer=" + writer + ", reg_date="

				+ reg_date + "]"+dataError;

	}
    
    
 
}
