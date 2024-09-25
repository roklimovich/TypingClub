package pl.edu.pja.s27619.gui_pro3.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TextEngine {

    private List<WordInfo> pattern; //list which will contains starting text
    private ArrayList<DataCollector> dataCollectorList = new ArrayList<>(); //list which contains necessary info
    private List<WordInfo> copyOfPattern = new LinkedList<>(); //supporting list to save information about words
    private String currentLanguage; //contains information about current language
    private int time; //contains time of test

    /**
     * This method generate 30 random words from file
     *
     * @param filename - text file name
     * @return - String with text
     * @throws IOException - throws exception if something is going wrong
     */
    public String getTextFromFile(String filename) throws IOException {
        int maxWordCount = 30; //max words to set
        pattern = new LinkedList<>(); //list which contains starting text
        Path path = Paths.get("dictionary/" + filename + ".txt");//get path where we should find necessary text file
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8); //read file in List of type String
        Random random = new Random(); //to generate random index
        //using for loop iterate elements while index of loop not equals 30 words
        for (int i = 0; i <= maxWordCount; i++) {
            int randomIndex = random.nextInt(allLines.size());//generate random index
            //get words from list of text with given index and add to our pattern list with starting text
            pattern.add(new WordInfo(allLines.get(randomIndex), null, null));
        }
        return pattern.stream().map(WordInfo::getWord).collect(Collectors.joining(" "));
    }

    /**
     * This method return element from given word and index
     *
     * @param currentIndexOfWord - int variable which contains index of current word
     * @param position           - int variable which contains position in this word
     * @return - String element of given word and given position
     */
    public String getCharacterAtPosition(int currentIndexOfWord, int position) {
        String currentWord = pattern.get(currentIndexOfWord).getWord(); //get word from current position in pattern
        //check if position greater than word length, we return empty string
        if (position > currentWord.length() - 1) {
            return "";
        }
        //return character from give word and position
        return String.valueOf(currentWord.charAt(position));
    }

    /**
     * This method checks last character of word
     *
     * @param currentIndexOfWord - int variable which contains index of current word
     * @param position           - int variable which contains position in the word
     * @return - true or false
     */
    public boolean isLastCharacter(int currentIndexOfWord, int position) {
        String currentWord = pattern.get(currentIndexOfWord).getWord(); //get word from current position in pattern
        //if position is greater than word last character, we return true, if not false
        if (position > currentWord.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method add elements to data collector list, which contains information about the test
     *
     * @param dataCollector - object of class DataCollector
     */
    public void addDataElements(DataCollector dataCollector) {
        dataCollectorList.add(dataCollector);
    }


    /**
     * This method checks, if we can move to next word or not, and set end and start time to calculate statistics
     * for each word
     *
     * @param index - int variable which contains information about current index word
     * @return - true or false
     */
    public boolean moveToNextWord(int index) {
        /*
         * if index is greater than size of our list which contains starting text, we set end time for previous word,
         * copy all information from pattern in additional list, clear our pattern and return false
         */
        if (index >= pattern.size()) {
            pattern.get(index - 1).setEndTime(LocalTime.now());
            copyOfPattern.addAll(pattern);
            pattern.clear();
            return false;
        }

        //if index not equals zero, we set for previous word end time
        if (index != 0) {
            pattern.get(index - 1).setEndTime(LocalTime.now());
        }

        //also set start time for word from given index and return true
        pattern.get(index).setStartTime(LocalTime.now());

        return true;

    }


    /**
     * This method write info about each word from test in text file and save it
     */
    public void writeWordInfo() {
        //get current date and set this date as a name of our text file
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-sss");
        String stamp = dateFormat.format(new Date());
        String fileName = stamp + ".txt";

        try {
            //create file writer to save our information
            FileWriter fileWriter = new FileWriter(fileName);

            //using for-each loop iterate all list of pattern and calculate necessary info
            for (WordInfo word : pattern) {
                //check if end time is null, we break the loop
                if (word.getEndTime() == null) {
                    break;
                } else {
                    //find difference from one time to another
                    double difference = word.getStartTime().until(word.getEndTime(), ChronoUnit.MILLIS);
                    //get amount of letters in word
                    int amountOfLetters = word.getWord().length();
                    //calculate result
                    int result = (int) (amountOfLetters / difference * 6000);
                    //write this info in our file writer
                    fileWriter.write(word.getWord() + " -> " + result + " wpm" + "\n");
                }
            }
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("File writer was broken");
        }

    }

    /**
     * This method calculate average WPM from all test
     *
     * @return - int which contains wpm
     */
    public int averageWPM() {
        int counter = 0;
        int speed = 0;
        //using loop iterate all elements from data collector list and add speed from each object of data collector list
        for (DataCollector collector : dataCollectorList) {
            speed += collector.getSpeed();
            counter++;
        }
        return speed / counter;
    }

    /**
     * This method prints information about each second in real time, while thread is working (just to test)
     */
    public void printElem() {
        //iterate all elements from data collector list and print each element
        for (DataCollector collector : dataCollectorList) {
            System.out.println("Time: " + collector.getTime() + " Speed: " + collector.getSpeed() + " Error: " +
                    collector.getError());
        }
    }

    public void clearDataCollectorList() {
        dataCollectorList.clear();
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<DataCollector> getDataCollectorList() {
        return dataCollectorList;
    }
}
