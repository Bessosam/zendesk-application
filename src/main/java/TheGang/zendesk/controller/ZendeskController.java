package TheGang.zendesk.controller;

import TheGang.zendesk.service.ZendeskService;
import org.springframework.web.bind.annotation.*;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class ZendeskController {

    private final Zendesk zendesk;
    private final ZendeskService zendeskService;

    public ZendeskController(Zendesk zendesk, ZendeskService zendeskService) {
        this.zendesk = zendesk;
        this.zendeskService = zendeskService;
    }

    @GetMapping("/problems")
    public List<Ticket> listTicketProblems() {
        List<Ticket> problemTickets = new ArrayList<>();
        try {
            Iterable<Ticket> tickets = zendesk.getTickets();
            for (Ticket ticket : tickets) {
                if (ticket.getPriority().equals("high")) {
                    problemTickets.add(ticket);
                }
            }
        } catch (Exception e) {

        }
        return problemTickets;
    }

    @GetMapping("/all")
    public List<Ticket> showMultipleTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        try {
            Iterable<Ticket> tickets = zendesk.getTickets();
            for (Ticket ticket : tickets) {
                allTickets.add(ticket);
            }
        } catch (Exception e) {

        }
        return allTickets;
    }

    @DeleteMapping("/deleteMultiple")
    public void deleteMultipleTickets(@RequestBody List<Long> ticketIds) {
        zendeskService.deleteMultipleTickets(ticketIds);
    }

    @GetMapping("/updateMultiple")
    public void updateMultipleTickets() {
        zendeskService.updateMultipleTickets();
    }

}




