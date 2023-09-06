package com.zee.website.banner.backend.service;

import java.io.IOException;
import java.util.List;

import com.zee.website.banner.api.dto.BundleDto;
import com.zee.website.banner.api.dto.BundleRequest;
import com.zee.website.banner.api.response.BundleResponse;
import com.zshop.commons.exception.business.BusinessException;

public interface BundleService {

	BundleResponse bundleCreate(List<BundleDto> bundleDto) throws BusinessException, IOException;
	
	BundleResponse readBundleByIds(BundleRequest bundleRequest) throws BusinessException, IOException;


}
