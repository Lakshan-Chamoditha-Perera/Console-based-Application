import entity.Appointment;
import entity.Job;
import entity.JobType;
import entity.Technician;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in); // Scanner object to read input

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Book an appointment");
            Appointment appointment = new Appointment(promptDate(), // Prompt the user to enter the date
                    promptTime(), // Prompt the user to enter the time
                    null, // End time will be calculated based on the job type
                    promptJobType()); // Prompt the user to enter the job type
            try {

                Technician technician = checkAvailability(appointment); // Check if a technician is available for the appointment
                if (technician != null) {
                    System.out.println("Technician " + technician.getName() + " is available for the appointment.");
                    appointment.setEnd(calculateEstimatedTime(appointment).toLocalTime());
                    technician.getAppointmentList().add(appointment);

                    technician.getAppointmentList().forEach(System.out::println);

                    System.out.println("Appointment booked successfully.");
                } else {
                    System.out.println("No technician available for the appointment.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Do you want to book another appointment? (press any key to exit or Y/y to continue)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("Y")) break;
        }
    }

    /**
     * Prompt the user to enter the job type
     *
     * @return Job
     */
    private static Job promptJobType() {
        Job appointmentJob = null;
        while (appointmentJob == null) {
            System.out.println("Select job type:");
            System.out.println("1: Plumbing");
            System.out.println("2: A/C Repairing");
            System.out.print("Enter your choice (1 or 2): ");

            if (scanner.hasNextInt()) {
                int jobType = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                appointmentJob = createJobFromType(jobType);
                if (appointmentJob == null) {
                    System.out.println("Invalid job type. Please enter 1 for Plumbing or 2 for A/C Repairing.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return appointmentJob;
    }

    /**
     * Creates a Job instance based on the provided job type.
     *
     * @param jobType the job type selected by the user
     * @return a Job instance or null if the job type is invalid
     */
    private static Job createJobFromType(int jobType) {
        return switch (jobType) {
            case 1 -> new Job(JobType.PLUMBING, 60);
            case 2 -> new Job(JobType.AC_REPAIRING, 100);
            default -> null;
        };
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
        if (Technician.getTechnicianList().isEmpty()) throw new RuntimeException("No technician available");
        return Technician.getTechnicianList().stream().filter(technician -> technician.isAvailable(appointment)).findFirst().orElse(null);
    }


    /**
     * Calculate the estimated time for the appointment
     *
     * @param appointment
     * @return Time
     */
    public static Time calculateEstimatedTime(Appointment appointment) {
        return Time.valueOf(appointment.getStart().plusMinutes(appointment.getJob().getEstimatedTime()));
    }
}
