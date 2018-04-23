package com.digisold.web.manage.kldata.vo;

public class KlDataListVo {

	private String date;

	private Long enters = 0L;

	private Long exits = 0L;	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getEnters() {
		return enters;
	}

	public void setEnters(Long enters) {
		this.enters = enters;
	}

	public Long getExits() {
		return exits;
	}

	public void setExits(Long exits) {
		this.exits = exits;
	}

}
