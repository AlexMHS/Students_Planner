package com.malakov.newThingOmegaLul;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Months {
     ArrayList<Day> days = new ArrayList<>();
     String[] weekdays= new String []{"mon","tue","wed","thu","fri","sat","sun"};
     Scene scene;
     String name;
     GridPane grid = new GridPane();
     public Months(int numdays, int buff,String namef)
     {
          int dayNum = 0;
          for(int i = 0; i < numdays; i++)
          {
               days.add(new Day(dayNum,weekdays[(buff+i)%7]));
               dayNum++;
          }
          name=namef;
     }
}
