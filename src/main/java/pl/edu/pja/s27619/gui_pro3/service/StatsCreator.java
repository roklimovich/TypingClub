package pl.edu.pja.s27619.gui_pro3.service;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class StatsCreator {

    private TextFlowControl textFlowControl;
    private TextEngine textEngine;

    public StatsCreator(TextFlowControl textFlowControl, TextEngine textEngine) {
        this.textFlowControl = textFlowControl;
        this.textEngine = textEngine;
    }

    public Pane createStat() {

        //pane
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(120);
        pane.setPrefHeight(352);
        pane.setPrefWidth(950);
        pane.setStyle("-fx-background-color: #323437");

        //label for wpm
        Label wpmLabel = new Label("WPM");
        wpmLabel.setLayoutX(22);
        wpmLabel.setLayoutY(27);
        wpmLabel.setPrefWidth(58);
        wpmLabel.setPrefHeight(46);
        wpmLabel.setFont(Font.font("Monospace", 18));
        wpmLabel.setTextFill(Paint.valueOf("#646669"));
        pane.getChildren().add(wpmLabel);

        //label for acc
        Label accLabel = new Label("ACC");
        accLabel.setLayoutX(22);
        accLabel.setLayoutY(130);
        accLabel.setPrefWidth(58);
        accLabel.setPrefHeight(46);
        accLabel.setFont(Font.font("Monospace", 18));
        accLabel.setTextFill(Paint.valueOf("#646669"));
        pane.getChildren().add(accLabel);

        //label for test type
        Label testTypeLabel = new Label("Test type");
        testTypeLabel.setLayoutX(22);
        testTypeLabel.setLayoutY(248);
        testTypeLabel.setPrefWidth(114);
        testTypeLabel.setPrefHeight(46);
        testTypeLabel.setFont(Font.font("Monospace", 18));
        testTypeLabel.setTextFill(Paint.valueOf("#646669"));
        pane.getChildren().add(testTypeLabel);

        //label for characters
        Label characters = new Label("Characters");
        characters.setLayoutX(191);
        characters.setLayoutY(248);
        characters.setPrefWidth(114);
        characters.setPrefHeight(46);
        characters.setFont(Font.font("Monospace", 18));
        characters.setTextFill(Paint.valueOf("#646669"));
        pane.getChildren().add(characters);

        //label where we will set wpm from our test
        Label wpmText = new Label();
        wpmText.setText(String.valueOf(textEngine.averageWPM()));
        wpmText.setLayoutX(22);
        wpmText.setLayoutY(73);
        wpmText.setPrefWidth(86);
        wpmText.setPrefHeight(46);
        wpmText.setFont(Font.font("Monospace", 18));
        wpmText.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(wpmText);

        //label where we will set acc from our test
        Label accText = new Label();
        accText.setText(textFlowControl.calculateAcc() + "%");
        accText.setLayoutX(22);
        accText.setLayoutY(178);
        accText.setPrefWidth(86);
        accText.setPrefHeight(46);
        accText.setFont(Font.font("Monospace", 18));
        accText.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(accText);

        //label where we will set test type from our test
        Label testTypeText = new Label();
        testTypeText.setText("Time " + textEngine.getTime() + "\n" + textEngine.getCurrentLanguage());
        testTypeText.setLayoutX(22);
        testTypeText.setLayoutY(294);
        testTypeText.setPrefWidth(141);
        testTypeText.setPrefHeight(46);
        testTypeText.setFont(Font.font("Monospace", 18));
        testTypeText.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(testTypeText);

        //label where we will set characters from our test
        Label characterText = new Label();
        characterText.setText(textFlowControl.getCharCount() + "/" + textFlowControl.getErrorCount() + "/" +
                textFlowControl.getExtraCharCounter() + "/" + textFlowControl.getOmittedCharCounter());
        characterText.setLayoutX(191);
        characterText.setLayoutY(294);
        characterText.setPrefWidth(194);
        characterText.setPrefHeight(46);
        characterText.setFont(Font.font("Monospace", 18));
        characterText.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(characterText);

        //pane where we will show line chart from our test
        Pane paneForStats = new Pane();
        paneForStats.setLayoutX(135);
        paneForStats.setLayoutY(32);
        paneForStats.setPrefWidth(761);
        paneForStats.setPrefHeight(221);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        //create line chart
        LineChart lineChart = new LineChart(xAxis, yAxis);
        //create line series
        XYChart.Series dataSeries = new XYChart.Series();

        //using for-each loop to get values from Data Collector list and add to our line chart
        for (DataCollector collector : textEngine.getDataCollectorList()) {
            dataSeries.getData().add(new XYChart.Data(collector.getTime(), collector.getSpeed()));
            //if we have error, we create new series for each second if it necessary
            if (collector.getError() != 0) {
                XYChart.Series errorSeries = new XYChart.Series();
                errorSeries.getData().add(new XYChart.Data(collector.getTime(), collector.getError()));
                lineChart.getData().add(errorSeries);
            }
        }
        lineChart.getData().addAll(dataSeries);
        lineChart.setPrefWidth(705);
        lineChart.setPrefHeight(185);
        paneForStats.getChildren().add(lineChart);
        pane.getChildren().add(paneForStats);

        return pane;
    }
}
