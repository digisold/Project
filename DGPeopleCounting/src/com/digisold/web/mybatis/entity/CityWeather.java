package com.digisold.web.mybatis.entity;

import java.util.Date;

public class CityWeather {
	private String id;

	private Integer city;

	private String weather;

	private String ltmp;

	private String htmp;

	private String pm;

	private Date wtime;

	public CityWeather(String id, Integer city, String weather, String ltmp, String htmp, String pm, Date wtime) {
		this.id = id;
		this.city = city;
		this.weather = weather;
		this.ltmp = ltmp;
		this.htmp = htmp;
		this.pm = pm;
		this.wtime = wtime;
	}

	public CityWeather() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getLtmp() {
		return ltmp;
	}

	public void setLtmp(String ltmp) {
		this.ltmp = ltmp;
	}

	public String getHtmp() {
		return htmp;
	}

	public void setHtmp(String htmp) {
		this.htmp = htmp;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public Date getWtime() {
		return wtime;
	}

	public void setWtime(Date wtime) {
		this.wtime = wtime;
	}
}