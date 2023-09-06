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
@Table(name = "website_banners")
@Getter
@Setter
@NoArgsConstructor
public class BannerEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "website_code")
	private String websiteCode;

	@Column(name = "banner_types")
	private String bannerType;

	@Column(name = "banner_title")
	private String bannerTitle;

	@Column(name = "banner_subtitle")
	private String bannerSubtitle;

	@Column(name = "banner_image_cdn_url")
	private String bannerImageCdnUrl;

	@Column(name = "banner_landing_url")
	private String bannerLandingUrl;

	@Column(name = "video_url")
	private String bannerVideoUrl;

	@Column(name = "banner_sequence")
	private long bannerSequence;

	@Column(name = "banner_location")
	private String bannerLocation;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "catId")
	private Long catId;

}
