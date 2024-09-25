package pl.edu.pja.s27619.gui_pro3.service;

import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import pl.edu.pja.s27619.gui_pro3.service.TextEngine;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TextFlowControl {
    private TextEngine textEngine;
    private TextFlow textFlowElement;
    private List<Text> textAsChars;
    private int enteredCharCounter; //counter for entered characters
    private int speedCharCounter; //counter for correct chars which we use to calculate speed
    private int correctCharCounter; //counter for correct char
    private int speedErrorCounter; //counter for error chars which we use to calculate speed
    private int errorCounter; //counter for error chars
    private int omittedCharCounter; //counter for omitted chars
    private int extraCharCounter; //counter for extra chars
    private int currentPosition; //current position of text flow
    private boolean checkedCharacter; //variable to check each character from word
    private int indexOfCurrentWord; //index of current word
    private int wordPositionCounter; //index of position in word


    public TextFlowControl(TextFlow textFlow, TextEngine textEngine) {
        this.textEngine = textEngine;
        textAsChars = new LinkedList<>();
        textFlowElement = textFlow;
        wordPositionCounter = 0;
        currentPosition = 0;
        indexOfCurrentWord = 0;
        speedCharCounter = 0;
        errorCounter = 0;
        checkedCharacter = false;
        omittedCharCounter = 0;
        extraCharCounter = 0;
        enteredCharCounter = 0;
    }

    /**
     * This method set text to our text flow
     *
     * @param newText - text from file
     */
    public void setText(String newText) {
        //clear all elements from text flow
        textFlowElement.getChildren().clear();
        //clear supporting list which contains each letter from word
        textAsChars.clear();
        //convert String to char Array
        char[] wordToChar = newText.toCharArray();
        //using for-each loop iterate all element and convert char to type Text
        for (char character : wordToChar) {
            Text charToText = new Text(String.valueOf(character));
            //add this variable of type Text to our list
            textAsChars.add(charToText);
            //set font and fill for each letter
            charToText.setFont(Font.font("Monospace", 20));
            charToText.setFill(Paint.valueOf("#646669"));
        }
        //make observable list and add to this list, list of type Text to set words in text flow
        ObservableList observableList = textFlowElement.getChildren();
        observableList.addAll(textAsChars);
    }


    /**
     * This method we use to check character given from user with the character, which we have in our starting Text
     *
     * @param isLastCharacter   - boolean variable which contains info about last character
     * @param keyBoardCharacter - String variable which contains text from key, which was pressed
     */
    public void checkCharacter(boolean isLastCharacter, String keyBoardCharacter) {
        //if it not last character
        if (!isLastCharacter) {
            //we get text from given position with the given keyboard character
            if (textAsChars.get(currentPosition).getText().equals(keyBoardCharacter)) {
                checkedCharacter = true;
                repaintCharacter(currentPosition); //repaint character from given position
                wordPositionCounter++; //add +1 to word position counter
                currentPosition++; //add +1 to current position counter
                correctCharCounter++; //add +1 to correct char counter
                speedCharCounter++; //add +1 to speed char counter
                enteredCharCounter++; //add +1 to entered char counter

            } else if (!textAsChars.get(currentPosition).getText().equals(keyBoardCharacter)) {
                checkedCharacter = false;
                textAsChars.get(currentPosition).setText(keyBoardCharacter);
                repaintCharacter(currentPosition); //repaint character from given position
                wordPositionCounter++; //add +1 to word position counter
                currentPosition++; //add +1 to current position counter
                errorCounter++; //add +1 to error counter
                speedErrorCounter++; //add +1 to speed error count
                enteredCharCounter++; //add +1 to entered char counter
            }

        } else {
            //if it is last character and user try to write something, we add these elements to our text flow
            Text character = new Text(keyBoardCharacter);
            textAsChars.add(currentPosition, character);
            textFlowElement.getChildren().add(currentPosition, character);
            character.setFont(Font.font("Monospace", 20)); //change font and size
            character.setFill(Paint.valueOf("FF8000")); //change colour
            wordPositionCounter++; //add +1 to word position counter
            currentPosition++; //add +1 to current position counter
            extraCharCounter++; //add +1 to extra char counter
            enteredCharCounter++; //add +1 to entered character counter
        }

    }

    /**
     * This method implements pressing Space in our app
     */
    public void pressSpace() {
        //if current position is greater or equals the size of given text we set new text to our text flow
        if (currentPosition >= textAsChars.size()) {
            if (!textEngine.moveToNextWord(indexOfCurrentWord + 1)) {
                try {
                    setText(textEngine.getTextFromFile(textEngine.getCurrentLanguage()));
                } catch (IOException e) {
                    System.out.println("New text wasn't added");
                }
                textEngine.moveToNextWord(0);
                //reset the counters
                currentPosition = 0;
                wordPositionCounter = 0;
                indexOfCurrentWord = 0;
            }
            /*
             * if on current position we have space, we add +1 to index of current word, reset position counter, and
             * add +1 to current position
             */
        } else if (textAsChars.get(currentPosition).getText().equals(" ")) {
            indexOfCurrentWord++;
            wordPositionCounter = 0;
            currentPosition++;
            textEngine.moveToNextWord(indexOfCurrentWord);
            /*
             * if on current position we don't have space, we set on this position text to another colour, and we add
             * +1 to index word position, add +1 to current position, add +1 to omitted char counter, add +1 to entered
             * char counter
             */
        } else if (!textAsChars.get(currentPosition).getText().equals(" ")) {
            textAsChars.get(currentPosition).setFill(Paint.valueOf("#000000"));
            wordPositionCounter++;
            currentPosition++;
            omittedCharCounter++;
            enteredCharCounter++;
        }
    }

    /**
     * This method implements pressing BackSpace in our app
     *
     * @param result - String variable which contains text of previous letter
     */
    public void pressBackSpace(String result) {
        /*
         * if result is empty, we remove -1 from current position, remove -1 from word position counter, remove the
         * previous elem from text flow and also remove this elem from our list of text
         */
        if (result.equals("")) {
            currentPosition--;
            wordPositionCounter--;
            textFlowElement.getChildren().remove(currentPosition);
            textAsChars.remove(currentPosition);
        } else {
            /*
             * if result isn't empty, we remove -1 from current position, remove -1 from word position counter, set
             * result in current position of text flow and repaint this character
             */
            wordPositionCounter--;
            currentPosition--;
            textAsChars.get(currentPosition).setText(result);
            textAsChars.get(currentPosition).setFill(Paint.valueOf("#646669"));
        }
    }

    /**
     * This method repaint character
     *
     * @param currentPosition - int variable which contains current position of letter
     */
    public void repaintCharacter(int currentPosition) {
        //if checked character true, we repaint text to yellow, if not, repaint to red
        if (checkedCharacter) {
            textAsChars.get(currentPosition).setFill(Paint.valueOf("#d7b704"));
        } else {
            textAsChars.get(currentPosition).setFill(Paint.valueOf("#FF0000"));
        }
    }

    /**
     * This method reset all values from text flow control
     */
    public void reset() {
        wordPositionCounter = 0;
        currentPosition = 0;
        indexOfCurrentWord = 0;
        speedCharCounter = 0;
        speedErrorCounter = 0;
        omittedCharCounter = 0;
        extraCharCounter = 0;
        enteredCharCounter = 0;
        correctCharCounter = 0;
        errorCounter = 0;
        textFlowElement.getChildren().clear();
    }

    /**
     * Method to calculate wpm
     *
     * @return - int variable which contains wpm
     */
    public int calculateWPM() {
        //speed char counter we divide by average letters in the word and multiply by 60 sec
        double speed = speedCharCounter / 5.0 * 60.0;
        int result = (int) speed;
        //reset speed char counter
        speedCharCounter = 0;
        return result;
    }

    /**
     * Method to calculate errors
     *
     * @return - int variable which contains amount of errors
     */
    public int calculateErrors() {
        int result = speedErrorCounter;
        speedErrorCounter = 0;
        return result;
    }

    /**
     * Method to calculate accuracy
     *
     * @return - int variable which contains acc
     */
    public int calculateAcc() {
        //correct character we divide by entered characters and multiply by 100
        double result = (double) (correctCharCounter) / enteredCharCounter * 100;
        return (int) result;
    }


    public int getIndexOfCurrentWord() {
        return indexOfCurrentWord;
    }

    public int getWordPositionCounter() {
        return wordPositionCounter;
    }

    public int getCharCount() {
        return correctCharCounter;
    }

    public int getErrorCount() {
        return errorCounter;
    }

    public int getOmittedCharCounter() {
        return omittedCharCounter;
    }

    public int getExtraCharCounter() {
        return extraCharCounter;
    }

}