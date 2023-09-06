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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
import com.zee.website.banner.api.dto.BannerCreateDto;
import com.zee.website.banner.api.dto.PromoElkCreateRequest;
import com.zee.website.banner.backend.api.dtos.BannerDto;
import com.zee.website.banner.backend.api.dtos.BannerReadRequest;
import com.zee.website.banner.backend.api.dtos.BannerResponseDto;
import com.zee.website.banner.backend.service.BannerService;
import com.zee.website.banner.backend.service.config.BannerElkConfig;
import com.zee.website.banner.backend.service.entity.BannerEntity;
import com.zee.website.banner.backend.service.repository.BannerRepo;
import com.zee.website.banner.backend.service.utils.CommonUtils;
import com.zshop.commons.exception.business.BusinessException;
import com.zshop.helper.utils.GsonUtils;
import com.zshop.helper.utils.NullAwareBeanUtilsBean;
import com.zshop.helper.utils.ServiceUtils;
import com.zshop.helper.utils.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepo bannerRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	private BannerElkConfig bannerElkConfig;

	// @Transactional(transactionManager = "readMasterDataSourceTransactionManager",
	// rollbackFor = Exception.class)
	@Override
	public BannerResponseDto writeAndEditBanner(List<BannerDto> bannerDto) throws BusinessException, IOException {
		BannerResponseDto responseDto = new BannerResponseDto();

		try {
			List<BannerEntity> bannerEntityList = new ArrayList<>();

			Map<String, List<Long>> mapBannerId = new HashMap<>();

			List<Long> updateIds = new ArrayList<>();
			List<Long> insertIds = new ArrayList<>();

			for (BannerDto banners : bannerDto) {
				Boolean bannerLocationMaster = commonUtils.validatBannerBundleLocation("BANNER",
						banners.getBannerLocation(), "Y");

				Boolean bannersBundlesMaster = commonUtils.validateBannerBundleTypeCode("BANNER",
						banners.getBannerType(), "Y");

				Boolean websiteMaster = commonUtils.validateWebsiteMaster(banners.getWebsiteCode(), "Y");

				if (bannerLocationMaster.equals(Boolean.TRUE) && bannersBundlesMaster.equals(Boolean.TRUE)
						&& websiteMaster.equals(Boolean.TRUE)
						&& commonUtils.validate(banners.getStartDate(), banners.getEndDate())) {

					if (StringUtils.isEmpty(banners.getEndDate()))
						banners.setEndDate(null);

					if (StringUtils.isEmpty(banners.getStartDate()))
						banners.setStartDate(null);

					BannerEntity bannerEntity = new BannerEntity();
					if (banners.getId() != null) {
						log.info("Update Banner Api started");
						bannerEntity = updateBanner(banners, bannerEntity);
						bannerEntity = bannerRepo.save(bannerEntity);

						updateIds.add(bannerEntity.getId());
					} else {
						log.info("Add Banner Api started");
						bannerEntity = addBanner(banners, bannerEntity);
						bannerEntity = bannerRepo.save(bannerEntity);

						insertIds.add(bannerEntity.getId());

					}

					bannerEntityList.add(bannerEntity);

				} else {
					throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(),
							"Banner validations failed. Please check codes,banner sequence and dates", null);
				}
			}

			mapBannerId.put("UPDATED ID", updateIds);
			mapBannerId.put("NEW ID CREATED ", insertIds);

			saveElastic(bannerEntityList);

			responseDto.setMapBannerId(mapBannerId);
		} catch (BusinessException e) {
			log.error("Failed to save and update Data");
		}

		return responseDto;
	}

//	private void save(BannerResponseDto responseDto, BannerEntity bannerEntity) throws BusinessException, IOException {
//		bannerEntity = bannerRepo.save(bannerEntity);
//
//		responseDto.setId(bannerEntity.getId());
//
//		if (ObjectUtils.isNotEmpty(bannerEntity)) {
//			List<BannerEntity> bannerEntityList = new ArrayList<>();
//			bannerEntityList.add(bannerEntity);
//			saveElastic(bannerEntityList);
//		}
//	}

	private BannerEntity updateBanner(BannerDto bannerDto, BannerEntity bannerEntity) throws BusinessException {
		Optional<BannerEntity> bannerEnt = bannerRepo.findById(bannerDto.getId());
		if (bannerEnt.isPresent()) {
			bannerEntity = bannerEnt.get();

			BeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			try {
				beanUtils.copyProperties(bannerEntity, bannerDto);
			} catch (IllegalAccessException e) {
				log.error("Please specify valid destination file {}", e);
			} catch (InvocationTargetException e) {
				log.error("Exception occurred in beanutils{}", e);
				e.printStackTrace();
			}
		} else {
			throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(), "Id not found", null);
		}
		return bannerEntity;
	}

	private BannerEntity addBanner(BannerDto bannerDto, BannerEntity bannerEntity) throws BusinessException {

		bannerDto.setIsActive(
				bannerDto.getIsActive() != null && bannerDto.getIsActive().isEmpty() ? "Y" : bannerDto.getIsActive());
		if (StringUtils.isNotEmpty(bannerDto.getBannerImageCdnUrl())
				&& StringUtils.isNotEmpty(bannerDto.getBannerLandingUrl()) && bannerDto.getBannerSequence() != null) {

			bannerEntity = modelMapper.map(bannerDto, BannerEntity.class);

			// TODO: Add category ID validation
			if (bannerDto.getBannerType().equals("CAT_BNR")) {

				if (ObjectUtils.isNotEmpty(bannerDto.getCatId())) {

					log.info("catgory id save succesfully");
				} else {
					throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(), "Please provide Category Id",
							null);
				}
			}
		} else {
			throw new BusinessException(StatusCodeEnum.BAD_CREDENTIALS.getCode(),
					"Please provide Image CDN URL, Image Landing URL and Banner sequence", null);
		}
		return bannerEntity;
	}

	@Override
	public BannerResponseDto readbanner(BannerReadRequest bannerReadRequest) throws BusinessException, IOException {

		BannerResponseDto bannerResponseDto = new BannerResponseDto();

		try {
			List<BannerEntity> bannerEntities = bannerRepo.findByIdIn(bannerReadRequest.getIds());

			List<BannerCreateDto> bannerCreateDto = new ArrayList<>();

			if (!CollectionUtils.isNullOrEmpty(bannerEntities)) {

				for (BannerEntity bannerEntity : bannerEntities) {

					BannerCreateDto bannerCreateDto1 = modelMapper.map(bannerEntity, BannerCreateDto.class);

					bannerCreateDto.add(bannerCreateDto1);
				}
				if (bannerReadRequest.getIsSyncToElkRequired() != null
						&& bannerReadRequest.getIsSyncToElkRequired().equals(Boolean.TRUE)) {
					saveElastic(bannerEntities);

				} else {
					log.info("Sync is not required");
				}
			} else {
				log.info("Entity not found");
			}
			bannerResponseDto.setBannerCreateDtos(bannerCreateDto);
		} catch (BusinessException e) {
			log.error("Failed to Read data");
		}
		return bannerResponseDto;
	}

	public void saveElastic(List<BannerEntity> bannerEntityList) throws BusinessException, IOException {

		try {
			List<BannerCreateDto> bannerList = new ArrayList<>();
			log.info("Elastic service started");

			for (BannerEntity bannerEntity : bannerEntityList) {
				BannerCreateDto bannerCreateDto = modelMapper.map(bannerEntity, BannerCreateDto.class);
				bannerList.add(bannerCreateDto);
			}
			PromoElkCreateRequest promoElkCreateRequest = new PromoElkCreateRequest();
			promoElkCreateRequest.setBannerCreateDtos(bannerList);

			String postUrl = new StringBuilder(bannerElkConfig.getBaseUrl())
					.append(bannerElkConfig.getBannerIngestUrl()).toString();
			String promoElkCreateRequestStr = GsonUtils.registerGsonBuilder().toJson(promoElkCreateRequest);

			ServiceUtils.post(postUrl, promoElkCreateRequestStr);
		} catch (BusinessException e) {
			log.error("Failed to save data in elastic");
		}

	}

}
