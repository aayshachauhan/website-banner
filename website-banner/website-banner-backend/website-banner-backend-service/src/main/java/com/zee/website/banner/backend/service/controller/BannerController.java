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

import com.zee.website.banner.backend.api.dtos.BannerDto;
import com.zee.website.banner.backend.api.dtos.BannerReadRequest;
import com.zee.website.banner.backend.api.dtos.BannerResponseDto;
import com.zee.website.banner.backend.service.BannerService;
import com.zshop.commons.exception.business.BusinessException;
import com.zshop.commons.exception.server.ServiceException;
import com.zshop.helper.utils.BaseResponse;
import com.zshop.helper.utils.BaseResponseBuilder;
import com.zshop.helper.utils.StatusCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BannerController {

	@Autowired
	BannerService bannerService;

	@GetMapping("/v1/readBanner")
	public ResponseEntity<BaseResponse> readBanner(@RequestBody BannerReadRequest bannerReadRequest)
			throws BusinessException, IOException, ServiceException, ParseException {

		log.info("Request for Read Banner : {}", bannerReadRequest);
		BannerResponseDto responseDto = bannerService.readbanner(bannerReadRequest);
		BaseResponse baseResponse = new BaseResponseBuilder().setBaseResponseWithStatusAndCodeAndData(
				HttpStatus.OK.name(), StatusCodeEnum.SUCCESS_CODE.getCode(), responseDto);

		return new ResponseEntity<>(baseResponse, HttpStatus.valueOf(baseResponse.getStatus()));
	}

	@PostMapping("/v1/addUpdateBanner")
	public ResponseEntity<BaseResponse> writeAndEditBanner(@Valid @RequestBody List<BannerDto> bannerDto)
			throws BusinessException, IOException, ServiceException, ParseException {

		log.info("Edit Banner Request start : {}", bannerDto);
		BannerResponseDto responseDto = bannerService.writeAndEditBanner(bannerDto);
		BaseResponse baseResponse = new BaseResponseBuilder().setBaseResponseWithStatusAndCodeAndData(
				HttpStatus.OK.name(), StatusCodeEnum.SUCCESS_CODE.getCode(), responseDto);

		return new ResponseEntity<>(baseResponse, HttpStatus.valueOf(baseResponse.getStatus()));
	}

}
