package com.example.demo.rest;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.example.demo.service.JwtUserDetailsService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	ShopRepository shopRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtUserDetailsService userService;
	
	
	@GetMapping("/dislike/{idShop}")
	public void dislikeShop(@PathVariable String idShop) {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
			    SecurityContextHolder.getContext().getAuthentication();
		String username="";
		username=auth.getName().toString();
		userService.dislikeShop(username, idShop);
	}
	@GetMapping("/like/{idShop}")
	public void likeShop(@PathVariable String idShop) {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
			    SecurityContextHolder.getContext().getAuthentication();
		
		
		String username="";
		username=auth.getName().toString();
		userService.likeShop(username, idShop);
		
	}
	
	@DeleteMapping("/delete/{idShop}")
	public void deleteShop(@PathVariable String idShop) {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
			    SecurityContextHolder.getContext().getAuthentication();
	
		String username="";
		username=auth.getName().toString();
		userService.deleteShopFromPreferredList(username, idShop);
		
	}

	
	
}
