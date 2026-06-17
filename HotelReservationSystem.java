import java.util.ArrayList;
import java.util.Scanner;

// Room Class
class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        available = status;
    }
}

// Customer Class
class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Reservation Class
class Reservation {
    private static int counter = 1001;

    private int bookingId;
    private Customer customer;
    private Room room;

    public Reservation(Customer customer, Room room) {
        this.bookingId = counter++;
        this.customer = customer;
        this.room = room;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                " | Customer: " + customer.getName() +
                " | Room: " + room.getRoomNumber() +
                " | Category: " + room.getCategory();
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void initializeRooms() {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));

        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));

        rooms.add(new Room(301, "Suite", 4000));
    }

    public static void viewAvailableRooms() {

        System.out.println("\n----- AVAILABLE ROOMS -----");

        boolean found = false;

        for (Room room : rooms) {

            if (room.isAvailable()) {

                found = true;

                System.out.println(
                        "Room No: " + room.getRoomNumber() +
                                " | Category: " + room.getCategory() +
                                " | Price: ₹" + room.getPrice());
            }
        }

        if (!found) {
            System.out.println("No Rooms Available.");
        }
    }

    public static void bookRoom() {

        viewAvailableRooms();

        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNo &&
                    room.isAvailable()) {

                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room Not Available!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer customer = new Customer(name);

        System.out.println("\nPayment Amount: ₹" +
                selectedRoom.getPrice());

        System.out.print("Confirm Payment? (yes/no): ");
        String payment = sc.nextLine();

        if (!payment.equalsIgnoreCase("yes")) {
            System.out.println("Booking Cancelled.");
            return;
        }

        selectedRoom.setAvailable(false);

        Reservation reservation =
                new Reservation(customer, selectedRoom);

        reservations.add(reservation);

        System.out.println("\nBooking Successful!");
        System.out.println("Booking ID: " +
                reservation.getBookingId());
    }

    public static void cancelReservation() {

        System.out.print("Enter Booking ID: ");
        int bookingId = sc.nextInt();

        for (Reservation r : reservations) {

            if (r.getBookingId() == bookingId) {

                r.getRoom().setAvailable(true);

                reservations.remove(r);

                System.out.println("Reservation Cancelled!");
                return;
            }
        }

        System.out.println("Booking ID Not Found!");
    }

    public static void viewReservations() {

        if (reservations.isEmpty()) {
            System.out.println("No Reservations Found.");
            return;
        }

        System.out.println("\n----- RESERVATIONS -----");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {

        initializeRooms();

        int choice;

        do {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewAvailableRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelReservation();
                    break;

                case 4:
                    viewReservations();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }
}
