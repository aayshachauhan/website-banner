package com.zee.website.banner.backend.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banners_bundles_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannersBundlesMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "div_type")
	private String divType;

	@Column(name = "banner_bundle_type")
	private String bannerBundleType;

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
