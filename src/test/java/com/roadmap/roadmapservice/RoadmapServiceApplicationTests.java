package com.roadmap.roadmapservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.roadmap.roadmapservice.controller.RoadmapController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class RoadmapServiceApplicationTests {

	@Autowired
	private RoadmapController controller;
	 
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
