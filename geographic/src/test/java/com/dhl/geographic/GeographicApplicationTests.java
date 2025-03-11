package com.dhl.geographic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dhl.geographic.dto.DistanceResponseDto;
import com.dhl.geographic.model.Postcodelatlng;
import com.dhl.geographic.repository.PostCodeLatLngRepo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeographicApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostCodeLatLngRepo postcodeRepo;

	@Test
	void testCalculateDistance() {
		
		//postcodeRepo.save(new Postcodelatlng("AB123LG", 53.122165, -3.114848));
		//postcodeRepo.save(new Postcodelatlng("AB523LG", 54.122165, -2.114848));
		
		ResponseEntity<DistanceResponseDto> response = restTemplate.getForEntity("/distance?postCodeFrom=AB10 1XG&postCodeTo=AB10 6RN", DistanceResponseDto.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("AB10 1XG", response.getBody().postcodeFrom().poscode());
        assertEquals("AB10 6RN", response.getBody().postcodeTo().poscode());
        assertEquals("km", response.getBody().unit());
		
	}

}
