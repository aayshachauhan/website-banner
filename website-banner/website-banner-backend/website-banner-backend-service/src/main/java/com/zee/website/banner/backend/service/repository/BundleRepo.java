package com.zee.website.banner.backend.service.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.website.banner.backend.service.entity.BundleEntity;


@Repository
public interface BundleRepo extends JpaRepository<BundleEntity, Long> {

	List<BundleEntity> findByIdIn(List<Long> ids);
	
	Optional<BundleEntity> findById(Long id);
	



}
