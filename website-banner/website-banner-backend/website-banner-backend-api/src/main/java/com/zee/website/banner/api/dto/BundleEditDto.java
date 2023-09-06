package com.zee.website.banner.api.dto;

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
public class BundleEditDto {
	@NotNull
	private Long id;

	private String websiteCode;

	private String bundleType;

	private String bundleTitle;

	private String bundleSubtitle;

	private String bundleImageCdnUrl;

	private String bundleLandingUrl;

	private String videoUrl;
	
	private String bundleProduct;

	private Long bundleSequence;

	private String bundleLocation;

	private String isActive;

	private String startDate;

	private String endDate;


}
