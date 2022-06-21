package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeFlightComparator;
import ru.netology.repositiry.Repository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


public class ManagerTest {
    Repository repo = new Repository();
    Manager manager = new Manager();
    Ticket first = new Ticket(1, 350, "DME", "IST", 190);
    Ticket second = new Ticket(2, 260, "IST", "TBS", 165);
    Ticket third = new Ticket(3, 230, "IST", "TBS", 170);
    Ticket fourth = new Ticket(4, 1200, "IST", "SJO", 160);
    Ticket fifth = new Ticket(5, 250, "IST", "TBS", 160);
    Comparator<Ticket> comparator = new TicketByTimeFlightComparator();

    @BeforeEach
    public void setUp() {
        repo.save(first);
        repo.save(second);
        repo.save(third);
        repo.save(fourth);
        repo.save(fifth);
        manager.addTicket(first);
        manager.addTicket(second);
        manager.addTicket(third);
        manager.addTicket(fourth);
        manager.addTicket(fifth);
    }

    @Test
    public void shouldRepoSearchAllTickets() {
        Ticket[] actual = repo.findAll();
        Ticket[] expected = new Ticket[]{first, second, third, fourth, fifth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldRepoRemoveTicket() {
        repo.removeById(2);
        Ticket[] actual = repo.findAll();
        Ticket[] expected = new Ticket[]{first, third, fourth, fifth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldRepoFindTicket() {
        repo.findById(2);
        Ticket actual = repo.findById(3);
        Ticket expected = third;
        assertEquals(actual, expected);
    }

    @Test
    public void shouldManagerFindTicketsWithSortByTime() {

        Ticket[] actual = manager.searchTicketsByTime("IST", "TBS", comparator);
        Ticket[] expected = new Ticket[]{fifth, second, third};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldManagerFindTicketsWithSortByCost() {

        Ticket[] actual = manager.searchTicketsByCost("IST", "TBS");
        Ticket[] expected = new Ticket[]{third, fifth, second};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldManagerFindOneTicket() {

        Ticket[] actual = manager.searchTicketsByCost("IST", "SJO");
        Ticket[] expected = new Ticket[]{fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldManagerFindNothingTickets() {

        Ticket[] actual = manager.searchTicketsByCost("TBS", "IST");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(actual, expected);
    }
}