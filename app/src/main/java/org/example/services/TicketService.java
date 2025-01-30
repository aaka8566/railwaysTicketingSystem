package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TicketService {

    private User user;

    private Ticket ticket;

    private List<Ticket> ticketList;

    private List<User> travellers;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String TICKETS_PATH = "app/src/main/java/org/example/localDB/tickets.json";

    private static final String USERS_PATH = "app/src/main/java/org/example/localDB/users.json";

    public TicketService(){
        try{
            this.ticketList = loadTickets();
        }catch (IOException e){
            System.out.println("Error loading tickets: "+e.getMessage());
        }
    }

//    public void saveTicketsToUsersDB() throws IOException {
//        File users=new File(USERS_PATH);
//        this.travellers=loadUsers();
//        File ticketPath=new File(TICKETS_PATH);
//        this.ticketList=loadTickets();
//        for(Ticket ticket: ticketList){
//            Optional<User> optionalUser=travellers.stream()
//                    .filter(user -> user.getUserId().equals(ticket.getUserId()))
//                    .findFirst();
//
//            optionalUser.ifPresent(user -> user.getTicketsBooked().add(ticket));
//        }
//
//        objectMapper.writeValue(users,travellers);
//    }

    public void bookTicket(Ticket ticket) throws IOException {
        File tickets=new File(TICKETS_PATH);
        ticketList = loadTickets();
        ticketList.add(ticket);
        objectMapper.writeValue(tickets,ticketList);
        File users=new File(USERS_PATH);
        travellers = loadUsers();
        Optional<User> optionalUser=travellers.stream()
                .filter(user -> user.getUserId().equals(ticket.getUserId()))
                .findFirst();
        optionalUser.ifPresent(user -> user.getTicketsBooked().add(ticket));
    }

    private List<User> loadUsers() throws IOException {
        File users=new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    private List<Ticket> loadTickets() throws IOException {
        File tickets = new File(TICKETS_PATH);
        return objectMapper.readValue(tickets, new TypeReference<List<Ticket>>() {});
    }
}
