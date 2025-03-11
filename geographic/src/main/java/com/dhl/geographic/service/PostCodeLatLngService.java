package com.dhl.geographic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhl.geographic.dto.DistanceResponseDto;
import com.dhl.geographic.dto.PostcodeLatLngDto;
import com.dhl.geographic.model.Postcodelatlng;
import com.dhl.geographic.repository.PostCodeLatLngRepo;

@Service
public class PostCodeLatLngService {
	
	@Autowired
	private PostCodeLatLngRepo postCodeRepo;
	
	public DistanceResponseDto calDistance(String postCodeFrom, String postCodeTo) {
		Optional<Postcodelatlng> pFrom = postCodeRepo.findByPostcode(postCodeFrom);
		Optional<Postcodelatlng> pTo = postCodeRepo.findByPostcode(postCodeTo);
		
		if(pFrom.isEmpty() || pTo.isEmpty()) {
			throw new IllegalArgumentException("Postcode from or postcode to or both postcode not found");
		}
		
		double distanceResult = harDistance(pFrom.get().getLatitude(), pFrom.get().getLongitude(), pTo.get().getLatitude(), pTo.get().getLongitude());
		
		return new DistanceResponseDto(new PostcodeLatLngDto(pFrom.get().getPostcode(), pFrom.get().getLatitude(), pFrom.get().getLongitude()),
				new PostcodeLatLngDto(pTo.get().getPostcode(), pTo.get().getLatitude(), pTo.get().getLongitude()), distanceResult, "km");
	}
	
	private double harDistance(double latFrom, double longFrom, double latTo, double longTo) {
		final int radius = 6371;
		double latDist = Math.toRadians(latTo - latFrom);
		double longDist = Math.toRadians(longTo - longFrom);
		
		double distanceCal = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                + Math.cos(Math.toRadians(latFrom)) * Math.cos(Math.toRadians(latTo))
                * Math.sin(longDist / 2) * Math.sin(longDist / 2);
		
		double calSqrft = 2 * Math.atan2(Math.sqrt(distanceCal), Math.sqrt(1 - distanceCal));
		
		return radius * calSqrft;
	}

}
