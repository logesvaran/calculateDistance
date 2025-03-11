package com.dhl.geographic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhl.geographic.dto.DistanceResponseDto;
import com.dhl.geographic.service.PostCodeLatLngService;

@RestController
public class PostCodeLatLngController {
	
	@Autowired
	private PostCodeLatLngService postCodeService;
	
	@GetMapping("/distance")
	public DistanceResponseDto getDistance(@RequestParam String postCodeFrom, @RequestParam String postCodeTo) {
		return postCodeService.calDistance(postCodeFrom, postCodeTo);
	}

}
