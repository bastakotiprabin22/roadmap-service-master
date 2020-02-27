package com.roadmap.roadmapservice.model;

public class Route {

	private String origin;
	
	private String destination;

	public boolean skip;
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Route(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
		skip =false;
	}

	public Route() {
		
	}
		
}
