package com.zee.website.banner.backend.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "banner-elk")
@EnableConfigurationProperties
public class BannerElkConfig {

	private String baseUrl;
	private String bundleIngestUrl;
	private String bannerIngestUrl;
}
