package com.zee.website.banner.api.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BannerCreateDto {

	private String id;
	private String websiteCode;
	private String bannerType;
	private String bannerImageCdnUrl;
	private String bannerLandingUrl;
	private String bannerSequence;
	private String bannerLocation;
	private String bannerTitle;
	private String bannerSubtitle;
	private String isActive;

	private String bannerVideoUrl;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN", timezone = "Asia/Calcutta")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN", timezone = "Asia/Calcutta")
	private Date endDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN", timezone = "Asia/Calcutta")
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en_IN", timezone = "Asia/Calcutta")
	private Date modifiedDate;
	
	private String createdBy;
	private String modifiedBy;
	
	private String catId;
	
}
