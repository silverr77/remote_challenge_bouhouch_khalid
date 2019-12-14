package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Shop;
import com.example.demo.bean.User;
import com.example.demo.bean.UserShop;
import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserShopRepository;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private UserShopRepository userShopRepository;

	@Override
	public List<Shop> getNearbyShops(String username) {

		User user=userRepository.findByUsername(username);
		if(user==null){
			return shopRepository.findAll();
		}else {	
			/*start : delete shops after 2h*/
			List<UserShop> us=userShopRepository.findAll();	
			Date d=new Date();
			for(UserShop u:us) {
				if(u.getLiked()==0) {
					long hours = ((d.getTime() - u.getCreated_at().getTime()) / 1000)/3600;

					if(hours>2) {
						userShopRepository.delete(u);
					}	
				}					
			}
			/*end : delete shops after 2h*/
			
			double x=user.getLat();
			double y=user.getLon();
			
			List<Shop> shops=shopRepository.findAll();
			
			double distance;
			for(Shop s:shops) {
				distance=Math.sqrt(Math.pow(x-s.getLat(), 2)+Math.pow(y-s.getLon(), 2));
				s.setDistance(distance);
			}
			List<Shop> Usershops=shopRepository.getUserShops(user.getId());
			if(Usershops!=null) {
				List<Shop> t=new ArrayList<Shop>();
				
				for(Shop s:shops) {
					int a=0;
					for(Shop su:Usershops) {
						if(su.getId()==s.getId()) {
							a=1;
							break;
						}
					}
					if(a==0) t.add(s); 
				}
				
				shops=t;	
			}
			//sorted
			Collections.sort(shops);
			
			return shops;
		
		}
		
	}

	@Override
	public List<Shop> getPreferredShops(String username) {
		
		User user = userRepository.findByUsername(username);
		return shopRepository.getUserShopsLiked(user.getId());
	}

}
