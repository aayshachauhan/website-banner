package com.zee.website.banner.backend.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.website.banner.api.dto.BundleCreateDto;
import com.zee.website.banner.api.dto.BundleDto;
import com.zee.website.banner.api.dto.BundleRequest;
import com.zee.website.banner.api.dto.PromoElkCreateRequest;
import com.zee.website.banner.api.response.BundleResponse;
import com.zee.website.banner.backend.service.BundleService;
import com.zee.website.banner.backend.service.config.BannerElkConfig;
import com.zee.website.banner.backend.service.entity.BundleEntity;
import com.zee.website.banner.backend.service.repository.BundleRepo;
import com.zee.website.banner.backend.service.utils.CommonUtils;
import com.zshop.commons.exception.business.BusinessException;
import com.zshop.helper.utils.GsonUtils;
import com.zshop.helper.utils.NullAwareBeanUtilsBean;
import com.zshop.helper.utils.ServiceUtils;
import com.zshop.helper.utils.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BundleServiceImpl implements BundleService {

	@Autowired
	BundleRepo bundleRepo;

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	private BannerElkConfig bannerElkConfig;

	@Override
	public BundleResponse bundleCreate(List<BundleDto> bundleDto) throws BusinessException, IOException {

		BundleResponse bundleResponse = new BundleResponse();

		try {
			List<BundleEntity> bundleEntites = new ArrayList<>();
			Map<String, List<Long>> actionBundleIdMap = new HashMap<>();

			List<Long> updatedIds = new ArrayList<>();
			List<Long> insertIds = new ArrayList<>();

			for (BundleDto bundle : bundleDto) {

				Boolean bannerLocationMaster = commonUtils.validatBannerBundleLocation("BUNDLE",
						bundle.getBundleLocation(), "Y");

				Boolean bannersBundlesMaster = commonUtils.validateBannerBundleTypeCode("BUNDLE",
						bundle.getBundleType(), "Y");

				Boolean websiteMaster = commonUtils.validateWebsiteMaster(bundle.getWebsiteCode(), "Y");

				if (bannerLocationMaster.equals(Boolean.TRUE) && bannersBundlesMaster.equals(Boolean.TRUE)
						&& websiteMaster.equals(Boolean.TRUE)
						&& commonUtils.validate(bundle.getStartDate(), bundle.getEndDate())) {

					if (StringUtils.isEmpty(bundle.getEndDate()))
						bundle.setEndDate(null);
					if (StringUtils.isEmpty(bundle.getStartDate()))
						bundle.setStartDate(null);

					BundleEntity bundleEntity = null;

					if (bundle.getId() != null) {
						log.info("Bundle update service started");

						bundleEntity = updateBundle(bundle);
						bundleEntity = bundleRepo.save(bundleEntity);
						updatedIds.add(bundleEntity.getId());

					} else {
						bundle.setIsActive(bundle.getIsActive() != null && bundle.getIsActive().isEmpty() ? "Y"
								: bundle.getIsActive());

						bundleEntity = insertInBundle(bundle);
						bundleEntity = bundleRepo.save(bundleEntity);
						insertIds.add(bundleEntity.getId());

					}

					bundleEntites.add(bundleEntity);

				} else {
					throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(),
							"Bundle validations failed. Please check codes and dates and sequence", null);
				}

			}
			actionBundleIdMap.put("UPDATE", updatedIds);
			actionBundleIdMap.put("INSERT", insertIds);

			saveInElk(bundleEntites);
			bundleResponse.setActionBundleIdMap(actionBundleIdMap);
		} catch (BusinessException e) {

			log.error("List not created successfully", e);
		}

		return bundleResponse;
	}

	private BundleEntity insertInBundle(BundleDto bundleDto) throws BusinessException {
		log.info("Bundle insert service started");
		BundleEntity bundleEntity = null;
		Boolean isValidRequest = checkProductAndUrl(bundleDto.getBundleImageCdnUrl(), bundleDto.getBundleLandingUrl(),
				bundleDto.getBundleProduct());

		if (isValidRequest.equals(Boolean.TRUE) && bundleDto.getBundleSequence() != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			bundleEntity = objectMapper.convertValue(bundleDto, BundleEntity.class);
		} else {
			throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(),
					"Please provide Image URL and bundleSequence", null);

		}

		return bundleEntity;

	}

	private BundleEntity updateBundle(BundleDto bundleDto) throws BusinessException {
		Optional<BundleEntity> bundles = bundleRepo.findById(bundleDto.getId());
		BundleEntity bundleEntity = null;
		if (bundles.isPresent()) {
			bundleEntity = bundles.get();

			BeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			try {
				beanUtils.copyProperties(bundleEntity, bundleDto);
			} catch (IllegalAccessException e) {
				log.error("Please specify valid destination file {}", e);
			} catch (InvocationTargetException e) {
				log.error("Exception occurred in beanutils{}", e);
				e.printStackTrace();
			}
		} else {
			throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(), "Id not found", null);
		}
		return bundleEntity;
	}

	public void saveInElk(List<BundleEntity> bundleEntityList) throws BusinessException, IOException {
		log.info("Save in elk process starts");

		List<BundleCreateDto> bundleList = new ArrayList<>();

		try {
			for (BundleEntity bundleEntity : bundleEntityList) {
				ObjectMapper objectMapper = new ObjectMapper();
				BundleCreateDto bundleCreateDto = objectMapper.convertValue(bundleEntity, BundleCreateDto.class);
				bundleList.add(bundleCreateDto);
			}

			PromoElkCreateRequest promoElkCreateRequest = new PromoElkCreateRequest();
			promoElkCreateRequest.setBundleCreateDtos(bundleList);

			String postUrl = new StringBuilder(bannerElkConfig.getBaseUrl())
					.append(bannerElkConfig.getBundleIngestUrl()).toString();
			String promoElkCreateRequestStr = GsonUtils.registerGsonBuilder().toJson(promoElkCreateRequest);

			ServiceUtils.post(postUrl, promoElkCreateRequestStr);
		} catch (IllegalArgumentException e) {
			log.error("NOT save in elastic", e);
		}

	}

	public boolean checkProductAndUrl(String bundleImageCdnUrl, String bundleLandingUrl, String bundleProduct) {
		Boolean validRequest = Boolean.FALSE;

		if (StringUtils.isEmpty(bundleImageCdnUrl) && StringUtils.isEmpty(bundleLandingUrl)) {
			if (StringUtils.isNotEmpty(bundleProduct))
				validRequest = Boolean.TRUE;
		} else if ((StringUtils.isEmpty(bundleProduct) && StringUtils.isNotEmpty(bundleImageCdnUrl))
				&& StringUtils.isNotEmpty(bundleLandingUrl)) {
			validRequest = Boolean.TRUE;
		} else if ((StringUtils.isNotEmpty(bundleProduct) && StringUtils.isNotEmpty(bundleImageCdnUrl))
				&& StringUtils.isNotEmpty(bundleLandingUrl)) {
			validRequest = Boolean.TRUE;
		}

		return validRequest;
	}

	@Override
	public BundleResponse readBundleByIds(BundleRequest bundleRequest) throws BusinessException, IOException {

		BundleResponse bundleResponse = new BundleResponse();

		try {
			List<BundleEntity> bundles = bundleRepo.findByIdIn(bundleRequest.getIds());
			List<BundleDto> bundleDtoList = new ArrayList<>();

			if (ObjectUtils.isNotEmpty(bundles)) {
				log.info("Entity is not Empty");
				for (BundleEntity bundleEntity : bundles) {

					ObjectMapper objectMapper = new ObjectMapper();
					BundleDto bundleDto = objectMapper.convertValue(bundleEntity, BundleDto.class);
					bundleDtoList.add(bundleDto);
				}

				if (bundleRequest.getIsSyncToElkRequired() != null
						&& bundleRequest.getIsSyncToElkRequired().equals(Boolean.TRUE)) {
					saveInElk(bundles);
				}
			} else {
				throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(), "IsSyncToElkRequired true", null);
			}

			bundleResponse.setBundleDtos(bundleDtoList);
		} catch (IllegalArgumentException e) {
			log.error("Failed to read data", e);

		}

		return bundleResponse;

	}

}