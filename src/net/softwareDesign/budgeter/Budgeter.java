package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Budgeter extends Application  {
    /**
     * Overall pane used for the application
     */
    private static GridPane pane;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        pane = new GridPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
