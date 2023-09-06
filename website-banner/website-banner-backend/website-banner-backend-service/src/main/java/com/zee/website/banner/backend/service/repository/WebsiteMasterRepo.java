package com.zee.website.banner.backend.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.website.banner.backend.service.entity.WebsiteMaster;

@Repository
public interface WebsiteMasterRepo extends JpaRepository<WebsiteMaster, Long> {

	Boolean existsByCodeAndIsActive(String code, String IsActive);

}
