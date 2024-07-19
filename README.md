# Appointment Booking System

## Project Overview

This Java application allows users to book appointments for various job types with available technicians. The system includes functionality to manage technicians, jobs, and appointments, ensuring that appointments are scheduled only if a technician is available and qualified.

## Design Patterns and Concepts

### 1. **Singleton Pattern**

- **Usage**: The `Technician` class uses a static `technicianList` to manage a list of technicians.
- **Purpose**: Ensures that the list of technicians is instantiated only once, and subsequent calls to `getTechnicianList()` return the same instance.

### 2. **Factory Method Pattern**

- **Usage**: The `createJobFromType(int jobType)` method in the `Main` class acts as a factory for creating `Job` instances based on job type.
- **Purpose**: Encapsulates the job creation logic and allows for the creation of different job types without exposing the instantiation logic to the client code.

### 3. **Strategy Pattern**

- **Usage**: The `checkTime(Job job, LocalTime start, Appointment currentAppointment)` method in the `Technician` class is used to determine if a technician's schedule is free for a new appointment.
- **Purpose**: Allows for flexible checking of time availability by encapsulating the logic for time overlap checks in a separate method.

### 4. **Builder Pattern**

- **Usage**: The `Appointment` class can be seen as using a simple form of the Builder pattern through its constructor that sets all required attributes.
- **Purpose**: Provides a clear and readable way to create `Appointment` objects with all necessary fields.

### 5. **Observer Pattern (Implicit)**

- **Usage**: The system maintains a list of appointments in the `Technician` class, which can be observed or updated as new appointments are added.
- **Purpose**: Allows for the technician's appointment list to be updated dynamically and observed for changes.

## Key Classes

### 1. **`Technician`**

- **Attributes**:
    - `name`: The technician's name.
    - `date`: The date the technician is available or their profile was created.
    - `jobList`: List of jobs the technician is qualified for.
    - `appointmentList`: List of appointments scheduled with the technician.
- **Methods**:
    - `isAvailable(Appointment appointment)`: Checks if the technician can handle the job and is available at the requested time.
    - `checkJobTimeAndDateAvailability(Appointment appointment)`: Verifies time and date availability for the technician.

### 2. **`JobType` (Enum)**

- **Values**: `AC_REPAIRING`, `PLUMBING`
- **Purpose**: Defines different types of jobs that can be scheduled.

### 3. **`Job`**

- **Attributes**:
    - `jobType`: The type of job (from `JobType` enum).
    - `estimatedTime`: The estimated time required for the job.
- **Methods**:
    - Getters and setters for attributes.

### 4. **`Appointment`**

- **Attributes**:
    - `date`: The date of the appointment.
    - `start`: The start time of the appointment.
    - `end`: The end time of the appointment.
    - `job`: The job associated with the appointment.
- **Methods**:
    - Getters and setters for attributes.
