package HospitalManagementSystem;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "admin@2025";
    
    // This is just for highlight the heading with green colour
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            
            while (true){
                System.out.println(ANSI_GREEN + "========== Hospital Management System ==========" + ANSI_RESET);
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");

                System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                
                switch (choice){
                    case 1:
                        //Add Patient
                        patient.addPatient();
                        System.out.println();
                    case 2:
                        //View Patient
                        patient.viewPatient();
                        System.out.println();
                    case 3:
                        //View Doctors
                        doctor.viewdoctors();
                        System.out.println();
                    case 4:
                        //Book appointment
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                    case 5:
                        return;
                    default:
                        System.out.println("Please Enter a valid choice : ");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void bookAppointment(Patient patient, Doctor doctor,Connection connection, Scanner scanner){
        System.out.print("Enter the patients id : ");
        int patientId = scanner.nextInt();
        System.out.print("Enter the Doctor id : ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter the appointment date (YYYY-MM-DD) : ");
        String appointmentDate = scanner.next();
        if (patient.getPatientById(patientId) && doctor.getDoctorByID(doctorId)){
            if (checkDoctorAvailability(doctorId, appointmentDate, connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    
                    int rowAffected = preparedStatement.executeUpdate();
                    if (rowAffected > 0){
                        System.out.println("Appointment has been booked successfully.");
                    }else {
                        System.out.println("Failed to book appointment. Try again later");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }               
        }else {
            System.out.println("Either Doctor or Patient doesn't exist");
        }
    } 
    
    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";   //count(*) it is used to count the no. or rows
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()){
                int count = resultSet.getInt(1);        //if doctor is appointed then 1 row is showing so we give false result bcz dr. is appointed
                if (count == 0){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
