package com.example.java.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
public class  DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {


	SpringApplication.run(DemoApplication.class, args);
	log.info("Application Started");





	}

//    @Override
//    public void run(String... args) throws Exception {
//
//    }


//	@Override
//	public void run(String... args) throws Exception {
//		Ticket ticket1 = new Ticket("w2082104","Movies");
//		Ticket ticket2 = new Ticket("w2082101","Farcry2");
//		Ticket ticket3 = new Ticket("w2082104","Movies34");
//		Ticket ticket4 = new Ticket("w2082101","Farcry12");
//		Ticket ticket5 = new Ticket("w2082104","Movies45");
//		Ticket ticket6 = new Ticket("w2082101","Farcry25");
//		Ticket ticket7 = new Ticket("w2082104","Movies347");
//		Ticket ticket8 = new Ticket("w2082101","Farcry12");
//		Ticket ticket9 = new Ticket("w2082104","Movies");
//		Ticket ticket10 = new Ticket("w2082101","Farcry2");
//		Ticket ticket11 = new Ticket("w2082104","Movies34");
//		Ticket ticket12 = new Ticket("w2082101","Farcry12");
//		TicketPool ticketPool = new TicketPool();
//
//		Consumer consumer = new Consumer(ticketPool,0);
//		Producer producer = new Producer(ticketPool,"w2082104",0,0,ticket1);
//		Thread thread = new Thread(consumer,"Consumer Thread");
//		Thread thread2 = new Thread(producer,"Producer Thread");
//
//		thread.start();
//		thread2.start();







//		ticketPool.removeTicket();

	}




