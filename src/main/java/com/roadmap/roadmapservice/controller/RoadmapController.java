package com.roadmap.roadmapservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roadmap.roadmapservice.service.RoadmapService;

@RestController
public class RoadmapController {

	@Autowired
	private RoadmapService service;

	@GetMapping("/connected")
	public ResponseEntity<String> getConnectedCities(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination) throws  IOException {
		String response = service.getConnectedCities(origin, destination);
		return ResponseEntity.ok().body(response);
	}

}
