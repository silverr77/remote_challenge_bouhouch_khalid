package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.UserShop;

public interface UserShopRepository extends JpaRepository<UserShop, Long>{
	
}
