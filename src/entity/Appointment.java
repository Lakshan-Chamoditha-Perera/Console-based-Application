package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private JobType jobType;

    public Appointment(LocalDate date, LocalTime start, LocalTime end, JobType jobType) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.jobType = jobType;
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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return "Appointment{" + "date=" + date + ", start=" + start + ", end=" + end + ", jobType=" + jobType + '}';
    }
}
