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
@Table(name = "banner_location_master")
@Getter
@Setter
@NoArgsConstructor
public class BannerLocationMaster {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "div_type")
	private String divType;
	
	@Column(name = "bundle_banner_location_type")
	private String bundelBannerLocationType;

	@Column(name = "desc")
	private String desc;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "modified_date")
	private String modifiedDate;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

}
