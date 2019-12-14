package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.example.demo.bean.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserShopRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService,userService {

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private UserShopRepository userShopRepository;
	
	public void save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setName(user.getName());
		userRepository.save(newUser);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	@Override
	public void likeShop(String username,String idShop) {
		
		User user=userRepository.findByUsername(username);
		Optional<Shop> shop=shopRepository.findById((long) Integer.parseInt(idShop));
		
		user.getShops().add(new UserShop(user,shop.get(),1,new Date()));	
		userRepository.save(user);	
		
		
	}

	@Override
	public void dislikeShop(String username,String idShop) {
		User user=userRepository.findByUsername(username);
		Optional<Shop> shop=shopRepository.findById((long) Integer.parseInt(idShop));
		
		user.getShops().add(new UserShop(user,shop.get(),0,new Date()));	
		userRepository.save(user);		
		
	}

	@Override
	public void deleteShopFromPreferredList(String username,String idShop) {
		
		User user=userRepository.findByUsername(username);
		shopRepository.deleteShopUser(user.getId(), (long) Integer.parseInt(idShop));
		
	}
	
	
	
	
}