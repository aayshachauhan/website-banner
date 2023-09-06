package com.zee.website.banner.api.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BundleCreateDto {

	private String id;
	private String websiteCode;
	private String bundleType;
	private String bundleCode;
	private String bundleImageCdnUrl;
	private String bundleLandingUrl;
	private String bundleVideoUrl;
	private String bundleSequence;
	private String bundleLocation;
	private String bundleTitle;
	private String bundleSubtitle;

	private String bundleProduct;
	
	private String isActive;

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

}
