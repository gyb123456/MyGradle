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
@Table(name="base_station")
public class Station {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="source")
	private Integer source;
	
	@Column(name="route_num")
	private Integer routeNum;
	
	@Column(name="station_num")
	private String stationNum;
	
	@Column(name="name")
	private String name;
	
	@Column(name="direction")
	private Integer direction;
	
	@Column(name="idx")
	private Integer idx;
	
	@Column(name="lon")
	private Double lon;
	
	@Column(name="lat")
	private Double lat;
	
	@Column(name="invl_km")
	private Float invlKm;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "first_time")
	private Date firstTime;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@Column(name = "last_time")
	private Date lastTime;
	
	@Column(name="grid")
	private Long grid;

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

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Float getInvlKm() {
		return invlKm;
	}

	public void setInvlKm(Float invlKm) {
		this.invlKm = invlKm;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Long getGrid() {
		return grid;
	}

	public void setGrid(Long grid) {
		this.grid = grid;
	}	
}