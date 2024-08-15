module com.admin.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.admin.studentmanagementsystem to javafx.fxml;
    exports com.admin.studentmanagementsystem;
}
