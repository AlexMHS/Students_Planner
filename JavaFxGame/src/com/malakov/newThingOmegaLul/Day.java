package com.malakov.newThingOmegaLul;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Day {
    Button button;
    int number;
    String weekday;
    ArrayList<Homework> hw = new ArrayList<>();
    public Day(int dayNum, String wekday)
    {
        number=dayNum;
        weekday = wekday;
    }
}
