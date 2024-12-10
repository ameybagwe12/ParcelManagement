package com.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.model.Booking;
import com.model.User;
import com.service.*;

public class Menu {

    // Display menu after logging in
    public static void displayMenu(Scanner scanner, User loggedInUser) {
        // Condition to call role based menu function
        if (loggedInUser.getRole().equalsIgnoreCase("Customer")) {
            showCustomerMenu(scanner, loggedInUser);
        } else if (loggedInUser.getRole().equalsIgnoreCase("Officer")) {
            showOfficerMenu(scanner, loggedInUser);
        } else {
            System.out.println("Invalid role. Please contact support.");
        }
    }

    // Customer menu
    private static void showCustomerMenu(Scanner scanner, User customerMenu) {
        int choice;

        do {
            System.out.println("------ Customer Menu ------");
            System.out.println("1. Home");
            System.out.println("2. Booking Service");
            System.out.println("3. Tracking");
            System.out.println("4. Previous Booking");
            System.out.println("5. Contact Support");
            System.out.println("6. View Invoice");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: System.out.println("Welcome to Home Page!");
                    break;
                // Booking a service / parcel
                case 2: System.out.println("Proceeding to Booking Service...");
                    BookingService.initiateBooking(customerMenu, scanner);
                    break;
                // Tracking status of booking
                case 3: System.out.println("Navigating to Tracking Screen...");
                    System.out.print("Enter your Booking ID: ");
                    String bookingID = scanner.nextLine();
                    Booking booking = BookingService.getBookingById(bookingID);

                    if (booking == null || !booking.getSenderName().equals(customerMenu.getName())) {
                        System.out.println("No booking found with the given ID for the current user.");
                    } else {
                        System.out.println("\n------ Booking Details ------");
                        System.out.println(booking);
                    }
                    break;
                // Check booking history of customer
                case 4: System.out.println("Navigating to Previous Booking...");
                    System.out.print("Enter CustomerID: ");
                    String custId = scanner.nextLine();
                    viewCustomerPreviousBookings(custId);

                    break;
                case 5: System.out.println("Redirecting to Support Page...");
                    break;
                // View invoice of booking done
                case 6:
                    System.out.print("Enter Booking ID: ");
                    String bookingId = scanner.nextLine();
                    BookingService.viewInvoice(bookingId);
                    break;
                case 7: System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // Officer menu
    private static void showOfficerMenu(Scanner scanner, User officerMenu) {
        int choice;
        do {
            System.out.println("------ Officer Menu ------");
            System.out.println("1. Home");
            System.out.println("2. Tracking");
            System.out.println("3. Delivery Status");
            System.out.println("4. Pickup Scheduling");
            System.out.println("5. Previous Booking");
            System.out.println("6. Booking Service");
            System.out.println("7. View Invoice");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: System.out.println("Welcome to Home Page!");
                    break;
                case 2: System.out.println("Navigating to Tracking Screen...");
                    System.out.print("Enter your Booking ID: ");
                    String bookingId = scanner.nextLine();
                    Booking booking = BookingService.getBookingById(bookingId);

                    if (booking == null || !booking.getSenderName().equals(officerMenu.getName())) {
                        System.out.println("No booking found with the given ID for the current user.");
                    } else {
                        System.out.println("\n------ Booking Details ------");
                        System.out.println(booking);
                    }
                    break;
                case 3: System.out.println("Navigating to Delivery Status...");
                    updateDeliveryStatus(scanner);
                    break;
                case 4: System.out.println("Navigating to Pickup Scheduling...");
                    updatePickupSchedule(scanner);
                    break;
                case 5: System.out.print("Navigating to Previous Booking...Enter CustomerID: ");
                    viewOfficerPreviousBookings(scanner);

                    break;
                case 6: System.out.println("Navigating to Booking Service...");
                    BookingService.initiateBooking(officerMenu, scanner);
                    break;
                case 7:
                    System.out.print("Enter Booking ID: ");
                    String bookingID = scanner.nextLine();
                    BookingService.viewInvoice(bookingID);
                    break;
                case 8: System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // Get customer booking history form booking service
    private static void viewCustomerPreviousBookings(String customerId) {
        List<Booking> previousBookings = BookingService.getCustomerPreviousBookings(customerId);

        if (previousBookings.isEmpty()) {
            System.out.println("No previous bookings found.");
            return;
        }

        System.out.println("\n------ Previous Bookings ------");
        System.out.printf("%-15s %-15s %-15s %-20s %-30s %-10s %-10s\n",
                "Customer ID", "Booking ID", "Booking Date", "Receiver Name", "Delivered Address", "Amount", "Status");
        System.out.println("Customer ID\tBooking ID\tBooking Date\tReceiver Name\tDelivered Address\tAmount\tStatus");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Booking booking : previousBookings) {
            System.out.println(booking.getCustomerId()+"\t"+booking.getBookingId()+"\t"+booking.getBookingDate()+"\t"+booking.getReceiverName()+"\t"+booking.getReceiverAddress()+"\t"+booking.getServiceCost()+"\t"+booking.getParcelStatus());
        }
    }

    // Get officer booking history form booking service
    private static void viewOfficerPreviousBookings(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        List<Booking> filteredBookings = BookingService.getOfficerPreviousBookings(customerId, startDate, endDate);

        if (filteredBookings.isEmpty()) {
            System.out.println("No bookings found for the given criteria.");
            return;
        }

        System.out.println("\n------ Filtered Bookings ------");
        System.out.println("Customer ID\tBooking ID\tBooking Date\tReceiver Name\tDelivered Address\tAmount\tStatus");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Booking booking : filteredBookings) {
            System.out.println(booking.getCustomerId()+"\t"+booking.getBookingId()+"\t"+booking.getBookingDate()+"\t"+booking.getReceiverName()+"\t"+booking.getReceiverAddress()+"\t"+booking.getServiceCost()+"\t"+booking.getParcelStatus());
        }
    }

    // Update pickup schedule for officer

    private static void updatePickupSchedule(Scanner scanner) {
        System.out.print("Enter the Booking ID: ");
        String bookingId = scanner.nextLine();

        Booking booking = BookingService.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("No booking found with the given ID.");
            return;
        }

        // Display booking details
        System.out.println("\n------ Booking Details ------");
        System.out.println("Booking ID       : " + booking.getBookingId());
        System.out.println("Full Name        : " + booking.getSenderName());
        System.out.println("Recipient Name   : " + booking.getReceiverName());
        System.out.println("Recipient Address: " + booking.getReceiverAddress());
        System.out.println("Date of Booking  : " + booking.getPaymentTime());
        System.out.println("Pickup Time      : " + booking.getPickupTime());
        System.out.println("Dropoff Time     : " + booking.getDropoffTime());
        System.out.println("Current Status   : " + booking.getParcelStatus());


        // Get new pickup and dropoff times
        System.out.print("\nEnter new Pickup Time (e.g., 2024-12-03T15:00): ");
        String newPickupTime = scanner.nextLine();

        System.out.print("Enter new Dropoff Time (e.g., 2024-12-03T18:00): ");
        String newDropoffTime = scanner.nextLine();

        // Update times
        if (BookingService.updatePickupAndDropoff(bookingId, newPickupTime, newDropoffTime)) {
            System.out.println("Pickup and Dropoff times updated successfully.");
        } else {
            System.out.println("Failed to update times. Please try again.");
        }
    }

    // Update delivery status for officer
    private static void updateDeliveryStatus(Scanner scanner) {
        System.out.print("Enter the Booking ID: ");
        String bookingId = scanner.nextLine();

        Booking booking = BookingService.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("No booking found with the given ID.");
            return;
        }

        // Display booking details
        System.out.println("\n------ Booking Details ------");
        System.out.println("Booking ID       : " + booking.getBookingId());
        System.out.println("Full Name        : " + booking.getSenderName());
        System.out.println("Recipient Name   : " + booking.getReceiverName());
        System.out.println("Recipient Address: " + booking.getReceiverAddress());
        System.out.println("Date of Booking  : " + booking.getPaymentTime());
        System.out.println("Current Status   : " + booking.getParcelStatus());

        // Provide new status options
        System.out.println("\n------ Update Status ------");
        System.out.println("1. Booked");
        System.out.println("2. In Transit");
        System.out.println("3. Delivered");
        System.out.println("4. Returned");
        System.out.print("Enter the corresponding number for the new status: ");

        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        String newStatus;

        // Validate status choice
        switch (statusChoice) {
            case 1:
                newStatus = "Booked";
                break;
            case 2:
                newStatus = "In Transit";
                break;
            case 3:
                newStatus = "Delivered";
                break;
            case 4:
                newStatus = "Returned";
                break;
            default:
                System.out.println("Invalid status choice. Please try again.");
                return;
        }

        // Update the status in booking service
        if (BookingService.updateParcelStatus(bookingId, newStatus)) {
            System.out.println("Parcel status updated successfully to: " + newStatus);
        } else {
            System.out.println("Failed to update parcel status. Please try again.");
        }
    }
}
