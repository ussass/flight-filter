package com.gridnine.testing;

import java.util.List;

public interface Filters {

    List<Flight> getFlights();

    List<Flight> excludeFlightsBeforeTheCurrentTime();

    List<Flight> excludeFlightsWithADepartureDateBeforeArrivalDate();

    List<Flight> excludeFlightsWithTheTotalGroundTimeMoreThan2Hours();
}
