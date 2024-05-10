package TheGang.zendesk.service;

import org.springframework.stereotype.Service;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;

import java.util.*;

@Service
public class ZendeskService {

    private final Zendesk zendesk;

    public ZendeskService(Zendesk zendesk) {
        this.zendesk = zendesk;
    }

    public List<Ticket> listTicketProblems() {
        List<Ticket> problemTickets = getTickets();
        try {
            Iterable<Ticket> tickets = zendesk.getTickets();
            for (Ticket ticket : tickets) {

                if (ticket.getPriority().equals("high")) {
                    problemTickets.add(ticket);
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
        return problemTickets;
    }

    private static List<Ticket> getTickets() {
        List<Ticket> problemTickets = new ArrayList<>();
        return problemTickets;
    }

    public List<Ticket> showMultipleTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        try {
            Iterable<Ticket> tickets = zendesk.getTickets();
            for (Ticket ticket : tickets) {
                allTickets.add(ticket);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return allTickets;
    }
    public void deleteMultipleTickets(List<Long> ticketIds) {
        for (Long ticketId : ticketIds) {
            Ticket ticket = zendesk.getTicket(ticketId);
            if (ticket != null) {
                zendesk.deleteTicket(ticketId);
                System.out.println("Deleted Ticket ID: " + ticketId);
            } else {
                System.out.println("No ticket found with ID: " + ticketId);
            }
        }

    }

    public void updateMultipleTickets() {
        Set<Long> ticketIdsToUpdate = new HashSet<>(Arrays.asList(1L, 2L, 7L, 9L, 10L, 14L, 16L, 17L));
        Iterable<Ticket> allTickets = zendesk.getTickets();
        for (Ticket ticket : allTickets) {
            // Check if this ticket's ID is in our list to update
            if (ticketIdsToUpdate.contains(ticket.getId())) {
                if (ticketIdsToUpdate.contains(ticket.getId())) {
                    // Update the ticket's status and priority according to requirements
                    ticket.setStatus(Status.SOLVED);
                    ticket.setPriority(Priority.NORMAL);
                    zendesk.updateTicket(ticket);
                    System.out.println("Updated Ticket ID: " + ticket.getId());
                }
            }
            System.out.println("Ticket updates complete!");
        }
    }
}
