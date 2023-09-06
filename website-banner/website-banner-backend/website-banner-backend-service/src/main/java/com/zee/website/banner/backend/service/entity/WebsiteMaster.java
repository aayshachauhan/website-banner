package com.zee.website.banner.backend.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "website_master")
@Getter
@Setter
@NoArgsConstructor
public class WebsiteMaster {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "website_code")
	private String code;
	
	@Column(name = "website_name")
	private String websiteName;
	
	@Column(name = "website_url")
	private String websiteUrl;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createDate")
	private String createDate;
	
	@Column(name = "modifiedDate")
	private String modifiedDate;
	
	@Column(name = "isActive")
	private String isActive;

}
