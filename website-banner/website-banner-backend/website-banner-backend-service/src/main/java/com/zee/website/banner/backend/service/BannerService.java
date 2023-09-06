package com.zee.website.banner.backend.service;

import java.io.IOException;
import java.util.List;

import com.zee.website.banner.backend.api.dtos.BannerDto;
import com.zee.website.banner.backend.api.dtos.BannerReadRequest;
import com.zee.website.banner.backend.api.dtos.BannerResponseDto;
import com.zshop.commons.exception.business.BusinessException;

public interface BannerService {

	BannerResponseDto readbanner(BannerReadRequest bannerReadRequest) throws BusinessException, IOException;

	BannerResponseDto writeAndEditBanner(List<BannerDto> bannerDto) throws BusinessException, IOException;

}
