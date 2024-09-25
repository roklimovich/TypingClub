package pl.edu.pja.s27619.gui_pro3.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class TypingController {
    private int testTime;
    private boolean waitThread;
    private Pane mainPane;
    private TextFlow textFlow;
    private ComboBox<String> selectLanguageButton;
    private String textFromFile;
    private TextFlowControl textFlowControl;
    private TextEngine textEngine;
    private int threadTime;
    private Thread thread;
    private Label timeLabel;

    public TypingController(Pane pane, TextFlow textFlow, ComboBox<String> selectLanguageButton, Label label) {
        mainPane = pane; //pain for main window
        this.textFlow = textFlow; //text flow to write text
        this.selectLanguageButton = selectLanguageButton; //combo box which contains all available languages
        textEngine = new TextEngine(); //text engine to work with given text
        textFlowControl = new TextFlowControl(textFlow, textEngine); //text flow control to work with text in text flow
        timeLabel = label; //label to present time left

    }

    /**
     * This method initialize all available languages in application
     */
    public void initializeLanguages() {
        //create folder to all available texts
        File folder = new File("dictionary");
        //collect text files from folder in array
        File[] files = folder.listFiles();

        //list which will contains all available languages
        ObservableList<String> fileNames = FXCollections.observableArrayList();
        //check if we have available languages or not
        if (files != null) {
            //iterate all files using for-each loop
            for (File file : files) {
                //if this file is text file, we add to list the name of file in list with available languages
                if (file.isFile()) {
                    String fileName = file.getName().replace(".txt", "");
                    fileNames.add(fileName);
                }
            }
            //add list in this combo box
            selectLanguageButton.setItems(fileNames);
        } else {
            //if files is null, we print to user special message
            System.out.println("Don't have any text files");
        }
        //add action listener to this combo box
        selectLanguageButton.setOnAction(this::selectLanguageClicked);

    }

    /**
     * Method to set text pressed from the combo box
     *
     * @param actionEvent - action event handler
     */
    public void selectLanguageClicked(ActionEvent actionEvent) {
        //get the value of pressed language
        String currentLanguage = selectLanguageButton.getValue();
        try {
            //set current language to text engine
            textEngine.setCurrentLanguage(currentLanguage);
            //variable which contains text from file
            textFromFile = textEngine.getTextFromFile(currentLanguage);
            //set this text to our text flow
            textFlowControl.setText(textFromFile);
        } catch (IOException e) {
            System.out.println("Failed");
        }
    }

    /**
     * Method which handle keys from keyboard
     *
     * @param keyEvent - keypress event handler
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        //get text from pressed key
        String keyText = keyEvent.getText();
        //get code of pressed key
        KeyCode keyCode = keyEvent.getCode();

        //filtering pressed keyboard keys
        if (keyCode == KeyCode.SHIFT) {
            return;
        } else if (keyCode == KeyCode.ALT) {
            return;
        } else if (keyCode == KeyCode.CONTROL) {
            return;
        } else if (keyCode == KeyCode.DELETE) {
            return;
        } else if (keyCode == KeyCode.INSERT) {
            return;
        } else if (keyCode == KeyCode.HOME) {
            return;
        } else if (keyCode == KeyCode.CAPS) {
            return;
        } else if (keyCode == KeyCode.WINDOWS) {
            return;
        } else if (keyCode == KeyCode.TAB) {
            return;
        } else if (keyCode == KeyCode.SPACE) {
            //if key pressed was SPACE, we call special method
            textFlowControl.pressSpace();
        } else if (keyCode == KeyCode.BACK_SPACE) {
            //if key pressed was BACK_SPACE, we should get the character of previous position in the word
            String result = textEngine.getCharacterAtPosition(textFlowControl.getIndexOfCurrentWord(),
                    textFlowControl.getWordPositionCounter() - 1);
            textFlowControl.pressBackSpace(result);
        } else if (keyCode == KeyCode.ESCAPE) {
            //if key pressed was ESCAPE, we should reset all necessary information and stop threads
            textFlowControl.reset();
            textEngine.clearDataCollectorList();
            stopThread();
        } else if (keyCode == KeyCode.P && keyEvent.isControlDown() && keyEvent.isShiftDown()) {
            //if we pressed shortcut Ctrl+Shift+P, we pause our thread for 10 seconds
            waitThread = true;
            System.out.println("Pause for 10 seconds");
        } else if (keyCode == KeyCode.ENTER && keyEvent.isControlDown()) {
            //if we pressed shortcut Ctrl+Enter, we restart our test
            restart();
        } else {
            //check if we have current thread, or not
            if (thread == null) {
                thread = new Thread(this::handleThread);
                thread.start();
            }

            //check if the last character in current word or not
            boolean isLast = textEngine.isLastCharacter(textFlowControl.getIndexOfCurrentWord(),
                    textFlowControl.getWordPositionCounter());
            textFlowControl.checkCharacter(isLast, keyText);
        }
    }

    /**
     * This method handle pressed time buttons
     *
     * @param oldButton - previous button
     * @param newButton - new button
     */
    public void handleButtonPressed(Toggle oldButton, Toggle newButton) {
        //check if new button not equals null and new button is selected we set thread time of our test
        if (newButton != null && newButton.isSelected()) {
            //set new button as selected
            ToggleButton selectedButton = (ToggleButton) newButton;
            //get thread time
            threadTime = Integer.parseInt(selectedButton.getText());
            //get set test time to our text engine
            testTime = Integer.parseInt(selectedButton.getText());
            textEngine.setTime(testTime);
            //set to our time label thread time
            timeLabel.setText(String.valueOf(threadTime));
            //set style to the selected button
            selectedButton.setStyle("-fx-text-fill: #d7b704; -fx-background-color: #323437");
            //print the value of selected button
            System.out.println(threadTime);
        }

        //if old button not equals null and set old button as previous button
        if (oldButton != null) {
            ToggleButton previousButton = (ToggleButton) oldButton;
            //set style to previous button
            previousButton.setStyle("-fx-text-fill: #646669; -fx-background-color: #323437");
        }

    }

    /**
     * Method which handle thread of our application
     */
    public void handleThread() {
        int countTime = 0; //to count time
        textEngine.moveToNextWord(0); //set start time to our first word
        while (!thread.isInterrupted()) {
            //if thread time is -1, we interrupt our thread and show statistics
            if (threadTime == -1) {
                Platform.runLater(() -> {
                    //create pane to show stats from the test
                    Pane statsCreator = new StatsCreator(textFlowControl, textEngine).createStat();
                    //we add to main pane add pane with stats and set time for 15 seconds to show this pane
                    mainPane.getChildren().add(statsCreator);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
                        //after 15 sec we remove from main pane this pane with stats
                        mainPane.getChildren().remove(statsCreator);

                    }));
                    //start this timeLine
                    timeline.play();

                    //set zero to our time label
                    timeLabel.setText("");

                    textEngine.writeWordInfo();
                    textEngine.clearDataCollectorList();

                    //reset all values in text flow control
                    textFlowControl.reset();
                    //set thread as null
                    thread = null;
                });
                break;
            }

            //if we have pause, we stop our thread for 10 seconds
            if (waitThread) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("Can't wait");
                }
                //set wait thread to false, because we wait pause and can continue our test
                waitThread = false;
            }

            //update time label in real time
            Platform.runLater(() -> {
                timeLabel.setText(String.valueOf(threadTime));
            });

            //add elements to list which will contains information about each second such as (time, sec wpm and errors)
            textEngine.addDataElements(new DataCollector(countTime, textFlowControl.calculateWPM(),
                    textFlowControl.calculateErrors()));
            //print current information to test
            textEngine.printElem();

            countTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                break;
            }
            //decrease our thread time from one sec
            threadTime--;
        }
    }


    /**
     * Method which stops our thread
     */
    public void stopThread() {
        //set text to our time label
        timeLabel.setText("");
        //check if thread is null or not
        if (thread != null) {
            thread.interrupt();
            thread = null;
        } else {
            System.out.println("Thread is null");
        }
    }

    /**
     * Method which restart our test
     */
    public void restart() {
        //reset all values from text flow
        textFlowControl.reset();
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        //set values to text label
        timeLabel.setText(String.valueOf(testTime));
        threadTime = testTime;
        //set text from file to text flow
        try {
            textFromFile = textEngine.getTextFromFile(selectLanguageButton.getValue());
            textFlowControl.setText(textFromFile);
        } catch (IOException e) {
            System.out.println("Restart failed");
        }
    }
}


