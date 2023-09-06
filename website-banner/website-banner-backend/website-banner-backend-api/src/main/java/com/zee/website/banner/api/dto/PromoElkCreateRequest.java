package com.zee.website.banner.api.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromoElkCreateRequest {

	private List<BannerCreateDto> bannerCreateDtos;
	
	private List<BundleCreateDto> bundleCreateDtos;
}