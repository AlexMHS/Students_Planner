package com.malakov.newThingOmegaLul;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBoxNew {

    public static void display(Day day)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //pp
        window.setTitle("yeet");
        window.setMinWidth(400);
        window.setMinHeight(400);

        Button closeButton = new Button("back");
        closeButton.setOnAction(e-> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }


}
