package entity;

import java.sql.Time;

public class Job {
   private JobType jobType;
   private Time estimatedTime;

    public Job() {
    }

    public Job(JobType jobType, Time estimatedTime) {
        this.jobType = jobType;
        this.estimatedTime = estimatedTime;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
