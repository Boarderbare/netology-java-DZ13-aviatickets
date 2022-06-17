package ru.netology.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int cost;
    private String airportDeparture;
    private String airportArrival;
    private int timeFlight;


    public Ticket(int id, int cost, String airportDeparture, String airportArrival, int timeFlight) {
        this.id = id;
        this.cost = cost;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
        this.timeFlight = timeFlight;
    }

    @Override
    public int compareTo(Ticket t) {
        if (cost < t.getCost()) {
            return -1;
        }
        if (cost > t.getCost()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && cost == ticket.cost && timeFlight == ticket.timeFlight && Objects.equals(airportDeparture, ticket.airportDeparture) && Objects.equals(airportArrival, ticket.airportArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, airportDeparture, airportArrival, timeFlight);
    }
}
