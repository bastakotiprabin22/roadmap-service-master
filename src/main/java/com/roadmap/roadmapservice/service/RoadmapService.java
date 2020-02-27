package com.roadmap.roadmapservice.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.roadmap.roadmapservice.model.Route;

@Service
public class RoadmapService {

	private static final Logger logger = LoggerFactory.getLogger(RoadmapService.class);

	int size = 0;
	Route[] routes = new Route[100];
	Stack<Route> btStack = new Stack<Route>();
	String response = null;

	/**
	 * 
	 * Read the text file with cities pair using CSV reader and store it in a array
	 * of origin and destination cities
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	public String getConnectedCities(String origin, String destination) throws IOException {
		logger.info("Method to getConnected Cities routes");

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader("city.txt"));
			String[] road;
			while ((road = reader.readNext()) != null) {
				routes[size] = new Route(road[0].trim(), road[1].trim());
				size++;
				logger.info(" List of cities with direct routes : {} to {}", road[0], road[1]);
			}

			findRoute(origin, destination);

			if (btStack.size() != 0)
				route(destination);
			logger.info("Response in getConnectedCities : " + response);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}

		return response;

	}

	/**
	 * Used for printing the result in log
	 * 
	 * @param destination
	 */
	public void route(String destination) {
		Stack<Route> rev = new Stack<Route>();
		Route r;
		int num = btStack.size();

		for (int i = 0; i < num; i++)
			rev.push(btStack.pop());

		for (int i = 0; i < num; i++) {
			r = (Route) rev.pop();
			logger.info(r.getOrigin() + " --to-> ");
		}

		logger.info(destination);
	}

	/**
	 * To find the possible road way for the given origin and destination cities
	 * using recursive way and stack to store the new pair formed for connecting
	 * cities
	 * 
	 * @param origin
	 * @param destination
	 */
	public void findRoute(String origin, String destination) {

		logger.info("Finding the route for 2 different cities");
		Route direction;
		response = findDirectRoute(origin, destination);
		logger.info("Response is : " + response);
		if (response.equals("Yes")) {
			btStack.push(new Route(origin, destination));
			logger.info("Yes condition" + response);
			return;
		}
		logger.info("No direct road available so opting for connecting routes");

		direction = findConnectingRoute(origin);

		if (direction != null) {

			btStack.push(new Route(origin, destination));
			findRoute(direction.getDestination(), destination);

		} else if (btStack.size() > 0) {

			direction = (Route) btStack.pop();
			findRoute(direction.getOrigin(), direction.getDestination());
		}
	}

	/**
	 * Method to find the connecting roads by reading the pair of array string
	 * cities stored in route object
	 * 
	 * @param origin
	 * @return
	 */
	public Route findConnectingRoute(String origin) {

		for (int i = 0; i < size; i++) {
			if (routes[i].getOrigin().equalsIgnoreCase(origin) && !routes[i].skip) {
				Route route = new Route(routes[i].getOrigin(), routes[i].getDestination());
				routes[i].skip = true;
				return route;
			} else if (routes[i].getDestination().equalsIgnoreCase(origin) && !routes[i].skip) {
				Route route = new Route(routes[i].getDestination(), routes[i].getOrigin());
				routes[i].skip = true;
				return route;
			}
		}
		return null;
	}

	/**
	 * Direct road for the given origin city and destination city
	 * @param origin
	 * @param destination
	 * @return
	 */
	public String findDirectRoute(String origin, String destination) {
		logger.info("Find Direct route available method {} to {}", origin, destination);
		for (int i = size - 1; i > -1; i--) {
			if (((routes[i].getOrigin().equalsIgnoreCase(origin)
					&& routes[i].getDestination().equalsIgnoreCase(destination))
					|| (routes[i].getOrigin().equalsIgnoreCase(destination)
							&& routes[i].getDestination().equalsIgnoreCase(origin)))
					&& !routes[i].skip) {
				logger.info("Route found so returning Yes");
				routes[i].skip = true;
				return "Yes";
			}
		}

		return "No";

	}

}
