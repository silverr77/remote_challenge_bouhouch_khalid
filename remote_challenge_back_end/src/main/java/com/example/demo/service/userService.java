package com.example.demo.service;

public interface userService {
	
	public void likeShop(String username,String idShop);
	public void dislikeShop(String username,String idShop);
	public void deleteShopFromPreferredList(String username,String idShop);
	
}
