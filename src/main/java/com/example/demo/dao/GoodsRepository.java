package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long>{

}
