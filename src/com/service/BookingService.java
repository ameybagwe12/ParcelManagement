package com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.model.Booking;
import com.model.User;

public class BookingService {

    private static ArrayList<Booking> bookings = new ArrayList<>();

    public static void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public static Booking getBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    public static void initiateBooking(User currentUser, Scanner scanner) {
        // Display sender details
        System.out.println("------ Booking Service ------");
        System.out.println("Sender Details:");
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Address: " + currentUser.getAddress());
        System.out.println("Contact: " + currentUser.getMobileNumber());
        System.out.println();

        // Capture recipient details
        System.out.println("Enter Recipient Details:");
        System.out.print("Recipient Name: ");
        String recName = scanner.nextLine();

        System.out.print("Recipient Address: ");
        String recAddress = scanner.nextLine();

        System.out.print("Recipient Pin Code: ");
        String recPin = scanner.nextLine();

        System.out.print("Recipient Mobile Number: ");
        String recMobile = scanner.nextLine();

        // Capture parcel details
        System.out.println("\nEnter Parcel Details:");
        System.out.print("Parcel Weight (in grams): ");
        double parWeight = scanner.nextDouble();

        scanner.nextLine(); // Consume the newline character

        System.out.print("Parcel Contents Description: ");
        String parContentsDescription = scanner.nextLine();

        System.out.print("Delivery Type (Standard/Express): ");
        String parDeliveryType = scanner.nextLine();

        System.out.print("Packing Preference (Regular/Fragile): ");
        String parPackingPreference = scanner.nextLine();

        System.out.print("Pickup Time (HH:mm): ");
        String parPickupTime = scanner.nextLine();

        System.out.print("Dropoff Time (HH:mm): ");
        String parDropoffTime = scanner.nextLine();

        System.out.print("Parcel Current Status: ");
        String parcelStatus = scanner.nextLine();

        // Calculate service cost
        double serviceCost = calculateServiceCost(parWeight, parDeliveryType, parPackingPreference);

        // Capture payment time
        LocalDateTime paymentTime = LocalDateTime.now();
        LocalDate bookingDate = LocalDate.now();


        String bookingId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();
        Booking newBooking = new Booking(bookingId, currentUser.getName(), recName, recAddress, recPin,
                recMobile, parWeight, parContentsDescription, parDeliveryType,
                parPackingPreference, parPickupTime, parDropoffTime, serviceCost,
                paymentTime, parcelStatus, customerId, bookingDate);

        addBooking(newBooking);

        // Display Booking Summary
        System.out.println("\nBooking successful! Your Booking ID is: " + bookingId);

        // Display booking summary
        System.out.println("\n------ Booking Summary ------");
        System.out.println("Customer Id: "+ customerId);
        System.out.println("Sender Name: " + currentUser.getName());
        System.out.println("Recipient Name: " + recName);
        System.out.println("Recipient Address: " + recAddress);
        System.out.println("Recipient Mobile: " + recMobile);
        System.out.println("Parcel Weight: " + parWeight + " grams");
        System.out.println("Delivery Type: " + parDeliveryType);
        System.out.println("Packing Preference: " + parPackingPreference);
        System.out.println("Pickup Time: " + parPickupTime);
        System.out.println("Dropoff Time: " + parDropoffTime);
        System.out.println("Service Cost: â‚¹" + serviceCost);
        System.out.println("Payment Time: " + paymentTime);
        System.out.println("Parcel Status: " + parcelStatus);

        System.out.println("\nBooking successful!");


    }

    public static void viewInvoice(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                System.out.println("\n------ Booking Invoice ------");
                System.out.println(booking);
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    public static List<Booking> getOfficerPreviousBookings(String customerId, LocalDate startDate, LocalDate endDate) {
        return bookings.stream()
                .filter(booking -> booking.getCustomerId().equals(customerId) &&
                        (booking.getBookingDate().isAfter(startDate) || booking.getBookingDate().isEqual(startDate)) &&
                        (booking.getBookingDate().isBefore(endDate) || booking.getBookingDate().isEqual(endDate)))
                .sorted(Comparator.comparing(Booking::getBookingDate).reversed())
                .collect(Collectors.toList());
    }


    public static List<Booking> getCustomerPreviousBookings(String customerId) {
        return bookings.stream()
                .filter(booking -> booking.getCustomerId().equals(customerId))
                .sorted(Comparator.comparing(Booking::getBookingDate).reversed())
                .collect(Collectors.toList());
    }

    public static boolean updatePickupAndDropoff(String bookingId, String newPickupTime, String newDropoffTime) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                booking.updatePickupAndDropoffTimes(newPickupTime, newDropoffTime);
                return true; // Successfully updated
            }
        }
        return false; // Booking ID not found
    }

    public static boolean updateParcelStatus(String bookingId, String newStatus) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                booking.updateParcelStatus(newStatus);
                return true; // Successfully updated parcel status
            }
        }
        return false; // Booking ID not found
    }

    // Calculate service cost with random calculation
    private static double calculateServiceCost(double weight, String deliveryType, String packingPreference) {
        double baseRate = 50; // Base rate for standard delivery
        double costPerGram = 0.05; // Cost per gram
        double deliveryMultiplier = deliveryType.equalsIgnoreCase("Express") ? 2.0 : 1.0;
        double packingCost = packingPreference.equalsIgnoreCase("Fragile") ? 20.0 : 0.0;

        return (baseRate + (weight * costPerGram)) * deliveryMultiplier + packingCost;
    }
}
