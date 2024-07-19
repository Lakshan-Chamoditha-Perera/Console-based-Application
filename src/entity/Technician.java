package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Technician {
    private String name;
    private Date date;
    private List<Job> jobList = new ArrayList<>();
    private List<Appointment> appointmentList = new ArrayList<>();

    public Technician() {
    }

    public Technician(String name, Date date, List<Job> jobList) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}


