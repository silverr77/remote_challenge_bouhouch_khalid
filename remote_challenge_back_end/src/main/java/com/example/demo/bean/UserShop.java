package com.example.demo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
@Entity
public class UserShop implements Serializable {

	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
	
	private int liked;
	private Date created_at;

	public UserShop() {
		super();
	}

	public UserShop(User user, Shop shop, int liked,Date created_at) {
		super();
		this.user = user;
		this.shop = shop;
		this.liked = liked;
		this.created_at=created_at;
	}


	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	
	
	
	
}
