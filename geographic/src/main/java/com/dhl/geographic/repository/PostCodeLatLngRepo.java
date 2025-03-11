package com.dhl.geographic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhl.geographic.model.Postcodelatlng;

public interface PostCodeLatLngRepo extends JpaRepository<Postcodelatlng, Long> {
	Optional<Postcodelatlng> findByPostcode(String postcode);
}
