package com.admin.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    TextField textFieldID; // Textfield for student ID

    @FXML
    TextField textFieldNameID; // Textfield for student name

    @FXML
    TextField textFieldMobileID; // Textfield for student mobile

    public void btn_Add(ActionEvent e) {
        // Get input from text fields
        String studentID = textFieldID.getText();
        String studentName = textFieldNameID.getText();
        String studentMobile = textFieldMobileID.getText();

        // Creating a new Student object
        Student student = new Student(studentID, studentName, studentMobile);

        // Add the student to the database
        StudentDAO.add(student);

        // Clear the input fields
        textFieldID.clear();
        textFieldNameID.clear();
        textFieldMobileID.clear();
    }

    public void btn_View(ActionEvent e) {
        // Find all the students in the database
        StudentDAO.view();

    }

    public void btn_Search(ActionEvent e) {
        // Get input values from text fields
        String studentID = textFieldID.getText();
        String studentName = textFieldNameID.getText();
        String studentMobile = textFieldMobileID.getText();

        // Create a new Student object
        Student student = new Student(studentID, studentName, studentMobile);
        // Find the student in the database
        StudentDAO.find(student);
        
    }

    public void btn_Delete(ActionEvent e) {
        // Get student ID from text field
        String studentID = textFieldID.getText();
        
        // Create a new Student object with the student ID
        Student student = new Student(studentID);
        
        // Remove the student from the database
        StudentDAO.remove(student);
        
        // Clear the student ID input field
        textFieldID.clear();
    }

}
