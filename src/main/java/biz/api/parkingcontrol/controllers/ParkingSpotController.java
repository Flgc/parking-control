package biz.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.api.parkingcontrol.dtos.ParkingSpotDto;
import biz.api.parkingcontrol.models.ParkingSpotModel;
import biz.api.parkingcontrol.services.ParkingSpotService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	final ParkingSpotService parkingSpotService;

	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}
	
	@PostMapping												
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){ 
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel();		
		//Converter dto for model
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		//auto Setting date 
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));		
		return ResponseEntity.
				status(HttpStatus.CREATED).
				body(parkingSpotService.save(parkingSpotModel));		
	}
}
