package com.zee.website.banner.backend.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.website.banner.backend.service.entity.BannerLocationMaster;

@Repository
public interface BannerLocationMasterRepo extends JpaRepository<BannerLocationMaster, Long> {


	Boolean existsByDivTypeAndBundelBannerLocationTypeAndIsActive(String divType, String bundelBannerLocationType, String isActive);
}