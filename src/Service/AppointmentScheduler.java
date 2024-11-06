package Service;

import Model.Appointment;
import Model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class AppointmentScheduler {
    private static AppointmentScheduler instance;
    private List<Appointment> appointments;
    private List<Doctor> doctors;
    private List<String[]> timeSlots;

    private AppointmentScheduler() {
        appointments = new ArrayList<>();
        doctors = new ArrayList<>();
        initializeDoctors();
        initializeTimeSlots();
    }

    public static AppointmentScheduler getInstance() {
        if (instance == null) {
            instance = new AppointmentScheduler();
        }
        return instance;
    }

    private void initializeDoctors() {
        doctors.add(new Doctor("Alice Smith", "Cardiology"));
        doctors.add(new Doctor("Bob Johnson", "Neurology"));
        doctors.add(new Doctor("Clara Lee", "Dermatology"));
        doctors.add(new Doctor("Daniel Brown", "Pediatrics"));
    }

    private void initializeTimeSlots() {
        timeSlots = new ArrayList<>();
        timeSlots.add(new String[]{"10:00 AM", "11:00 AM"});
        timeSlots.add(new String[]{"11:00 AM", "12:00 PM"});
        timeSlots.add(new String[]{"1:00 PM", "2:00 PM"});
        timeSlots.add(new String[]{"2:00 PM", "3:00 PM"});
        timeSlots.add(new String[]{"3:00 PM", "4:00 PM"});
        timeSlots.add(new String[]{"4:00 PM", "5:00 PM"});
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<String[]> getTimeSlots() {
        return timeSlots;
    }

    // Method to check if the time slot is available for the given doctor and date
    private boolean isTimeSlotAvailable(Doctor doctor, String date, String[] timeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equals(doctor.getName()) &&
                    appointment.getDate().equals(date) &&
                    appointment.getStartTime().equals(timeSlot[0]) &&
                    appointment.getEndTime().equals(timeSlot[1])) {
                return false; // Conflict detected
            }
        }
        return true; // No conflict
    }

    public Appointment bookAppointment(String patientName, Doctor doctor, String date, String[] timeSlot) {
        // Check for conflicts before booking
        if (!isTimeSlotAvailable(doctor, date, timeSlot)) {
            return null; // Indicate conflict
        }

        String id = "A" + (appointments.size() + 1);
        Appointment appointment = new Appointment(id, patientName, doctor.getName(), date, timeSlot[0], timeSlot[1]);
        appointment.setStatus("Confirmed");
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public boolean cancelAppointment(String id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId().equals(id)) {
                appointment.setStatus("Cancelled");
                return true;
            }
        }
        return false;
    }
}
