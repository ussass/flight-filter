package com.gridnine.testing;

import java.sql.Timestamp;
import java.util.*;

public class FiltersImpl implements Filters {

    private List<Flight> flights;

    FiltersImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public List<Flight> excludeFlightsBeforeTheCurrentTime() {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            boolean isRight = true;
            for (Segment segment : flight.getSegments()) {
                Date date = new Date();
                long departureTime = Timestamp.valueOf(segment.getDepartureDate()).getTime();

                if (departureTime < date.getTime()) {
                    isRight = false;
                }
            }
            if (isRight) result.add(flight);
        }
        flights = result;
        return flights;
    }

    @Override
    public List<Flight> excludeFlightsWithADepartureDateBeforeArrivalDate() {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            boolean isRight = true;
            for (Segment segment : flight.getSegments()) {
                long departureTime = Timestamp.valueOf(segment.getDepartureDate()).getTime();
                long arrivalTime = Timestamp.valueOf(segment.getArrivalDate()).getTime();

                if (departureTime > arrivalTime) {
                    isRight = false;
                }
            }
            if (isRight) result.add(flight);
        }
        flights = result;
        return flights;
    }

    @Override
    public List<Flight> excludeFlightsWithTheTotalGroundTimeMoreThan2Hours() {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            long timeOnGround = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                long arrivalTime = Timestamp.valueOf(flight.getSegments().get(i).getArrivalDate()).getTime();
                long nextDepartureTime = Timestamp.valueOf(flight.getSegments().get(i + 1).getDepartureDate()).getTime();
                timeOnGround += nextDepartureTime - arrivalTime;
            }
            if (timeOnGround <= 7200000) result.add(flight);
        }
        flights = result;
        return flights;
    }
}
