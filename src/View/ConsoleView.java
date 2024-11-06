package View;

import Model.Appointment;
import Model.Doctor;
import java.util.List;

public class ConsoleView {
    public void showMainMenu() {
        System.out.println("\n--- Medical Appointment System ---");
        System.out.println("1. Book an Appointment");
        System.out.println("2. View Appointments");
        System.out.println("3. Cancel an Appointment");
        System.out.println("0. Exit");
    }

    public void displayDoctors(List<Doctor> doctors) {
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
    }

    public void displayTimeSlots(List<String[]> timeSlots) {
        System.out.println("\nAvailable Time Slots:");
        for (int i = 0; i < timeSlots.size(); i++) {
            System.out.println((i + 1) + ". " + timeSlots.get(i)[0] + " - " + timeSlots.get(i)[1]);
        }
    }

    public void showAppointmentDetails(Appointment appointment) {
        if (appointment != null) {
            System.out.println("\nAppointment Booked Successfully!");
            System.out.println(appointment);
        } else {
            System.out.println("\nBooking Failed. Please try again.");
        }
    }

    public void displayAppointments(List<Appointment> appointments) {
        System.out.println("\n--- Appointments ---");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void showCancelResult(boolean result) {
        if (result) {
            System.out.println("\nAppointment cancelled successfully.");
        } else {
            System.out.println("\nAppointment not found.");
        }
    }

    public void invalidChoice() {
        System.out.println("Invalid choice. Please select a valid option.");
    }

    public void exitMessage() {
        System.out.println("Thank you for using the Medical Appointment System. Goodbye!");
    }
}
