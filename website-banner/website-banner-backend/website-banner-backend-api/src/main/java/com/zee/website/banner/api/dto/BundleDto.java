package com.zee.website.banner.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BundleDto {

	private Long id;

	@NotEmpty(message ="Please provide valid websiteCode")
	private String websiteCode;

	@NotEmpty(message ="Please provide valid bundleType")
	private String bundleType;

	private String bundleTitle;

	private String bundleSubtitle;

	@NotNull
	private String bundleImageCdnUrl;

	@NotNull
	private String bundleLandingUrl;

	private String bundleVideoUrl;
	
	@NotNull
	private String bundleProduct;

	@NotNull(message="Please provide bundleSequence")
	private Long bundleSequence;

	@NotEmpty(message ="Please provide valid bundleLocation")
	private String bundleLocation;

	private String isActive;

	private String startDate;

	private String endDate;

}
