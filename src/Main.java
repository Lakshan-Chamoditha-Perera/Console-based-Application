import entity.Appointment;
import entity.Job;
import entity.JobType;
import entity.Technician;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Technician> technicianList = new ArrayList<>(); // List of technicians
    public static Scanner scanner = new Scanner(System.in); // Scanner object to read input

    /**
     * Initialize the technician list with a technician
     */
    static {
        Technician technician = new Technician("Dave", LocalDate.now(), List.of(new Job(JobType.PLUMBING, 60), new Job(JobType.AC_REPAIRING, 100)));
        technicianList.add(technician);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Book an appointment");

            Appointment appointment = new Appointment(promptDate(), promptTime(), null, promptJobType());

            try {

                Technician technician = checkAvailability(appointment);
                if (technician != null) {
                    System.out.println("Technician " + technician.getName() + " is available for the appointment.");
                    appointment.setEnd(calculateEstimatedTime(appointment).toLocalTime());
                    technician.getAppointmentList().add(appointment);

//                technician.getAppointmentList().forEach(System.out::println);

                    System.out.println("Appointment booked successfully.");
                } else {
                    System.out.println("No technician available for the appointment.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Do you want to book another appointment? (Y/N)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("Y")) break;
        }
    }

    /**
     * Prompt the user to enter the job type
     *
     * @return JobType
     */
    private static JobType promptJobType() {
        JobType appointmentJob = null;
        while (appointmentJob == null) {
            System.out.println("Input job type (1: Plumbing / 2: A/C repairing): ");
            if (scanner.hasNextInt()) {
                int jobType = scanner.nextInt();
                scanner.nextLine();
                if (jobType == 1) {
                    appointmentJob = JobType.PLUMBING;
                } else if (jobType == 2) {
                    appointmentJob = JobType.AC_REPAIRING;
                } else {
                    System.out.println("Invalid job type. Please enter 1 for Plumbing or 2 for A/C repairing.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        }
        return appointmentJob;
    }

    /**
     * Prompt the user to enter the date in dd/MM/yyyy format
     *
     * @return LocalDate
     */
    private static LocalDate promptDate() {
        LocalDate parsedDate = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (parsedDate == null) {
            System.out.println("Input Date (dd/MM/yyyy): ");
            String dateString = scanner.nextLine();
            try {
                parsedDate = LocalDate.parse(dateString, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }
        return parsedDate;
    }


    /**
     * Prompt the user to enter the time in hh:mm aa format
     *
     * @return LocalTime
     */
    private static LocalTime promptTime() {
        LocalTime bookingTime = null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        while (bookingTime == null) {
            System.out.print("Enter time (hh:mm aa): ");
            String timeString = scanner.nextLine();
            try {
                bookingTime = LocalTime.parse(timeString, timeFormatter);
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter the time in hh:mm aa format.");
            }
        }
        return bookingTime;
    }


    /**
     * Check if a technician is available for the appointment
     *
     * @param appointment
     * @return Technician
     */
    public static Technician checkAvailability(Appointment appointment) {
        if (technicianList.isEmpty()) throw new RuntimeException("No technician available");
        return technicianList.stream().filter(technician -> technician.isAvailable(appointment)).findFirst().orElse(null);
    }


    /**
     * Calculate the estimated time for the appointment
     *
     * @param appointment
     * @return Time
     */
    public static Time calculateEstimatedTime(Appointment appointment) {
        switch (appointment.getJobType()) {
            case PLUMBING:
                return Time.valueOf(appointment.getStart().plusMinutes(60));
            case AC_REPAIRING:
                return Time.valueOf(appointment.getStart().plusMinutes(100));
            default:
                throw new RuntimeException("Invalid Job Type");
        }
    }
}
