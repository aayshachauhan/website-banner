package com.zee.website.banner.backend.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.website.banner.backend.service.entity.BannersBundlesMaster;

@Repository
public interface BannersBundlesMasterRepo extends JpaRepository<BannersBundlesMaster, Long> {

//	List<MasterBundleEntity> findByCodeAndTypeAndisActive(String code, String type, String isActive);

	Boolean existsByDivTypeAndBannerBundleTypeAndIsActive(String divType,
			String bannerBundleType, String isActive);


}
