package Model;

public class Appointment {
    private String id;
    private String patientName;
    private String doctorName;
    private String date;
    private String startTime;
    private String endTime;
    private String status;

    public Appointment(String id, String patientName, String doctorName, String date, String startTime, String endTime) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "Pending"; // Status options: Pending, Confirmed, Completed, Cancelled
    }

    public String getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ID: " + id + ", Patient: " + patientName + ", Doctor: " + doctorName + ", Date: " + date +
                ", Time: " + startTime + " - " + endTime + ", Status: " + status;
    }
}
