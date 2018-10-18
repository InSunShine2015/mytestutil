package com.sun.bean.second;

import java.util.Date;

public class Modle {
	private Integer f1;
	private Long f2;
	private Double f3;
	private Date f4;
	private String f5;
	private Des f6;
	
	public Des getF6() {
		return f6;
	}
	public void setF6(Des f6) {
		this.f6 = f6;
	}
	public Integer getF1() {
		return f1;
	}
	public void setF1(Integer f1) {
		this.f1 = f1;
	}
	public Long getF2() {
		return f2;
	}
	public void setF2(Long f2) {
		this.f2 = f2;
	}
	public Double getF3() {
		return f3;
	}
	public void setF3(Double f3) {
		this.f3 = f3;
	}
	public Date getF4() {
		return f4;
	}
	public void setF4(Date f4) {
		this.f4 = f4;
	}
	public String getF5() {
		return f5;
	}
	public void setF5(String f5) {
		this.f5 = f5;
	}
	@Override
	public String toString() {
		return "Modle [f1=" + f1 + ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6 + "]";
	}
	
	
}
