package Controller;

import Model.Doctor;
import Service.AppointmentScheduler;
import View.ConsoleView;
import java.util.List;
import java.util.Scanner;

public class AppointmentController {
    private AppointmentScheduler scheduler;
    private ConsoleView view;
    private Scanner scanner;

    public AppointmentController() {
        this.scheduler = AppointmentScheduler.getInstance();
        this.view = new ConsoleView();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.showMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    bookAppointment();
                    break;
                case "2":
                    viewAppointments();
                    break;
                case "3":
                    cancelAppointment();
                    break;
                case "0":
                    running = false;
                    view.exitMessage();
                    break;
                default:
                    view.invalidChoice();
            }
        }
    }

    private void bookAppointment() {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        // Display available doctors and select one
        List<Doctor> doctors = scheduler.getDoctors();
        view.displayDoctors(doctors);

        System.out.print("Select a doctor by entering the number: ");
        int doctorIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (doctorIndex >= 0 && doctorIndex < doctors.size()) {
            Doctor selectedDoctor = doctors.get(doctorIndex);

            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            // Display available time slots
            List<String[]> timeSlots = scheduler.getTimeSlots();
            view.displayTimeSlots(timeSlots);

            System.out.print("Select a time slot by entering the number: ");
            int timeSlotIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (timeSlotIndex >= 0 && timeSlotIndex < timeSlots.size()) {
                String[] selectedTimeSlot = timeSlots.get(timeSlotIndex);

                var appointment = scheduler.bookAppointment(patientName, selectedDoctor, date, selectedTimeSlot);
                if (appointment != null) {
                    view.showAppointmentDetails(appointment);
                } else {
                    System.out.println("This time slot is already booked for the selected doctor. Please choose another slot.");
                }
            } else {
                System.out.println("Invalid time slot selection.");
            }
        } else {
            System.out.println("Invalid doctor selection.");
        }
    }

    private void viewAppointments() {
        view.displayAppointments(scheduler.getAppointments());
    }

    private void cancelAppointment() {
        System.out.print("Enter appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();
        boolean result = scheduler.cancelAppointment(appointmentId);
        view.showCancelResult(result);
    }
}
