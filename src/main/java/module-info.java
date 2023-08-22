module com.application.practical_exams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.practical_exams to javafx.fxml;
    exports com.application.practical_exams;
}