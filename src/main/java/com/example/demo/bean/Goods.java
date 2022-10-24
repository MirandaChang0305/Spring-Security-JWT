package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "商品")
public class Goods implements Serializable{
	
	private static final long serialVersionUID = -4800321155153142046L;
	
	@ApiModelProperty(value="UUID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "goods_generator")
	@SequenceGenerator(name="goods_generator", sequenceName="goods__id_seq", allocationSize = 1)
	@Column(name="_id")
	@JsonProperty("_id")
	private Long id;
	
	@ApiModelProperty(value="商品名稱")
	@JsonProperty("goods_name")
	@Column(nullable=false)
	private String name;

}
