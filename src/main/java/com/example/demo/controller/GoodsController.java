package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Goods;
import com.example.demo.service.GoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="商品")
@RestController
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@ApiOperation(value="新增商品",notes="新增商品")
	@PostMapping("/add")
	public Object addGoods(@RequestBody Goods good) throws Exception{
		return goodsService.addGoods(good.getName());
	}
	
	@ApiOperation(value="取得所有商品",notes="取得所有商品")
	@GetMapping()
	public Object getGoods() throws Exception{
		return goodsService.getGoods();
	}
	
	@ApiOperation(value="取得指定商品",notes="取得指定商品")
	@GetMapping("/{id}")
	public Object getGood(@PathVariable("id") Long id) throws Exception{
		return goodsService.getGood(id);
	}
	
	@ApiOperation(value="更新商品",notes="更新商品")
	@PutMapping("/{id}")
	public Object updateGood(@PathVariable("id") String id, @RequestBody Goods good) throws Exception{
		return goodsService.updateGood(Long.valueOf(id),good.getName());
	}
	
	@ApiOperation(value="刪除商品",notes="刪除商品")
	@DeleteMapping("/{id}")
	public void updateGood(@PathVariable("id") String id) throws Exception{
		goodsService.deleteGood(Long.valueOf(id));
	}

}
