package springmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc.model.Ticket;
import springmvc.services.TicketService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/bus_tickets")
public class BusTicketsController {

    private TicketService ticketService;

    @GetMapping("/main")
    public String mainPage() {
        return "MainPage";
    }

    @GetMapping("show_ticket/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Ticket printSingleTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
}