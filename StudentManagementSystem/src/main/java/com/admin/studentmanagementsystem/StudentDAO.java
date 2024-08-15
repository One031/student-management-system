package com.admin.studentmanagementsystem;

import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author oneaz
 */
// Data Access Object (DAO) class for managing students in the Student management system


public class StudentDAO {
    
    //Method to add new student to the database
    public static void add(Student student) {
        String url = "jdbc:mysql://localhost:3306/smsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        String username = "root";

        String password = "P@ssword1";

        String driver = "com.mysql.cj.jdbc.Driver";

        try {

            //Establish connection 
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL query for insertion
             String query = "INSERT INTO Student (StudentID, StudentName, StudentMobile) VALUES (?, ?, ?)";
            // Validation patterns
            String mobileValidation = "^(\\+?27|0)\\d{9}$";
            String idValidation = "^\\d+$";
           
            // Validating the input fields
            if (student.getStudentID().isEmpty() || student.getStudentName().isEmpty() || student.getStudentMobile().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Please fill in all fields.", "Error", "Error adding student");

            } else if (student.getStudentMobile().matches(mobileValidation) && student.getStudentID().matches(idValidation)) {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, student.getStudentID());
                ps.setString(2, student.getStudentName());
                ps.setString(3, student.getStudentMobile());

                ps.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Student added successfully: " + student, "Success", "Student Added");
            } else if (!student.getStudentID().matches(idValidation)) {
                showAlert(Alert.AlertType.ERROR, "Please enter a valid ID", "Invalid Input", "Invalid ID format");
            } else {
                showAlert(Alert.AlertType.ERROR, "It should start with 0 and have 10 digits.", "Invalid Input", "Invalid mobile number format.");
            }

            conn.close();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

            e.printStackTrace();

        }

    }
    
    // Method to show an alert dialog
    public static void showAlert(Alert.AlertType alertType, String message, String title, String head) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.showAndWait();

    }
    
    // Method to view all students in the database
    public static void view() {

        String url = "jdbc:mysql://localhost:3306/smsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";

        String password = "P@ssword1";

        String driver = "com.mysql.cj.jdbc.Driver";

        try {

            //Establish connection 
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(url, username, password);

            Statement s = conn.createStatement();
            
            // SQL query to retrieve all students
            String retrieve = "Select * FROM student";

            //Place all records retrieved in a result get
            ResultSet rs = s.executeQuery(retrieve);

            // Iterate through the result set and display the records on the screen
            StringBuilder records = new StringBuilder();
            while (rs.next()) {
                String sID = rs.getString("StudentID");
                String sName = rs.getString("StudentName");
                String sMobile = rs.getString("StudentMobile");

                records.append("Student{id=").append(sID).append(", name=").append(sName).append(", mobileNumber=").append(sMobile).append("}\n");
            }

            showAlert(Alert.AlertType.INFORMATION, records.toString(), "Student Information", "List of Students");

            conn.close();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

            e.printStackTrace();

        }

    }

    // Method to find a student in the database
    public static void find(Student student) {

        String url = "jdbc:mysql://localhost:3306/smsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";

        String password = "P@ssword1";

        String driver = "com.mysql.cj.jdbc.Driver";

        try {

            //Establish connection 
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(url, username, password);

            Statement s = conn.createStatement();
            
            // SQL query to retrieve a specific student
            String get = "Select * FROM student";
            
            // Collect matching records and display
            ResultSet fd = s.executeQuery(get);
            

            StringBuilder recordsF = new StringBuilder();
            while (fd.next()) {
                String sID = fd.getString("StudentID");
                String sName = fd.getString("StudentName");
                String sMobile = fd.getString("StudentMobile");
                if (sID.equals(student.getStudentID()) || sName.equals(student.getStudentName()) || sMobile.equals(student.getStudentMobile())) {
                    recordsF.append("Student{id=").append(sID).append(", name=").append(sName).append(", mobileNumber=").append(sMobile).append("}\n");
                }
            }
            
            
                showAlert(Alert.AlertType.INFORMATION, "Student Found:" + recordsF.toString(), "Student Information", "Student Found");
           
                
            
            conn.close();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

            e.printStackTrace();

        }

    }

    // Method to remove a student from the database
    public static void remove(Student student) {

        String url = "jdbc:mysql://localhost:3306/smsdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";

        String password = "P@ssword1";

        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            // Establish connection
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(url, username, password);
            
            // SQL query to delete a student by ID
            String delete = "Delete FROM student WHERE StudentID = ?";

            PreparedStatement statement = conn.prepareStatement(delete);
            statement.setString(1, student.getStudentID());

            int rowsDeleted = statement.executeUpdate();
            
            // Show alert dialog based on the result
            if (rowsDeleted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Student with ID " + student.getStudentID() + " deleted successfully.", "Student Deleted", "Success");
            }else{
                showAlert(Alert.AlertType.ERROR, "Please enter a valid ID", "Invalid Input", "Invalid ID format");
            }

            conn.close();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

            e.printStackTrace();

        }

    }

}
