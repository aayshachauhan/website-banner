package com.zee.website.banner.backend.service.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.website.banner.backend.service.repository.BannerLocationMasterRepo;
import com.zee.website.banner.backend.service.repository.BannersBundlesMasterRepo;
import com.zee.website.banner.backend.service.repository.WebsiteMasterRepo;
import com.zshop.helper.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonUtils {

	@Autowired
	private BannerLocationMasterRepo bannerLocationMasterRepo;

	@Autowired
	private BannersBundlesMasterRepo bannersBundlesMasterRepo;

	@Autowired
	private WebsiteMasterRepo websiteMasterRepo;

	public Boolean validateWebsiteMaster(String websiteCode, String isActive) {

		Boolean websiteMaster = websiteMasterRepo.existsByCodeAndIsActive(websiteCode, isActive);

		String result = websiteMaster.equals(Boolean.TRUE) ? "Website code is valid"
				: "Please provide valid website code";
		log.info(result);
		return websiteMaster;
	}

	public Boolean validateBannerBundleTypeCode(String type, String bundelBannerLocationType, String isActive) {

		Boolean bannersBundleMaster = bannersBundlesMasterRepo.existsByDivTypeAndBannerBundleTypeAndIsActive(type,
				bundelBannerLocationType, isActive);

		String result = bannersBundleMaster.equals(Boolean.TRUE) ? "Banner/Bundle Type is valid"
				: "Please provide valid Banner/Bundle type";
		log.info(result);
		return bannersBundleMaster;
	}

	public Boolean validatBannerBundleLocation(String type, String locationType, String isActive) {
		Boolean bannerBundleLocationMaster = bannerLocationMasterRepo
				.existsByDivTypeAndBundelBannerLocationTypeAndIsActive(type, locationType, isActive);
		String result = bannerBundleLocationMaster.equals(Boolean.TRUE) ? "Banner/Bundle Location is valid"
				: "Please provide valid Banner/Bundle Location";
		log.info(result);
		return bannerBundleLocationMaster;
	}

	public boolean validate(String startDateStr, String endDateStr) {

		Boolean validRequest = Boolean.FALSE;

		log.info("Validating start date and end date");

		try {
			if ((StringUtils.isNotEmpty(startDateStr) && StringUtils.isEmpty(endDateStr))) {

				validRequest = Boolean.TRUE;
			} else if ((StringUtils.isNotEmpty(startDateStr) && StringUtils.isNotEmpty(endDateStr))) {

				Date startDate = DateUtils.getDateFromString(startDateStr, DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
				Date endDate = DateUtils.getDateFromString(endDateStr, DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
				if (DateUtils.isStartDateBeforeEndDate(startDate, endDate))

					validRequest = Boolean.TRUE;
			} else if ((StringUtils.isEmpty(startDateStr) && StringUtils.isEmpty(endDateStr))) {

				validRequest = Boolean.TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return validRequest;

	}

}
