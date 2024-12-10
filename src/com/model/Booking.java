package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPin() {
        return receiverPin;
    }

    public void setReceiverPin(String receiverPin) {
        this.receiverPin = receiverPin;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public double getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(double parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public String getParcelContentsDescription() {
        return parcelContentsDescription;
    }

    public void setParcelContentsDescription(String parcelContentsDescription) {
        this.parcelContentsDescription = parcelContentsDescription;
    }

    public String getPackingPreference() {
        return packingPreference;
    }

    public void setPackingPreference(String packingPreference) {
        this.packingPreference = packingPreference;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    private String bookingId;
    private String parcelStatus;
    private String senderName;
    private String receiverName;
    private String receiverAddress;
    private String receiverPin;
    private String receiverMobile;
    private double parcelWeight;
    private String parcelContentsDescription;
    private String deliveryType;
    private String packingPreference;
    private String pickupTime;
    private String dropoffTime;
    private double serviceCost;
    private LocalDateTime paymentTime;
    private String customerId;
    private LocalDate bookingDate;

    // Booking Constructor
    public Booking(String bookingId, String senderName, String receiverName, String receiverAddress,
                   String receiverPin, String receiverMobile, double parcelWeight,
                   String parcelContentsDescription, String deliveryType, String packingPreference,
                   String pickupTime, String dropoffTime, double serviceCost, LocalDateTime paymentTime, String parcelStatus, String customerId, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPin = receiverPin;
        this.receiverMobile = receiverMobile;
        this.parcelWeight = parcelWeight;
        this.parcelContentsDescription = parcelContentsDescription;
        this.deliveryType = deliveryType;
        this.packingPreference = packingPreference;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.serviceCost = serviceCost;
        this.paymentTime = paymentTime;
        this.parcelStatus = parcelStatus;
        this.customerId = customerId;
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nCustomer ID: " + customerId+
                "\nReceiver Name: " + receiverName +
                "\nReceiver Address: " + receiverAddress +
                "\nReceiver Pin: " + receiverPin +
                "\nReceiver Mobile: " + receiverMobile +
                "\nParcel Weight: " + parcelWeight + " grams" +
                "\nParcel Contents Description: " + parcelContentsDescription +
                "\nDelivery Type: " + deliveryType +
                "\nPacking Preference: " + packingPreference +
                "\nPickup Time: " + pickupTime +
                "\nDropoff Time: " + dropoffTime +
                "\nService Cost: â‚¹" + serviceCost +
                "\nPayment Time: " + paymentTime+
                "\nBooking Date: " + bookingDate+
                "\nParcel Status: " + parcelStatus;

    }

    // Update Pickup and DropOff time of Officer
    public void updatePickupAndDropoffTimes(String newPickupTime, String newDropoffTime) {
        this.pickupTime = newPickupTime;
        this.dropoffTime = newDropoffTime;
    }

    // To update parcel status of a user
    public void updateParcelStatus(String newStatus) {
        this.parcelStatus = newStatus;
    }
}

