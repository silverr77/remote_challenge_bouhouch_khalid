package com.example.demo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="SHOP")
public class Shop implements Serializable, Comparable<Shop> {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String Description;
	private String picture;
	private String phone;
	private String adresse;
	private double lat;
	private double lon;
	private double distance;
	
	
	
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserShop> users;
	
	public Shop() {
		super();
	}
	
	
	public Shop(String name, String picture, String phone, String adresse, double lat, double lon,String Description) {
		super();
		this.name = name;
		this.picture = picture;
		this.phone = phone;
		this.adresse = adresse;
		this.lat = lat;
		this.lon = lon;
		this.distance=0;
		this.Description=Description;
	}

	
	
	
	
	
	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @Override     
	  public int compareTo(Shop shop) {          
	    return (this.getDistance() < shop.getDistance() ? -1 : 
	            (this.getDistance() == shop.getDistance() ? 0 : 1));     
	  } 
	
	
}
