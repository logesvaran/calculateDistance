package com.dhl.geographic.dto;

public record DistanceResponseDto(PostcodeLatLngDto postcodeFrom, PostcodeLatLngDto postcodeTo, double distance, String unit) {}
