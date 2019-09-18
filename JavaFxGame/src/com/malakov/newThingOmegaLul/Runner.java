package com.malakov.newThingOmegaLul;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.time.Month;
import java.util.ArrayList;

public class Runner extends Application {

    Stage window;

    public void start(Stage stage) throws Exception
    {

        ArrayList<Months>monthAr=new ArrayList<>();
        monthAr.add(new Months(31,3,"jan"));
        monthAr.add(new Months(28,6,"feb"));
        monthAr.add(new Months(31,0,"mar"));
        monthAr.add(new Months(30,3,"apr"));
        monthAr.add(new Months(31,5,"may"));
        monthAr.add(new Months(30,1,"jun"));
        monthAr.add(new Months(31,3,"jul"));
        monthAr.add(new Months(31,7,"aug"));
        monthAr.add(new Months(30,0,"sep"));
        monthAr.add(new Months(31,2,"oct"));
        monthAr.add(new Months(30,5,"dec"));
        monthAr.add(new Months(31,0,"nov"));

        VBox screen = new VBox();

        window = stage;
        TextField addDate = new TextField();

        window = stage;
        window.setScene(monthAr.get(0).scene);
        window.show();
    }
}