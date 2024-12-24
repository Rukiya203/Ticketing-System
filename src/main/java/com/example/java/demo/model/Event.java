package com.example.java.demo.model;

import com.example.java.demo.CLI.TicketPool2;
import com.example.java.demo.TicketPool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Event {
    private final List<Event5> eventList;
    public int getTicketConsumed() {
        return ticketConsumed;
    }

    public void setTicketConsumed(int ticketConsumed) {
        this.ticketConsumed = ticketConsumed;
    }

    private int date;
    private TicketPool2 ticketPool2;
    private Configuration configuration;
    private String event_name;
    private int  ticketConsumed=0;
    private String location;
    private String ticket_name;
    private final Map<UUID ,Ticket> eventMap;
    private List <Ticket> ticketList;
    private static Event5 event5;

    private UUID event_id;


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Event(Configuration configuration, String eventName, String location, String ticketName) {
        this.eventList = Collections.synchronizedList(new ArrayList<>());
        this.configuration=configuration;
        this.event_name = eventName;
        this.location = location;
        this.ticket_name = ticketName;
        this.eventMap = new ConcurrentHashMap<>();
        this.event_id = UUID.randomUUID();
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
    }

    public TicketPool2 getTicketPool2() {
        return ticketPool2;
    }

    public void setTicketPool2(TicketPool2 ticketPool2) {
        this.ticketPool2 = ticketPool2;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void addEvent(){
        event5 = new Event5("colombo");

        eventList.add(event5);
        System.out.println("event added "+event5.getEventId() +"own pool size "+event5.getTicketPoolSize());
    }

    public void printEventList() {
        if (eventList.isEmpty()) {
            System.out.println("The event list is empty.");
        } else {
            System.out.println("Events in the list:");
            synchronized (eventList) {
                for (Event5 event : eventList) {
                    System.out.println(event);
                }
            }
        }
    }

    public void  addTFEvent(UUID event_id){
        Ticket ticket = null;
        for(Event5 event51 :eventList){
            if(event51.getEventId().equals(event_id)){

                for(int i=0;i<configuration.getMaxTicketCapacity();i++){
                    ticket = new Ticket(UUID.randomUUID(),"ggg","vgggg","vffggg");


                    event5.getTicketPool().add(ticket);
                    System.out.println("tickets added ");


//            TicketPool2 ticketPool21 = new TicketPool2(configuration);

//            ticketPool21.addTicket(ticket);

                    System.out.println("Event id "+event_id);


                }
        }else{
                System.out.println("naha yako");
                break;
            }

//        for(int i=0;i<configuration.getMaxTicketCapacity();i++){
//            ticket = new Ticket(UUID.randomUUID(),"ggg","vgggg","vffggg");
//
//
//            event5.getTicketPool().add(ticket);
//
////            TicketPool2 ticketPool21 = new TicketPool2(configuration);
//
////            ticketPool21.addTicket(ticket);
//
//            System.out.println("Event id "+event_id);
//




        }
//        eventList.add(event_id);


//        ticketPool2.allTickts();
//        System.out.println(ticket.getEventName());


    }

    public Ticket removeTFEvent(UUID event_id){
        Ticket ticket = null;

        for(Event5 event51 :eventList){

            if(event51.getEventId().equals(event_id)){
                System.out.println(" event identifyed");

                 ticket = event51.getTicketPool().remove(0);
            }
            return ticket;

        }


        return null;

    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }


    public UUID getId() {
       return  event5.getEventId();
    }
}
