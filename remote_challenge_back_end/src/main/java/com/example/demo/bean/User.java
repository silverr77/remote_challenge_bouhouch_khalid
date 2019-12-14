package com.example.demo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "USER")
public class User implements Serializable {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique=true)
	private String username;
	private String name;
	private String password;
	private double lat;
	private double lon;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserShop> shops;

	
	

	public User() {
		super();
	}
	
	
	public User(String username, String password,String name) {
		super();
		this.username = username;
		this.password = password;
		this.lat=0;
		this.lon=0;
		this.name=name;
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserShop> getShops() {
		return shops;
	}

	public void setShops(List<UserShop> shops) {
		this.shops = shops;
	}
	
	
	
}
