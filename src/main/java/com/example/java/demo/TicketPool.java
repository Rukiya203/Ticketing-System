//package com.example.java.demo;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//
//@Component
//public class TicketPool {
//
//
////    import { Injectable } from '@angular/core';
////import { Food } from '../../Shared/Models/Food';
////import { HttpClient } from '@angular/common/http';
////import { Observable } from 'rxjs';
////
////const BASIC_URL=["http://localhost:8080"];
////
////    @Injectable({
////            providedIn: 'root'
////            })
//
//    public BlockingQueue<Ticket> getBlockingDeque() {
//        return blockingDeque;
//    }
//    public int getSizeOfQueue(){
//        return blockingDeque.size();
//    }
////    export class FoodService {
////
////        constructor(private http:HttpClient) { }
////
////        postTicket(ticket:any):Observable<any>{
////            return this.http.post(BASIC_URL+"/api/v1/ticket",ticket);
//
//
//
//    private BlockingQueue<Ticket> blockingDeque;
//    private Event event;
//    private Configuration config;
//    private final TicketRepo2 ticketRepo;
//
//
////    private final  Configuration configuration;
//
//    public Configuration getConfig() {
//        return config;
//    }
//
//    public void setConfig(Configuration config) {
//        this.config = config;
//    }
//
//    public  TicketPool(TicketRepo2 ticketRepo2) {
//        this.ticketRepo= ticketRepo2;
//
//
//
//        this.blockingDeque = new ArrayBlockingQueue<>(250);
//    }
//    public synchronized void setConfiguration(Configuration configuration) {
//        this.config = configuration;
//        this.blockingDeque = new ArrayBlockingQueue<>(config.getMaxTicketCapacity());
//        System.out.println("Updated ticket pool capacity to: " + config.getMaxTicketCapacity());
//        Init();
//        System.out.println("Tickets available in the pool: " + blockingDeque.size());
//
//
//    }
//
//    public  synchronized void addTickets(Ticket ticket) {
//        try {
//            blockingDeque.put(ticket);
//            ticketRepo.save(ticket);
//            System.out.println("Tickets available in the pool: " + blockingDeque.size());
//            System.out.println("Thread name " + Thread.currentThread().getName() + " added ticket: " + ticket);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupted status
//            throw new RuntimeException("Thread was interrupted while adding a ticket", e);
//        }
//    }
//
//    public  synchronized void addTicketsForCli(Ticket ticket) {
//        try {
//            blockingDeque.put(ticket);
////            ticketRepo.save(ticket);
//            System.out.println("Tickets available in the pool: " + blockingDeque.size());
//            System.out.println("Thread name " + Thread.currentThread().getName() + " added ticket: " + ticket);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupted status
//            throw new RuntimeException("Thread was interrupted while adding a ticket", e);
//        }
//    }
//
//    @PostConstruct
//
//    public void Init(){
//        List<Ticket> ticketList = ticketRepo.findAll();
//
//        for(Ticket ticket:ticketList){
//            try {
//                blockingDeque.put(ticket);
//
//
//                System.out.println("Thread name " + Thread.currentThread().getName() + " added Database  tickets: " + ticket);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt(); // Restore interrupted status
//                throw new RuntimeException("Thread was interrupted while adding a ticket", e);
//            }
//
//        }
//
//    }
//
//    public synchronized Ticket removeTicketById(String ticket_id) {
//        for (Ticket ticket : blockingDeque) {
//            if (ticket.getTicket_id().equals(ticket_id)) {
//                // Ticket found, remove it from the queue
//                blockingDeque.remove(ticket);
//                ticketRepo.delete(ticket.getTicket_id());  // Delete the ticket from the database
//                System.out.println("Thread name " + Thread.currentThread().getName() + " removed ticket: " + ticket);
//                return ticket;  // Return the ticket
//            }
//        }
//        return null;  // If ticket with the given ID is not found, return null
//    }
//
//
//    public synchronized Ticket removeTicket() {
//        try {
//            System.out.println("Tickets available in the pool: " + blockingDeque.size());
//
//            Ticket ticket = blockingDeque.take();
////            ticketRepo.delete(ticket.getTicket_id());
//            System.out.println("Thread name " + Thread.currentThread().getName() + " removed ticket: " + ticket);
//            return ticket;
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupted status
//            throw new RuntimeException("Thread was interrupted while removing a ticket", e);
//        }
//    }
//    public synchronized List<Ticket> ticketList(){
//        return ticketRepo.findAll();
//    }
//
//// Uncomment if needed
////    public boolean isFull() {
////        return blockingDeque.remainingCapacity() == 0;
////    }
////
//    public int getCurrentCapacity() {
//        return blockingDeque.size();
//    }
//}

package com.example.java.demo;

import com.example.java.demo.model.Configuration;
import com.example.java.demo.model.Ticket;
import com.example.java.demo.service.TicketService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TicketPool {


    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    private BlockingQueue<Ticket> blockingQueue;
    private final Map<UUID, Ticket> ticketMap; // For quick ID lookups
    private final TicketService ticketService;
    private  Configuration config;

    public TicketPool(TicketService ticketService,Configuration config) {
        this.ticketService = ticketService;
        this.blockingQueue = new ArrayBlockingQueue<>(20);
        this.ticketMap = new ConcurrentHashMap<>();
        this.config=config;

    }

    public BlockingQueue<Ticket> getBlockingQueue() {
        return blockingQueue;
    }

    public int getQueueSize() {
        return blockingQueue.size();
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
        updateQueueCapacity(config.getMaxTicketCapacity());
    }

    public void updateQueueCapacity(int newCapacity) {
        BlockingQueue<Ticket> newQueue = new ArrayBlockingQueue<>(newCapacity);
        newQueue.addAll(blockingQueue); // Transfer existing tickets to the new queue
        blockingQueue = newQueue;
        logger.info("Updated ticket pool capacity to: {}", newCapacity);
    }

    @PostConstruct
    public void init() {
        List<Ticket> ticketsFromDb = ticketService.getAllTickets();
        ticketsFromDb.forEach(this::addTicketFromDb);
        logger.info("Initialized ticket pool with {} tickets from the database.", ticketsFromDb.size());
        System.out.println("ticket pool contains "+getCurrentCapacity());
        ticketList();
    }



    public synchronized void addTicket(Ticket ticket) {
        try {
            blockingQueue.put(ticket);
            ticketMap.put(ticket.getTicketId(), ticket); // Add to map for quick lookups
            ticketService.saveTicket(ticket);
            logger.info("Added ticket to pool: {}", ticket);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while adding ticket: {}", ticket, e);
            throw new RuntimeException("Thread interrupted while adding ticket", e);
        }
    }

//    public synchronized void addTicketFromCli(Ticket ticket) {
//        while(blockingQueue.size()>config.getMaxTicketCapacity()){
//            System.out.println("Ticket pool is full.");
//            break;
//        }
//        try {
//
//
//
//            blockingQueue.put(ticket);
//            System.out.println("tickets in the pool"+blockingQueue.size()+"\n");
//
////            ticketMap.put(ticket.getTicket_id(), ticket); // Add to map for quick lookups
////            ticketService.saveTicket(ticket);
//            logger.info("Added ticket to pool: {}", ticket);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            logger.error("Thread interrupted while adding ticket: {}", ticket, e);
//            throw new RuntimeException("Thread interrupted while adding ticket", e);
//        }
//    }

    private synchronized void addTicketFromDb(Ticket ticket) {
        try {
            blockingQueue.put(ticket);
            ticketMap.put(ticket.getTicketId(), ticket);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while adding ticket from DB: {}", ticket, e);
        }
    }

    public int getCurrentCapacity() {
        return blockingQueue.size();
    }

    public Ticket removeTicket() {
        try {

            Ticket ticket = blockingQueue.take();
            ticketMap.remove(ticket.getTicketId());
            logger.info("Removed ticket from pool: {}", ticket);
            return ticket;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            logger.error("Thread interrupted while removing ticket", e);
            throw new RuntimeException("Thread interrupted while removing ticket", e);
        }

    }
    public synchronized List <Ticket>  ticketList(){

        List<Ticket> Tickets = new ArrayList<>();
         for(Ticket ticket:blockingQueue){
           Tickets.add(ticket);
        }
         return Tickets;

    }

    public synchronized   Ticket removeTicketById(UUID ticketId) {
        Ticket ticket = ticketMap.remove(ticketId);
        if (ticket != null && blockingQueue.remove(ticket)) {
            ticketService.deleteTicketById(ticket);
            logger.info("Removed ticket by ID from pool: {}", ticket);
            return ticket;
        }
        logger.warn("Ticket with ID {} not found in pool.", ticketId);
        return null;
    }

    public synchronized List<Ticket> listTickets() {
        return ticketService.getAllTickets();
    }
}
