package com.zee.website.banner.backend.service.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.kms.model.NotFoundException;
import com.zee.website.banner.api.dto.BundleDto;
import com.zee.website.banner.api.dto.BundleRequest;
import com.zee.website.banner.api.response.BundleResponse;
import com.zee.website.banner.backend.service.BundleService;
import com.zshop.commons.exception.business.BusinessException;
import com.zshop.commons.exception.server.ServiceException;
import com.zshop.helper.utils.BaseResponse;
import com.zshop.helper.utils.BaseResponseBuilder;
import com.zshop.helper.utils.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class BundleController {
	@Autowired
	BundleService bundleService;

	@PostMapping("/v1/addBundle")
	public ResponseEntity<BaseResponse> addBundles(@Valid @RequestBody List<BundleDto> bundleDto)
			throws BusinessException, IOException, ServiceException, ParseException {

		log.info("Request for banner : {}", bundleDto);
		BundleResponse responseDto = bundleService.bundleCreate(bundleDto);
		BaseResponse baseResponse = new BaseResponseBuilder().setBaseResponseWithStatusAndCodeAndData(
				HttpStatus.OK.name(), StatusCodeEnum.SUCCESS_CODE.getCode(), responseDto);

		return new ResponseEntity<>(baseResponse, HttpStatus.valueOf(baseResponse.getStatus()));
	}
	
	@GetMapping("/v1/readBundle")
	public ResponseEntity<BaseResponse> readyByid(@RequestBody BundleRequest bundleRequest)
			throws BusinessException, NotFoundException, IOException, ServiceException {

		log.info("Read Bundle Api");
		BundleResponse bundleResponse = bundleService.readBundleByIds(bundleRequest);
		BaseResponse baseResponse = new BaseResponseBuilder().setBaseResponseWithStatusAndCodeAndData(
				HttpStatus.OK.name(), StatusCodeEnum.SUCCESS_CODE.getCode(), bundleResponse);

		return new ResponseEntity<>(baseResponse,
				HttpStatus.valueOf(baseResponse.getStatus()));
	}
}