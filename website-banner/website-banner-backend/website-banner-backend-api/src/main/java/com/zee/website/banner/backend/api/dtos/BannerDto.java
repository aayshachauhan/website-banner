package com.zee.website.banner.backend.api.dtos;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {

	private Long id;

	@NotEmpty(message ="Please provide valid Website Code")
	private String websiteCode;

	@NotEmpty(message ="Please provide valid Banner Types")
	private String bannerType;

	private String bannerTitle;

	private String bannerSubtitle;

	@NotEmpty(message = "Please provide valid Image URl")
	private String bannerImageCdnUrl;

	@NotEmpty(message = "Please provide valid Image Landing URL")
	private String bannerLandingUrl;

	private String bannerVideoUrl;

	private Long bannerSequence;

	@NotEmpty(message = "Please provide valid Banner Location")
	private String bannerLocation;

	private String isActive;

	private String startDate;

	private String endDate;
	
	private Long catId;

}
