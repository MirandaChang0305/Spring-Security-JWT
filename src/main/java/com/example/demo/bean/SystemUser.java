package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Entity
@Table(name="systemuser")
@Data
@ApiModel(description = "使用者")
public class SystemUser implements Serializable{
	
	private static final long serialVersionUID = -6814996335028388927L;

	@ApiModelProperty(value="UUID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "systemuser_generator")
	@SequenceGenerator(name="systemuser_generator", sequenceName="systemuser__id_seq", allocationSize = 1)
	@Column(name="_id")
	private Long id;
	
	@ApiModelProperty(value="帳號")
	@Column(nullable=false)
	private String account;
	
	@ApiModelProperty(value = "密碼")
	@Column(nullable=false)
	private String password;

}
