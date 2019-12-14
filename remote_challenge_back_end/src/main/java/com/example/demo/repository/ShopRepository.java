package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	@Query("Select s.shop from UserShop s where s.user.id=?1")
	public List<Shop> getUserShops(long id);

	@Query("Select s.shop from UserShop s where s.liked=1 and s.user.id=?1")
	public List<Shop> getUserShopsLiked(long id);

	@Transactional
	@Modifying
	@Query("delete from UserShop s where s.user.id=?1 and s.shop.id=?2")
	public void deleteShopUser(long idUser, long idShop);

}
