package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Goods;
import com.example.demo.dao.GoodsRepository;

@Service
public class GoodsService {

	@Autowired
	private GoodsRepository goodsRepository;

	public Goods addGoods(String goodsName) throws Exception {
		Goods newGood = new Goods();
		newGood.setName(goodsName);
		return goodsRepository.save(newGood);
	}
	
	public List<Goods> getGoods() throws Exception {
		return goodsRepository.findAll();
	}
	
	public Goods getGood(Long id) throws Exception {
		return goodsRepository.findById(id).get();
	}
	
	public Goods updateGood(Long id, String name) throws Exception {
		Goods good = new Goods(id, name);
		return goodsRepository.save(good);
	}
	
	public void deleteGood(Long id) throws Exception {
		goodsRepository.deleteById(id);
	}
}
