package com.base.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="base_route")
public class Route {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="source")
	private Integer source;
	
	@Column(name="route_num")
	private Integer routeNum;
	
	@Column(name="name")
	private String name;
	
	@Column(name="up_trml_station")
	private String upTrmlStation;
	
	@Column(name="down_trml_station")
	private String downTrmlStation;
	
	@Column(name="up_station_amount")
	private Integer upStationAmount;
	
	@Column(name="down_station_amount")
	private Integer downStationAmount;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "up_first_time")
	private Date upFirstTime;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "up_last_time")
	private Date upLastTime;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "down_first_time")
	private Date downFirstTime;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "down_last_time")
	private Date downLastTime;
	
	@Column(name="update_by")
	private Integer updateBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "update_time")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getRouteNum() {
		return routeNum;
	}

	public void setRouteNum(Integer routeNum) {
		this.routeNum = routeNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpTrmlStation() {
		return upTrmlStation;
	}

	public void setUpTrmlStation(String upTrmlStation) {
		this.upTrmlStation = upTrmlStation;
	}

	public String getDownTrmlStation() {
		return downTrmlStation;
	}

	public void setDownTrmlStation(String downTrmlStation) {
		this.downTrmlStation = downTrmlStation;
	}

	public Integer getUpStationAmount() {
		return upStationAmount;
	}

	public void setUpStationAmount(Integer upStationAmount) {
		this.upStationAmount = upStationAmount;
	}

	public Integer getDownStationAmount() {
		return downStationAmount;
	}

	public void setDownStationAmount(Integer downStationAmount) {
		this.downStationAmount = downStationAmount;
	}

	public Date getUpFirstTime() {
		return upFirstTime;
	}

	public void setUpFirstTime(Date upFirstTime) {
		this.upFirstTime = upFirstTime;
	}

	public Date getUpLastTime() {
		return upLastTime;
	}

	public void setUpLastTime(Date upLastTime) {
		this.upLastTime = upLastTime;
	}

	public Date getDownFirstTime() {
		return downFirstTime;
	}

	public void setDownFirstTime(Date downFirstTime) {
		this.downFirstTime = downFirstTime;
	}

	public Date getDownLastTime() {
		return downLastTime;
	}

	public void setDownLastTime(Date downLastTime) {
		this.downLastTime = downLastTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
