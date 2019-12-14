package com.example.demo.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Shop;
import com.example.demo.bean.User;
import com.example.demo.bean.UserShop;
import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserShopRepository;
import com.example.demo.service.ShopServiceImpl;

@RestController
@RequestMapping("shops")
@CrossOrigin
public class ShopController {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopServiceImpl shopService;

	
	@GetMapping("/getNearbyShops")
	public List<Shop> getNearbyShops() {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
			    SecurityContextHolder.getContext().getAuthentication();
		String username="";
		username=auth.getName().toString();
		return shopService.getNearbyShops(username);
		
	}
	
	@GetMapping("/getPreferredShops")
	public List<Shop> shopsLiked() {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
			    SecurityContextHolder.getContext().getAuthentication();
		String username="";
		username=auth.getName().toString();
		return shopService.getPreferredShops(username);
	}
	
	
	
	
}
