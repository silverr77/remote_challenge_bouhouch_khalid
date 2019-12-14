package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.Shop;

public interface ShopService {

	public List<Shop> getNearbyShops(String username);
	public List<Shop> getPreferredShops(String username);
	
}
