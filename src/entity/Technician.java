package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Technician {
    private String name;
    private LocalDate date;
    private List<Job> jobList = new ArrayList<>();
    private List<Appointment> appointmentList = new ArrayList<>();

    public Technician() {
    }

    public Technician(String name, LocalDate date, List<Job> jobList) {
        this.name = name;
        this.date = date;
        this.jobList = jobList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    /**
     * Check if the technician is available for the appointment
     * @param appointment
     * @return technician
     */
    public boolean isAvailable(Appointment appointment) {
        // Check if the technician has the required job type
        boolean hasJobType = jobList.stream()
                .anyMatch(j -> j.getJobType().equals(appointment.getJob().getJobType()));

        if (!hasJobType) {
            System.out.println("Technician is not available for the job type");
            return false;
        }

        // Check if the technician is available at the requested time
        boolean isAvailable = checkJobTimeAndDateAvailability(appointment);
        if (isAvailable) {
            System.out.println("Technician is available for the appointment");
        } else {
            System.out.println("Technician is not available for the appointment");
        }
        return isAvailable;
    }

    /**
     * Check if the technician is available for the appointment
     * @param appointment
     * @return technician
     */
    private boolean checkJobTimeAndDateAvailability(Appointment appointment) {
        return appointmentList.stream()
                .filter(app -> app.getDate().equals(appointment.getDate()))
                .allMatch(app -> checkTime(appointment.getJob(), appointment.getStart(), app));
    }

    /**
     * Check if the technician is available at the requested time
     * @param job
     * @param start
     * @param currentAppointment
     * @return boolean
     */
    public boolean checkTime(Job job, LocalTime start, Appointment currentAppointment) {
        LocalTime currentEnd = currentAppointment.getEnd();
        LocalTime proposedEnd = start.plusMinutes(job.getEstimatedTime());
        // Check for overlap: return false if there is any overlap
        return !(start.isBefore(currentEnd) && proposedEnd.isAfter(currentAppointment.getStart()));
    }


    @Override
    public String toString() {
        return "Technician{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", jobList=" + jobList +
                ", appointmentList=" + appointmentList +
                '}';
    }
}
