package com.zee.website.banner.backend.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zee.website.banner.backend.service.entity.BannerEntity;

@Repository
public interface BannerRepo extends JpaRepository<BannerEntity, Long> {
	
	List<BannerEntity> findByIdIn(List<Long> ids);
	
	Optional<BannerEntity> findById(Long id);

}
