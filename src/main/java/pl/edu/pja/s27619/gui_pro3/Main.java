package pl.edu.pja.s27619.gui_pro3;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import pl.edu.pja.s27619.gui_pro3.service.TypingController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //pane
        Pane pane = new Pane();
        pane.setPrefHeight(650);
        pane.setPrefWidth(950);
        pane.setStyle("-fx-background-color: #323437");
        pane.setMaxHeight(Double.parseDouble("Infinity"));
        pane.setMaxWidth(Double.parseDouble("Infinity"));

        //title panel
        Label label = new Label();
        label.setLayoutX(19);
        label.setLayoutY(20);
        label.setPrefHeight(95);
        label.setPrefWidth(276);
        label.setText("TypingGame");
        label.setFont(Font.font("Bauhaus 93", 38));
        label.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(label);

        //label to show time
        Label timeLabel = new Label();
        timeLabel.setLayoutX(127);
        timeLabel.setLayoutY(209);
        timeLabel.setPrefHeight(46);
        timeLabel.setPrefWidth(66);
        timeLabel.setFont(Font.font("Bauhaus 93", 19));
        timeLabel.setTextFill(Paint.valueOf("#d7b704"));
        pane.getChildren().add(timeLabel);

        //tools bar to set time
        ToolBar toolBar = new ToolBar();
        toolBar.setLayoutX(286);
        toolBar.setLayoutY(135);
        toolBar.setPrefHeight(53);
        toolBar.setPrefWidth(403);
        toolBar.setStyle("-fx-background-color: #323437");
        ToggleGroup group = new ToggleGroup();

        //button1 for tools bar to set time "15" sec
        ToggleButton button = new ToggleButton();
        button.setLayoutX(1);
        button.setLayoutY(12);
        button.setPrefHeight(26);
        button.setPrefWidth(42);
        button.setStyle("-fx-background-color: #323437");
        button.setText("15");
        button.setTextFill(Paint.valueOf("#646669"));
        button.setFont(Font.font("Bauhaus 93", 19));
        button.setToggleGroup(group);
        toolBar.getItems().add(button);

        //button2 for tools bar to set time "20" sec
        ToggleButton button1 = new ToggleButton();
        button1.setLayoutX(10);
        button1.setLayoutY(12);
        button1.setPrefHeight(26);
        button1.setPrefWidth(42);
        button1.setStyle("-fx-background-color: #323437");
        button1.setText("20");
        button1.setTextFill(Paint.valueOf("#646669"));
        button1.setFont(Font.font("Bauhaus 93", 19));
        button1.setToggleGroup(group);
        toolBar.getItems().add(button1);

        //button2 for tools bar to set time "45" sec
        ToggleButton button2 = new ToggleButton();
        button2.setLayoutX(45);
        button2.setLayoutY(12);
        button2.setPrefHeight(26);
        button2.setPrefWidth(42);
        button2.setStyle("-fx-background-color: #323437");
        button2.setText("45");
        button2.setTextFill(Paint.valueOf("#646669"));
        button2.setFont(Font.font("Bauhaus 93", 19));
        button2.setToggleGroup(group);
        toolBar.getItems().add(button2);

        //button3 for tools bar to set time "60" sec

        ToggleButton button3 = new ToggleButton();
        button3.setLayoutX(80);
        button3.setLayoutY(12);
        button3.setPrefHeight(26);
        button3.setPrefWidth(42);
        button3.setStyle("-fx-background-color: #323437");
        button3.setText("60");
        button3.setTextFill(Paint.valueOf("#646669"));
        button3.setFont(Font.font("Bauhaus 93", 19));
        button3.setToggleGroup(group);
        toolBar.getItems().add(button3);

        //button4 for tools bar to set time "90" sec
        ToggleButton button4 = new ToggleButton();
        button4.setLayoutX(116);
        button4.setLayoutY(12);
        button4.setPrefHeight(26);
        button4.setPrefWidth(42);
        button4.setStyle("-fx-background-color: #323437");
        button4.setText("90");
        button4.setTextFill(Paint.valueOf("#646669"));
        button4.setFont(Font.font("Bauhaus 93", 19));
        button4.setToggleGroup(group);
        toolBar.getItems().add(button4);

        //button5 for tools bar to set time "120" sec
        ToggleButton button5 = new ToggleButton();
        button5.setLayoutX(151);
        button5.setLayoutY(12);
        button5.setPrefHeight(36);
        button5.setPrefWidth(65);
        button5.setStyle("-fx-background-color: #323437");
        button5.setText("120");
        button5.setTextFill(Paint.valueOf("#646669"));
        button5.setFont(Font.font("Bauhaus 93", 19));
        button5.setToggleGroup(group);
        toolBar.getItems().add(button5);

        //button6 for tools bar to set time "300" sec
        ToggleButton button6 = new ToggleButton();
        button6.setLayoutX(186);
        button6.setLayoutY(12);
        button6.setPrefHeight(36);
        button6.setPrefWidth(65);
        button6.setStyle("-fx-background-color: #323437");
        button6.setText("300");
        button6.setTextFill(Paint.valueOf("#646669"));
        button6.setFont(Font.font("Bauhaus 93", 19));
        button6.setToggleGroup(group);
        toolBar.getItems().add(button6);

        pane.getChildren().add(toolBar);

        //combobox to select language
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setLayoutX(411);
        comboBox.setLayoutY(196);
        comboBox.setPrefHeight(26);
        comboBox.setPrefWidth(141);
        comboBox.setPromptText("Select language");
        pane.getChildren().add(comboBox);

        //text flow
        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(127);
        textFlow.setLayoutY(258);
        textFlow.setPrefHeight(179);
        textFlow.setPrefWidth(705);
        textFlow.setStyle("-fx-background-color: #323437");
        textFlow.setTextAlignment(TextAlignment.valueOf("JUSTIFY"));
        pane.getChildren().add(textFlow);

        TypingController typingController = new TypingController(pane, textFlow, comboBox, timeLabel);
        typingController.initializeLanguages();

        //available shortcuts in application
        Rectangle rectangle = new Rectangle(66, 26);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setFill(Paint.valueOf("#646669"));
        rectangle.setLayoutX(279);
        rectangle.setLayoutY(513);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle.setStrokeWidth(0);
        pane.getChildren().add(rectangle);

        Rectangle rectangle1 = new Rectangle(66, 26);
        rectangle1.setArcHeight(15);
        rectangle1.setArcWidth(15);
        rectangle1.setFill(Paint.valueOf("#646669"));
        rectangle1.setLayoutX(378);
        rectangle1.setLayoutY(513);
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle1.setStrokeWidth(0);
        pane.getChildren().add(rectangle1);

        Rectangle rectangle2 = new Rectangle(66, 26);
        rectangle2.setArcHeight(15);
        rectangle2.setArcWidth(15);
        rectangle2.setFill(Paint.valueOf("#646669"));
        rectangle2.setLayoutX(279);
        rectangle2.setLayoutY(552);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle2.setStrokeWidth(0);
        pane.getChildren().add(rectangle2);

        Rectangle rectangle3 = new Rectangle(66, 26);
        rectangle3.setArcHeight(15);
        rectangle3.setArcWidth(15);
        rectangle3.setFill(Paint.valueOf("#646669"));
        rectangle3.setLayoutX(378);
        rectangle3.setLayoutY(552);
        rectangle3.setStroke(Color.BLACK);
        rectangle3.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle3.setStrokeWidth(0);
        pane.getChildren().add(rectangle3);

        Rectangle rectangle4 = new Rectangle(39, 26);
        rectangle4.setArcHeight(15);
        rectangle4.setArcWidth(15);
        rectangle4.setFill(Paint.valueOf("#646669"));
        rectangle4.setLayoutX(476);
        rectangle4.setLayoutY(552);
        rectangle4.setStroke(Color.BLACK);
        rectangle4.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle4.setStrokeWidth(0);
        pane.getChildren().add(rectangle4);

        Rectangle rectangle5 = new Rectangle(66, 26);
        rectangle5.setArcHeight(15);
        rectangle5.setArcWidth(15);
        rectangle5.setFill(Paint.valueOf("#646669"));
        rectangle5.setLayoutX(279);
        rectangle5.setLayoutY(594);
        rectangle5.setStroke(Color.BLACK);
        rectangle5.setStrokeType(StrokeType.valueOf("INSIDE"));
        rectangle5.setStrokeWidth(0);
        pane.getChildren().add(rectangle5);


        //text for available shortcuts
        Text ctrl1 = new Text();
        ctrl1.setText("ctrl");
        ctrl1.setLayoutX(292);
        ctrl1.setLayoutY(531);
        ctrl1.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        ctrl1.setStrokeWidth(0);
        ctrl1.setTextAlignment(TextAlignment.valueOf("CENTER"));
        ctrl1.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        ctrl1.setWrappingWidth(39);
        pane.getChildren().add(ctrl1);

        Text enter = new Text();
        enter.setText("enter");
        enter.setLayoutX(391);
        enter.setLayoutY(531);
        enter.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        enter.setStrokeWidth(0);
        enter.setTextAlignment(TextAlignment.valueOf("CENTER"));
        enter.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        enter.setWrappingWidth(39);
        pane.getChildren().add(enter);

        Text ctrl = new Text();
        ctrl.setText("ctrl");
        ctrl.setLayoutX(292);
        ctrl.setLayoutY(571);
        ctrl.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        ctrl.setStrokeWidth(0);
        ctrl.setTextAlignment(TextAlignment.valueOf("CENTER"));
        ctrl.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        ctrl.setWrappingWidth(39);
        pane.getChildren().add(ctrl);

        Text shift = new Text();
        shift.setText("shift");
        shift.setLayoutX(391);
        shift.setLayoutY(571);
        shift.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        shift.setStrokeWidth(0);
        shift.setTextAlignment(TextAlignment.valueOf("CENTER"));
        shift.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        shift.setWrappingWidth(39);
        pane.getChildren().add(shift);

        Text p = new Text();
        p.setText("p");
        p.setLayoutX(476);
        p.setLayoutY(569);
        p.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        p.setStrokeWidth(0);
        p.setTextAlignment(TextAlignment.valueOf("CENTER"));
        p.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        p.setWrappingWidth(39);
        pane.getChildren().add(p);

        Text esc = new Text();
        esc.setText("esc");
        esc.setLayoutX(292);
        esc.setLayoutY(613);
        esc.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        esc.setStrokeWidth(0);
        esc.setTextAlignment(TextAlignment.valueOf("CENTER"));
        esc.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        esc.setWrappingWidth(39);
        pane.getChildren().add(esc);

        Text text = new Text();
        text.setText("+");
        text.setLayoutX(345);
        text.setLayoutY(530);
        text.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text.setStrokeWidth(0);
        text.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        text.setWrappingWidth(31);
        pane.getChildren().add(text);

        Text text1 = new Text();
        text1.setText("+");
        text1.setLayoutX(345);
        text1.setLayoutY(569);
        text1.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text1.setStrokeWidth(0);
        text1.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text1.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        text1.setWrappingWidth(31);
        pane.getChildren().add(text1);

        Text text2 = new Text();
        text2.setText("+");
        text2.setLayoutX(444);
        text2.setLayoutY(569);
        text2.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text2.setStrokeWidth(0);
        text2.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text2.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        text2.setWrappingWidth(31);
        pane.getChildren().add(text2);

        Text text3 = new Text();
        text3.setText("- restart test");
        text3.setLayoutX(452);
        text3.setLayoutY(532);
        text3.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text3.setStrokeWidth(0);
        text3.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text3.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        pane.getChildren().add(text3);

        Text text4 = new Text();
        text4.setText("- pause");
        text4.setLayoutX(523);
        text4.setLayoutY(569);
        text4.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text4.setStrokeWidth(0);
        text4.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text4.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        pane.getChildren().add(text4);

        Text text5 = new Text();
        text5.setText("- end test");
        text5.setLayoutX(354);
        text5.setLayoutY(612);
        text5.setStrokeType(StrokeType.valueOf("OUTSIDE"));
        text5.setStrokeWidth(0);
        text5.setTextAlignment(TextAlignment.valueOf("CENTER"));
        text5.setFont(Font.font("Berlin Sans FB Demi Bold", 16));
        pane.getChildren().add(text5);


        Scene scene = new Scene(pane, 950, 650);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, typingController::handleKeyPressed);


        group.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    event.consume();
                }
            });
        });
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            typingController.handleButtonPressed(oldValue, newValue);
        });

        primaryStage.setTitle("TypingClub");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
