package entity;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private Date date;
    private Time start;
    private Time end;
    private JobType jobType;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public Appointment(Date date, Time start, Time end, JobType jobType) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.jobType = jobType;
    }

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", start=" + start +
                ", end=" + end +
                ", jobType=" + jobType +
                '}';
    }
}
