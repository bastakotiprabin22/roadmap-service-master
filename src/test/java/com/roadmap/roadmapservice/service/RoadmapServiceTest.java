package com.roadmap.roadmapservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Stack;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.roadmap.roadmapservice.model.Route;

@RunWith(SpringRunner.class)
@WebMvcTest({RoadmapService.class})
public class RoadmapServiceTest {

	@MockBean
	private RoadmapService service;

	
	Route[] routes;
	
	Stack<Route> btStack;
	
	String response;
	@Before
	public void init() {
		routes = new Route[100];

		btStack = new Stack<Route>();
		
		routes[0] = new Route("Boston","New York");
		routes[1] = new Route("Trenton","Albany");
		routes[2] = new Route("Philadelphia","Newark");
		routes[3] = new Route("Newark","Boston");
		
	}
	
	@Test
	public void testgetConnectedCities() throws IOException {
		
		RoadmapService serv = new RoadmapService() {
			public void findRoute(String origin, String destination) {
				response = "Yes";
				return;
			}
		};
		assertEquals("Yes", serv.getConnectedCities("Boston", "New York"));
	}
	
	@Test
	public void testgetConnectedCitiesNo() throws IOException {
		
		RoadmapService serv = new RoadmapService() {
			public void findRoute(String origin, String destination) {
				response = "No";
				return;
			}
		};
		assertEquals("No", serv.getConnectedCities("Albany", "New York"));
	}
	
	@Test
	public void testfindDirectRouteYes() {
		RoadmapService serv = new RoadmapService() {
			
		};
		assertEquals("No",serv.findDirectRoute("Albany", "New York"));
	}
	
	@Test
	public void testfindConnectingRoute() {
		RoadmapService serv = new RoadmapService() {
			Route route = new Route("Boston","Philadelphia");
			
		};
		assertNull(serv.findConnectingRoute("Philadelphia"));
	}
	
	@Test
	public void testFindRoute() {
		RoadmapService serv = new RoadmapService() {
			public String findDirectRoute(String origin, String destination) {
				
				return "Yes";
			}
			public Route findConnectingRoute(String origin) {
				return routes[0];
			}
		};
		
		serv.findRoute("Boston", "Newark");
	}
}
