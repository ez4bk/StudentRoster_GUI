module com.example.project3_studentroster {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project3_studentroster to javafx.fxml;
    exports com.example.project3_studentroster;
}