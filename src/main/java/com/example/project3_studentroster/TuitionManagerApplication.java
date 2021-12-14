package com.example.project3_studentroster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Driver to start a Window and put up the scene for the application.
 *
 * @author Yuchen Wei, Denghao Sun
 */
public class TuitionManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerApplication.class.getResource("TuitionManager.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Tuition Manager");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main class to launch the application.
     * @param args There should be no arguments passing in.
     */
    public static void main(String[] args) {
        launch();
    }
}