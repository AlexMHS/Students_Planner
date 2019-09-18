package com.malakov.buttonbreaker;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

import javafx.scene.control.Label;

import static com.malakov.buttonbreaker.AlertBox.display;

public class ButtonBreakerGame extends Application {
    private static final int DELAY = 750;
    private static final int DELAY_BETWEEN = 250;
    private static Color[] allColors = Color.values();

    private int pointCounter = 0;
    private int levelCounter = 1;
    private int highestScore = 0;
    private int closestScore = 0;
    private int thirdScore = 0;
    private boolean clickLock = true;
    private String hs1 = "alex";
    private String hs2 = "alex";
    private String hs3 = "alex";
    private Stage window;
    private Scanner scanner;
    private Scene startScreen, gameScreen, endScreen;
    private Button redButton, blueButton, greenButton, yellowButton, ruleButton, quitButton, startButton, backToStart;
    private Label pointLabel, levelLabel, saysTheScore, saysTheLevel, gameTitle, highScoreOne, gameEnded;
    private Random random = new Random();
    private ArrayList<Color> remainingColors = new ArrayList<>();
    private ArrayList<Color> currentColorsChosen = new ArrayList<>();

    private String playerName;

    Color colorToChange = Color.Blue;

    public enum Color {
        Blue, Red, Green, Yellow
    }

    private Timer timer = new Timer();

    AudioClip boopSound, passSound, failSound;

    public ButtonBreakerGame () {
        boopSound = new AudioClip(this.getClass().getResource("/resource/sounds/boop.m4a").toExternalForm());
        passSound = new AudioClip(this.getClass().getResource("/resource/sounds/pass.m4a").toExternalForm());
        failSound = new AudioClip(this.getClass().getResource("/resource/sounds/fail.m4a").toExternalForm());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Button buffer = new Button();
        buffer.setMinSize(200, 200);
        buffer.setVisible(false);


        gameEnded = new Label("GAME OVER");
        gameEnded.setFont(new Font("Arial",50));
        highScoreOne = new Label("Score");
        highScoreOne.setFont(new Font("Arial", 30));
        backToStart = new Button("BACK TO START");
        backToStart.setOnAction(e -> {
            resetGame();
        });
        VBox endLayout = new VBox(10);
        backToStart.setMinSize(200,30);
        endLayout.getChildren().addAll(gameEnded, highScoreOne,buffer, backToStart);
        endLayout.setAlignment(Pos.CENTER);
        setBsckground(endLayout, "/resource/images/BtnBackground.jpg");
        endScreen = new Scene(endLayout, 600, 600);


        VBox startLayout = new VBox(10);
        startButton = new Button("START");
        ruleButton = new Button("HOW TO PLAY");
        ruleButton.setOnAction(e -> display("rules", "Colors will be displayed, when they have finished\nbeing shown, you must copy the pattern by clicking\non the color boxes which corespond to those shown"));
        quitButton = new Button("EXIT");
        gameTitle = new Label("BUTTON BREAKER");
        gameTitle.setFont(new Font("Impact", 50));
//        gameTitle.setStyle("-fx-font-family: Fixedsys");
        quitButton.setOnAction(e -> closeTheGame());
        startButton.setOnAction(e -> {
            window.setScene(gameScreen);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    showNextColors();
                }
            }, 1000);
        });
        startButton.setMinSize(200, 30);
        quitButton.setMinSize(200, 30);
        ruleButton.setMinSize(200, 30);


        startLayout.getChildren().addAll(gameTitle, buffer, startButton, ruleButton, quitButton);
        startLayout.setAlignment(Pos.CENTER);
        setBsckground(startLayout, "/resource/images/BtnBackground.jpg");
        startScreen = new Scene(startLayout, 600, 600);


        //game screen-----------------------------------------------------------------------------------------------

        blueButton = new Button();
        redButton = new Button();
        yellowButton = new Button();
        greenButton = new Button();

        levelLabel = new Label("" + levelCounter);
        pointLabel = new Label("" + pointCounter);
        saysTheLevel = new Label("Level: ");
        saysTheScore = new Label("Score: ");
        levelLabel.setFont(new Font("Arial", 30));
        pointLabel.setFont(new Font("Arial", 30));
        saysTheLevel.setFont(new Font("Arial", 30));
        saysTheScore.setFont(new Font("Arial", 30));

        resetButtonColor();

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);
        GridPane.setConstraints(saysTheLevel, 0, 0);
        GridPane.setConstraints(saysTheScore, 0, 1);
        GridPane.setConstraints(levelLabel, 1, 0);
        GridPane.setConstraints(pointLabel, 1, 1);
        pane.getChildren().addAll(saysTheLevel, saysTheScore, levelLabel, pointLabel);
        pane.setAlignment(Pos.CENTER);


        blueButton.setMinSize(600, 100);
        redButton.setMinSize(600, 100);
        greenButton.setMinSize(100, 400);
        yellowButton.setMinSize(100, 400);


        blueButton.setOnAction(e -> {
            blueButton.setStyle("-fx-background-color: #1b5cc4");
            if (!checkIfWorks(Color.Blue))
                gameOver();
        });
        blueButton.setOnMousePressed(e -> {
            blueButton.setStyle("-fx-background-color: #70baef");
            if ( ! clickLock)
                boopSound.play();
        });

        redButton.setOnAction(e -> {
            redButton.setStyle("-fx-background-color: #f92020");
            if (!checkIfWorks(Color.Red))
                gameOver();
        });
        redButton.setOnMousePressed(e -> {
            redButton.setStyle("-fx-background-color: #d66060");
            if ( ! clickLock)
                boopSound.play();
        });

        yellowButton.setOnAction(e -> {
            yellowButton.setStyle("-fx-background-color: #f9ef20");
            if (!checkIfWorks(Color.Yellow))
                gameOver();
        });
        yellowButton.setOnMousePressed(e -> {
            yellowButton.setStyle("-fx-background-color: #fff0c1");
            if ( ! clickLock)
                boopSound.play();
        });

        greenButton.setOnAction(e -> {
            greenButton.setStyle("-fx-background-color: #20700c");
            if (!checkIfWorks(Color.Green))
               gameOver();

        });
        greenButton.setOnMousePressed(e -> {
            greenButton.setStyle("-fx-background-color: #5edb77");
            if ( ! clickLock)
                boopSound.play();
        });

        BorderPane gameLayout = new BorderPane();
        gameLayout.setTop(blueButton);
        gameLayout.setLeft(yellowButton);
        gameLayout.setRight(greenButton);
        gameLayout.setBottom(redButton);
        gameLayout.setCenter(pane);
        setBsckground(gameLayout, "/resource/images/BtnBackground2.jpg");
        gameScreen = new Scene(gameLayout, 600, 600);


        // Window
        window.setScene(startScreen);
        window.setTitle("Color game");
        window.show();
    }

    public void resetButtonColor() {
        blueButton.setStyle("-fx-background-color: #1b5cc4");
        redButton.setStyle("-fx-background-color: #f92020");
        yellowButton.setStyle("-fx-background-color: #f9ef20");
        greenButton.setStyle("-fx-background-color: #20700c");
    }


    private void showNextColors() {


        clickLock = true;

        //add new value to the array
        addRandomColor();

        //phase 2: show the color
        remainingColors = new ArrayList<>(currentColorsChosen);
        for (int i = 0; i < currentColorsChosen.size(); i++) {
            if (i > 0)
                sleep(DELAY_BETWEEN);
            switch (currentColorsChosen.get(i)) {
                case Blue:
                    blueButton.setStyle("-fx-background-color: #70baef");
                    sleep(DELAY);
                    blueButton.setStyle("-fx-background-color: #1b5cc4");
                    break;
                case Red:
                    redButton.setStyle("-fx-background-color: #d66060");
                    sleep(DELAY);
                    redButton.setStyle("-fx-background-color: #f92020");
                    break;
                case Yellow:
                    yellowButton.setStyle("-fx-background-color: #fff0c1");
                    sleep(DELAY);
                    yellowButton.setStyle("-fx-background-color: #f9ef20");
                    break;
                case Green:
                    greenButton.setStyle("-fx-background-color: #5edb77");
                    sleep(DELAY);
                    greenButton.setStyle("-fx-background-color: #20700c");
                    break;
            }
        }

        clickLock=false;
//        System.out.println("done");
    }

    private void addRandomColor() //update the array
    {
        Color c = allColors[random.nextInt(allColors.length)];
        currentColorsChosen.add(c);
    }

    private boolean checkIfWorks(Color colour)//change the values in the second array
    {
        if (remainingColors.isEmpty())
            return true;

        if (clickLock)
            return true;

        if (remainingColors.get(0) == colour) {
            pointCounter++;
            pointLabel.setText("" + pointCounter);
            remainingColors.remove(0);
            if (remainingColors.size() == 0) {
                levelUp();
            }
            return true;
        }

        return false;
    }

    private void levelUp() {
        passSound.play();

        levelCounter++;
        levelLabel.setText("" + levelCounter);

        TimerTask newTask = new TimerTask() {
            @Override
            public void run() {
                showNextColors();
            }
        };
        timer.schedule(newTask, 1500);
    }

    private void gameOver() {
        failSound.play();
        sleep(1000);
        if (playerName == null)
            playerName = askForPlayerName();

        if(pointCounter>highestScore)
        {
            highestScore=pointCounter;
        }

        highScoreOne.setText("Your Record:  " + playerName + ":" + highestScore);
        window.setScene(endScreen);
        currentColorsChosen = new ArrayList<>();
    }

    private void resetGame() {
        clickLock = true;
        pointCounter = 0;
        levelCounter = 1;
        window.setScene(startScreen);
    }

    private void sleep (int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeTheGame() {
        window.close();
    }

    String askForPlayerName() {
        TextInputDialog dialog = new TextInputDialog("Alex");
        dialog.setTitle("Enter your name");
        dialog.setHeaderText("You name will be displayed as a potential high score");

        Optional<String> result = dialog.showAndWait();
        String entered = "Alex is best";
        if (result.isPresent())
            entered = result.get();
        return entered;
    }

    void setBsckground (Pane pane, String image) {
        BackgroundImage back = new BackgroundImage(new Image(image, 600, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(back));
    }
 }
