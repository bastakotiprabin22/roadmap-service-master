package com.roadmap.roadmapservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.roadmap.roadmapservice.service.RoadmapService;


@RunWith(SpringRunner.class)
@WebMvcTest({RoadmapController.class})
public class RoadmapControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoadmapService service;
	
	@Test
	public void shouldReturn_200_Yes_Message() throws Exception {
		String response = "Yes";
		when(service.getConnectedCities("Boston", "New York")).thenReturn(response);
		this.mockMvc.perform(get("/connected").param("origin", "Boston").param("destination", "New York")).andDo(print()).andExpect(status()
				.isOk()).andExpect(content().string("Yes"));
	}
	
	@Test
	public void shouldReturn_400_Message() throws Exception {
		this.mockMvc.perform(get("/connected").param("origin", "Boston")).andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldReturn_200_No_Message() throws Exception {
		String response = "No";
		when(service.getConnectedCities("Boston", "Albany")).thenReturn(response);
		this.mockMvc.perform(get("/connected").param("origin", "Boston").param("destination", "Albany")).andDo(print()).andExpect(status()
				.isOk()).andExpect(content().string("No"));
	}
	
	@Test
	public void shouldReturn_404_No_Message() throws Exception {
		this.mockMvc.perform(get("/con").param("origin", "Boston").param("destination", "Albany")).andDo(print()).andExpect(status()
				.isNotFound()).andExpect(content().string(""));
	}
}

