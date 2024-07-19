package entity;

/**
 * Entity class for job
 */
public class Job {
    private JobType jobType;
    private long estimatedTime;

    public Job() {
    }

    public Job(JobType jobType, long estimatedTime) {
        this.jobType = jobType;
        this.estimatedTime = estimatedTime;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "Job{" + "jobType=" + jobType + ", estimatedTime=" + estimatedTime + '}';
    }
}
