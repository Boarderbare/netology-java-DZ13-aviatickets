package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeFlightComparator;
import ru.netology.repositiry.Repository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Manager {
    private String from;
    private String to;
    Repository repo = new Repository();

//    public Manager(Repository repo) {
//        this.repo = repo;
//    }
//    public Manager(String from, String to) {
//        this.from = from;
//        this.to = to;
//    }
//    public Manager() {
//    }

    public void addTicket(Ticket ticket) {
        repo.save(ticket);
    }

    public Ticket[] getAll() {
        return repo.findAll();
    }

    public Ticket[] searchTicketsByTime(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] tickets = getAll();
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : tickets) {
            if ((from == ticket.getAirportDeparture()) && (to == ticket.getAirportArrival())) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < tmp.length - 1; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public Ticket[] searchTicketsByCost(String from, String to) {
        Ticket[] tickets = getAll();
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : tickets) {
            if ((from == ticket.getAirportDeparture()) && (to == ticket.getAirportArrival())) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < tmp.length - 1; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
