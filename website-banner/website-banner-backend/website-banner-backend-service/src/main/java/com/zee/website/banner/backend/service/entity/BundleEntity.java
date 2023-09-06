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
@Table(name = "website_bundles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BundleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "site_code")
	private String websiteCode;

	@Column(name = "bundle_types")
	private String bundleType;

	@Column(name = "bundle_title")
	private String bundleTitle;

	@Column(name = "bundle_subtitle")
	private String bundleSubtitle;

	@Column(name = "bundle_image_cdn_url")
	private String bundleImageCdnUrl;

	@Column(name = "bundle_landing_url")
	private String bundleLandingUrl;
	
	@Column(name = "video_url")
	private String bundleVideoUrl;

	@Column(name = "bundle_product")
	private String bundleProduct;

	@Column(name = "bundle_sequence")
	private Long bundleSequence;

	@Column(name = "bundle_location")
	private String bundleLocation;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

//	@Column(name = "created_date")
//	private String createDate;
//
//	@Column(name = "modified_date")
//	private Date modiDate;
//
//	@Column(name = "created_by")
//	private String createBy;
//
//	@Column(name = "modified_by")
//	private String modiBy;
}
