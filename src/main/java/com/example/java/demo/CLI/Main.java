package com.example.java.demo.CLI;

import com.example.java.demo.model.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Configuration configuration2;
    private static volatile boolean running = false;
    private static final String CONFIG_FILE = "config80.json";
    private static final List<Thread> threadList = new ArrayList<>();
    private static final Queue<Configuration>CONFIGURATION_QUEUE= new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean configurationLoaded = false;

        System.out.println("========================================");
        System.out.println("     🎟️ REAL-TIME EVENT TICKETING SYSTEM 🎟️");
        System.out.println("========================================\n");

        // Prompt to load a previously saved configuration
        System.out.println("🔄 Do you want to load the previously saved configuration? (yes/no): ");
        String loadConfig = scanner.nextLine().trim().toLowerCase();

        if (loadConfig.equals("yes")) {
            configurationLoaded = readData();
            if (configurationLoaded) {
                System.out.println("\n✅ Configuration loaded successfully:");
                displayConfiguration(configuration2);
            } else {
                System.out.println("\n⚠️ No valid configuration found or file is empty. Proceeding to create a new configuration.");
            }
        }

        if (!configurationLoaded) {
            System.out.println("\n📋 Let's create a new configuration:");

            int totalTickets = getValidInput("🔢 Enter total number of tickets to produce: ");
            int ticketReleaseRate = getValidInput("⏱️ Enter ticket release rate (tickets per second): ");
            int customerRetrievalRate = getValidInput("⏱️ Enter customer retrieval rate (tickets per second): ");
            int maxTicketCapacity = getValidInput("🛑 Enter maximum ticket pool capacity: ");
            int numProducers = getValidInput("👨‍💻 Enter the number of producers: ");
            int numConsumers = getValidInput("👥 Enter the number of consumers: ");

            // Create a new configuration
            configuration2 = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numProducers, numConsumers);

            System.out.print("\n💾 Do you want to save this configuration? (yes/no): ");
            String saveConfig = scanner.nextLine().trim().toLowerCase();

            if (saveConfig.equals("yes")) {
                writeData(configuration2);
            }
        }

        Menu();

//        System.out.println("\n🚀 Starting the ticket system management...\n");
//        TicketPool2 ticketPool = new TicketPool2(configuration2);
//
//        while (true) {
//            System.out.println("\n💻 Enter a command (start, stop, exit): ");
//            String command = scanner.nextLine().trim().toLowerCase();
//
//            switch (command) {
//                case "start":
//                    if (running) {
//                        System.out.println("⚠️ Simulation is already running!");
//                    } else {
//                        running = true;
//                        System.out.println("\n🚀 Starting the simulation...");
//
//                        for (int i = 1; i <= configuration2.getProducerCount(); i++) {
//                            Thread producer = new Thread(new Vendor(ticketPool, configuration2.getTicketReleaseRate()), "Producer-" + i);
//                            producer.start();
//                            threadList.add(producer);
//                            System.out.println("✅ Producer-" + i + " started.");
//                        }
//
//                        for (int i = 1; i <= configuration2.getConsumerCount(); i++) {
//                            Thread consumer = new Thread(new Consumer(ticketPool, configuration2.getCustomerRetrievalRate()), "Consumer-" + i);
//                            consumer.start();
//                            threadList.add(consumer);
//                            System.out.println("✅ Consumer-" + i + " started.");
//                        }
//                    }
//                    break;
//
//                case "s":
//                    if (!running) {
//                        System.out.println("⚠️ Simulation is not currently running!");
//                    } else {
//                        running = false;
//                        System.out.println("🛑 Stopping the simulation...");
//                        for (Thread thread : threadList) {
//                            thread.interrupt();
//                        }
//                        threadList.clear();
//                        displayFinalStatus(ticketPool);
//
//
//
//
//            }
//                    break;
//
//                case "exit":
//                    System.out.println("❌ Exiting the program...");
//                    displayFinalStatus(ticketPool);
//                    System.exit(0);
//
//                default:
//                    System.out.println("⚠️ Invalid command. Please enter 'start', 'stop', or 'exit'.");
//            }
//        }
    }

    public static int getValidInput(String message) {
        int value;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print(message);
                value = scanner.nextInt();

                if (value > 0) {
                    return value;
                } else {
                    System.out.println("❌ Value must be greater than 0. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }
    }

    private static boolean readData() {
        System.out.println("🔄 Attempting to read configuration from file...");
        try (FileReader fileReader = new FileReader(CONFIG_FILE)) {
            Configuration[] configurations = gson.fromJson(fileReader, Configuration[].class); // Read as array
            if (configurations != null) {
                CONFIGURATION_QUEUE.addAll(Arrays.asList(configurations)); // Populate the queue
                configuration2 = CONFIGURATION_QUEUE.peek(); // Use the first configuration as current
                return true;
            }
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("❌ Error reading configuration data: " + e.getMessage());
        }
        return false;
    }


    private static void writeData(Configuration newConfig) {
        System.out.println("💾 Saving configuration...");


        readData();


        CONFIGURATION_QUEUE.add(newConfig);


        try (FileWriter fileWriter = new FileWriter(CONFIG_FILE)) { // Overwrite the file
            gson.toJson(new ArrayList<>(CONFIGURATION_QUEUE), fileWriter); // Convert Queue to List
            System.out.println("✅ Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving configuration data: " + e.getMessage());
        }
    }


    private static void displayConfiguration(Configuration configuration) {
        System.out.println("----------------------------------------");
        System.out.println("       🛠️ CONFIGURATION DETAILS");
        System.out.println("----------------------------------------");
        System.out.println("🎫 Total Tickets: " + configuration.getTotalTickets());
        System.out.println("⏱️ Ticket Release Rate: " + configuration.getTicketReleaseRate() + " tickets/second");
        System.out.println("⏱️ Customer Retrieval Rate: " + configuration.getCustomerRetrievalRate() + " tickets/second");
        System.out.println("🛑 Maximum Ticket Pool Capacity: " + configuration.getMaxTicketCapacity());
        System.out.println("👨‍💻 Number of Producers: " + configuration.getProducerCount());
        System.out.println("👥 Number of Consumers: " + configuration.getConsumerCount());
        System.out.println("----------------------------------------");
    }

    private static void displayFinalStatus(TicketPool2 ticketPool) {
        System.out.println("\n📊 SYSTEM STATUS REPORT:");
        System.out.println("----------------------------------------");
        System.out.println("🎫 Tickets Produced: " + ticketPool.getTicketsProduced());
        System.out.println("🎫 Tickets Consumed: " + ticketPool.getTicketsConsumed());
        System.out.println("🎫 Tickets Remaining in Pool: " + ticketPool.getRemainingTickets());
        System.out.println("----------------------------------------");
        System.out.println("✅ Simulation Complete. Thank you for using the system!");
    }

    public static void Menu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n🚀 Starting the ticket system management...\n");
        TicketPool2 ticketPool = new TicketPool2(configuration2);

        while (true) {
            System.out.println("\n💻 Enter a command (start, stop, exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (running) {
                        System.out.println("⚠️ Simulation is already running!");
                    } else {
                        running = true;
                        System.out.println("\n🚀 Starting the simulation...");

                        for (int i = 1; i <= configuration2.getProducerCount(); i++) {
                            Thread producer = new Thread(new Vendor(ticketPool, configuration2.getTicketReleaseRate()), "Producer-" + i);
                            producer.start();
                            threadList.add(producer);
                            System.out.println("✅ Producer-" + i + " started.");
                        }

                        for (int i = 1; i <= configuration2.getConsumerCount(); i++) {
                            Thread consumer = new Thread(new Consumer(ticketPool, configuration2.getCustomerRetrievalRate()), "Consumer-" + i);
                            consumer.start();
                            threadList.add(consumer);
                            System.out.println("✅ Consumer-" + i + " started.");
                        }
                    }
                    break;

                case "s":
                    if (!running) {
                        System.out.println("⚠️ Simulation is not currently running!");
                    } else {
                        running = false;
                        System.out.println("🛑 Stopping the simulation...");
                        for (Thread thread : threadList) {
                            thread.interrupt();
                        }
                        threadList.clear();
                        displayFinalStatus(ticketPool);




                    }
                    break;

                case "exit":
                    System.out.println("❌ Exiting the program...");
                    displayFinalStatus(ticketPool);
                    System.exit(0);

                default:
                    System.out.println("⚠️ Invalid command. Please enter 'start', 'stop', or 'exit'.");
            }
        }

    }
}
