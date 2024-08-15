package com.admin.studentmanagementsystem;

/**
 *
 * @author oneaz
 */
public class Student {
    // Private student detail variables
    private String studentID;
    private String studentName;
    private String studentMobile;
    
    // Default constructor
    public Student(){}

    // Constructor to initialize studentID
    public Student(String studentID) {
        this.studentID = studentID;
    }
    
    
    // Constructor to initialize all student details variables
    public Student(String studentID, String studentName, String studentMobile) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentMobile = studentMobile;
    }

    // Getter for studentID
    public String getStudentID() {
        return studentID;
    }

    // Setter for studentID
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

     // Getter for studentName
    public String getStudentName() {
        return studentName;
    }

     // Setter for studentName
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // Getter for studentMobile
    public String getStudentMobile() {
        return studentMobile;
    }

    // Setter for studentMobile
    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    //toString method to provide a string representation of the student object
    @Override
    public String toString() {
        return "Student{" + "ID=" + studentID + ", Name=" + studentName + ", mobileNumber=" + studentMobile + '}';
    }
    
    
    
    
}
