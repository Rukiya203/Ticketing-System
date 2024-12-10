//package com.example.java.demo;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.UUID;
//
////@SpringBootApplication
//public class TestCli {
//    private static Configuration config;
////    private static TicketService ticketService;
//
//    private static TicketPool ticketPool2 = new TicketPool(null);
//    private static Gson gson = new Gson();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        // Initialize Spring context
////        ConfigurableApplicationContext context = SpringApplication.run(TestCli.class, args);
//
//        // Run CLI logic
//        runCLI();
//
//        // Close the context when done
////        context.close();
//    }
//
//    public static void runCLI() {
//        while (true) {
//            System.out.println("\nTicketing System CLI");
//            System.out.println("1. Add Configuration");
//            System.out.println("2. Add Ticket");
//            System.out.println("3. Show Tickets");
//            System.out.println("4. Simulate Fake Buyers and Producers");
//            System.out.println("5. Save Configuration to File");
//            System.out.println("6. Load Configuration from File");
//            System.out.println("7. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline character
//
//            switch (choice) {
//                case 1 -> addConfig();
////                case 2 -> addTicket();
////                case 3 -> showTickets();
//                case 4 -> simulateFakeUsers();
//                case 5 -> writeData();
////                case 6 -> readData();
//                case 7 -> {
//                    System.out.println("Exiting...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    private static void addConfig() {
//        System.out.print("Enter the total tickets: ");
//        int total = scanner.nextInt();
//        System.out.print("Enter the customer restriction: ");
//        int cusRestr = scanner.nextInt();
//        System.out.print("Enter the max ticket capacity: ");
//        int maxTicket = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline character
//
//        config = new Configuration(total,2,cusRestr,maxTicket,config.gId());
////        ticketPool2.setConfiguration(config);
//        System.out.println("Configuration registered successfully.");
//    }
//
////    private static void addTicket() {
////
////
////
////
//////        ticketPool2.setConfiguration(config);
////
////
////
////
//////        System.out.print("Enter Ticket ID: ");
//////        String ticketId = scanner.nextLine();
//////        System.out.print("Enter Ticket Name: ");
//////        String ticketName = scanner.nextLine();
//////
//////        Ticket ticket = new Ticket(ticketId, ticketName);
////        Producer producer = new Producer(total_v,UUID.randomUUID(),3,ticketPool2);
////        new Thread(producer).start();
////
////        Consumer consumer = new Consumer(ticketPool2,cusRestr,UUID.randomUUID());
////        new Thread(producer).start();
////
////
////
////        System.out.println("Ticket added successfully.");
////    }
//
////    private static void showTickets() {
////        System.out.println("Displaying available tickets...");
////        ticketPool2.displayTickets();
////    }
//
//    private static void simulateFakeUsers() {
//        System.out.print("Enter the total tickets: ");
//        int total = scanner.nextInt();
//        System.out.print("Enter the VENDOR RELEASE ");
//        int total_v = scanner.nextInt();
//        System.out.print("Enter the customer restriction: ");
//        int cusRestr = scanner.nextInt();
//        System.out.print("Enter the max ticket capacity: ");
//        int maxTicket = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline character
//
//        System.out.print("Enter number of producers: ");
//        int producerCount = scanner.nextInt();
//        System.out.print("Enter number of consumers: ");
//        int consumerCount = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        for (int i = 0; i < producerCount; i++) {
////            Ticket ticket = new Ticket("Ticket-" + i, "Event-" + i);
//            Producer producer = new Producer(total_v,UUID.randomUUID(),3,ticketPool2);
//            new Thread(producer).start();
//
//
//
//
//        }
//
//
//        for (int i = 0; i < consumerCount; i++) {
//            Consumer consumer = new Consumer(ticketPool2,cusRestr,UUID.randomUUID());
//            new Thread(consumer).start();
//
////        System.out.println("Simulation started with " + producerCount + " producers and " + consumerCount + " consumers.");
//        }
//    }
//
//    private static void readData() {
//        try (FileReader fileReader = new FileReader("config.json")) {
//            config = gson.fromJson(fileReader, Configuration.class);
//            if (config != null) {
//                System.out.println("Configuration loaded successfully.");
//                System.out.println("Total Tickets: " + config.getTotalTickets());
//            } else {
//                System.out.println("Configuration data is null or empty.");
//            }
//        } catch (IOException | JsonSyntaxException e) {
//            System.out.println("Error reading configuration data: " + e.getMessage());
//        }
//    }
//
//    private static void writeData() {
//        try (FileWriter fileWriter = new FileWriter("config.json")) {
//            gson.toJson(config, fileWriter);
//            System.out.println("Configuration saved successfully.");
//        } catch (IOException e) {
//            System.out.println("Error saving configuration data: " + e.getMessage());
//        }
//    }
//}
