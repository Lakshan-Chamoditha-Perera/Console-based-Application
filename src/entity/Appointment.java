package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private Job job;

    public Appointment(LocalDate date, LocalTime start, LocalTime end, Job job) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.job = job;
    }

    public Appointment() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Appointment{" + "date=" + date + ", start=" + start + ", end=" + end + ", job=" + job + '}';
    }
}
