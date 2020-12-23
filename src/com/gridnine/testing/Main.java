package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights;

        flights = FlightBuilder.createFlights();

        showFlights(flights); // show original

        FiltersImpl filters = new FiltersImpl(flights);

        // select the required filters
        filters.excludeFlightsBeforeTheCurrentTime();
        filters.excludeFlightsWithADepartureDateBeforeArrivalDate();
        filters.excludeFlightsWithTheTotalGroundTimeMoreThan2Hours();

        showFlights(filters.getFlights()); // show result


    }

    private static void showFlights(List<Flight> flights){
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                System.out.println(segment.toString());
            }
            System.out.println();
        }
        System.out.println("----- end -----\n");
    }
}
