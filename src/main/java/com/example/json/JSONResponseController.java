package com.example.json;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@RestController
public class JSONResponseController {

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();

        Person passenger1 = new Person();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");

        Person passenger2 = new Person();
        passenger2.setFirstName("Some other name");

        Ticket ticket1 = new Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);

        Ticket ticket2 = new Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(400);

        List<Ticket> tickets1 = new ArrayList<>();
        tickets1.add(ticket1);

        List<Ticket> tickets2 = new ArrayList<>();
        tickets2.add(ticket2);

        Calendar departDate = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
        departDate.set(2017, Calendar.APRIL, 21, 14, 34);

        flight1.setDepartsOn(departDate.getTime());
        flight1.setTickets(tickets1);

        flight2.setDepartsOn(departDate.getTime());
        flight2.setTickets(tickets2);

        return Arrays.asList(flight1, flight2);
    }

    @GetMapping("/flights/flight")
    public Flight getFlight(){
        Flight flight = new Flight();
        Person passenger1 = new Person();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");


        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger1);
        ticket.setPrice(200);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        flight.setTickets(tickets);

        Calendar departDate = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
        departDate.set(2017, Calendar.APRIL, 21, 14, 34);
        flight.setDepartsOn(departDate.getTime());

        return flight;
    }


    @PostMapping(value = "/flights/tickets/total")
    public String postTickets(@RequestBody Flight flights) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Ticket> ticketList = flights.getTickets();
        int total = 0;
        for (Ticket ticket : ticketList) {
            total += ticket.getPrice();
        }
        return "{\n  \"result\": " + mapper.writeValueAsString(total) + "\n}";
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    public static class Person {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

//    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize
    public static class Flight {

        private Date departsOn;
        private List<Ticket> tickets;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonGetter("departs")
        public Date getDepartsOn() { return departsOn; }

        public void setDepartsOn(Date dateTime) { this.departsOn = dateTime; }

        public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

        public List<Ticket> getTickets() { return tickets; }
    }

//    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    public static class Ticket {
        private Person passenger;
        private int price;

        public void setPassenger(Person passenger) { this.passenger = passenger; }

        public void setPrice(int price) { this.price = price; }

        public Person getPassenger(){
            return passenger;
        }

        public int getPrice(){
            return price;
        }
    }

}
