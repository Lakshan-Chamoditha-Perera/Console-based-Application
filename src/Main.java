import entity.Appointment;
import entity.Job;
import entity.JobType;
import entity.Technician;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        Job job1 = new Job();
        job1.setJobType(JobType.PLUMBING);
//        job1.setDuration("60 min");

        Job job2 = new Job();
        job1.setJobType(JobType.AC_REPARING);
//        job1.setDuration("100 min");

        Technician technician = new Technician();
        technician.setName("Dave");
        technician.getJobList().add(job1);
        technician.getJobList().add(job2);

        System.out.println("Input job type (1: Plumbing / 2: A/C repairing): ");
        int jobType = scanner.nextInt();
        JobType appointmentJob = jobType == 1 ? JobType.PLUMBING : JobType.AC_REPARING;

        System.out.println("Input Date (dd/MM/yyyy): ");
        Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

        System.out.print("Enter time (hh:mm aa): ");
        String time = scanner.next();
        Time booking_time = Time.valueOf(time);

        Appointment newAppointment = new Appointment();
        newAppointment.setJobType(appointmentJob);
        newAppointment.setDate(parsedDate);
        newAppointment.setStart(booking_time);


        if (checkAvailability(technician, newAppointment)) {
            System.out.println("Technitian is available");
            newAppointment.setEnd(calculateEstimatedTime(newAppointment));
            technician.getAppointmentList().add(newAppointment);
        }
    }

    public static boolean checkAvailability(Technician technician, Appointment appointment) {
        // checking the job type
        List list = new ArrayList();
        technician.getJobList().forEach(ele -> {
            list.add(ele.getJobType());
        });
        boolean contains = list.contains(appointment.getJobType());
        if (!contains) {
            throw new RuntimeException("Technition is not available for request job");
        }

        // checking the date
        technician.getAppointmentList().forEach(element -> {
            if (element.getDate().equals(appointment.getDate())) {

            //  checking time availability
                checkTimeAvailability(technician.getAppointmentList(), appointment);

            }
        });

        // no any appointments
        return true;
    }

    /**
     * Calculating time
     *
     * @param appointment
     * @return
     */
    public static Time calculateEstimatedTime(Appointment appointment) {
        switch (appointment.getJobType()) {
            case PLUMBING:
                return Time.valueOf(LocalTime.parse(appointment.getStart().toString()).plusMinutes(60));

            case AC_REPARING:
                return Time.valueOf(LocalTime.parse(appointment.getStart().toString()).plusMinutes(60));
            default:
                throw new RuntimeException("Invalid Job Type");
        }
    }

    /**
     * Checking time availability
     */
    public static boolean checkTimeAvailability(List<Appointment> appointments, Appointment booking) {

        return true;
    }

}