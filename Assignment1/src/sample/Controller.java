package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Slider;

import javax.swing.*;
import java.io.File;

import static java.awt.Color.BLACK;


public class Controller {

        public TabPane tabControlPane;

        @FXML
        public ImageView grayImageView;

        @FXML
        public Button grayscaleChange;

        @FXML
        public ImageView redView;

        @FXML
        public ImageView blueView;

        @FXML
        public ImageView greenView;

        @FXML
        public Label imageSize;

        @FXML
        public Label fileName;

        @FXML
        public ImageView hueChange;

        @FXML
        public Label imageWidth;

        @FXML
        public Label imageHeight;

        @FXML
        public ImageView satChange;

        @FXML
        public ImageView exposureChange;

        @FXML
        public Slider changeBright;

        @FXML
        public Slider changeSat;

        @FXML
        public Slider changeHue;

        @FXML
        public Tab rgbTab;

        @FXML
        public MenuItem rgbChange;

        @FXML
        public ImageView componentChoose;

        @FXML
        public Label blueColor;

        @FXML
        public Label greenColor;

        @FXML
        public Label redColor;

        @FXML
        public Label brightness;

        @FXML
        public Label saturation;

        @FXML
        public Label hue;

        @FXML
        private ImageView imageView;

        @FXML
        private MenuBar menuBar;

        @FXML
        private Menu menu;

        public Color col;

        @FXML
        public void initialize() {
                blueColor.setVisible(false);
                redColor.setVisible(false);
                greenColor.setVisible(false);
                saturation.setVisible(false);
                brightness.setVisible(false);
                hue.setVisible(false);
                rgbTab.setDisable(true);
                System.out.println(System.currentTimeMillis());

                imageView.setOnMouseClicked(e -> {
                        PixelReader pr = imageView.getImage().getPixelReader();
                        // Integer.parseInt(spaces.getText()));
                        //doesn't like this line of code
                        // Color col = pr.getColor(Integer.parseInt(String.valueOf(e.getX())),Integer.parseInt(String.valueOf(e.getY())));
                        int width = (int) e.getX();
                        int height = (int) e.getY();
                        col = pr.getColor(width, height);


                        System.out.println("Hue: " + col.getHue()); //scale online
                        System.out.println("Saturation: " + col.getSaturation()); //between 0 and 1
                        System.out.println("Brightness: " + col.getBrightness()); //between 0 and 1
                });
        }



        public void displayDetails(MouseEvent mouseEvent) {
                blueColor.setVisible(true);
                redColor.setVisible(true);
                greenColor.setVisible(true);
                saturation.setVisible(true);
                brightness.setVisible(true);
                hue.setVisible(true);
                blueColor.setText("Blue:" + String.format("%.2f", col.getBlue()));
                redColor.setText("Red: " +  String.format("%.2f", col.getRed()));
                greenColor.setText("Green: " +  String.format("%.2f", col.getGreen()));
                saturation.setText("Saturation: " +  String.format("%.2f" , col.getSaturation()));
                brightness.setText("Brightness: " +  String.format("%.2f", col.getBrightness()));
                hue.setText("Hue: " +  String.format("%.2f", col.getHue()));

        }

        public void openImage(ActionEvent actionEvent) {
                //https://www.youtube.com/watch?v=AS0NhRKyRa4&t=15s
                System.out.println("Open file");
                FileChooser fileChooser = new FileChooser();

                File file = fileChooser.showOpenDialog(stage);
                System.out.println("Chosen file: " + file);
                Image chosenImage = new Image(file.toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), false, true);

                imageView.setImage(chosenImage);
                grayImageView.setImage(chosenImage);
                hueChange.setImage(chosenImage);
                satChange.setImage(chosenImage);
                exposureChange.setImage(chosenImage);
                componentChoose.setImage(chosenImage);


                System.out.println("Image displayed.");

                //file choosers require a reference to the stage to work.
                //will have to pass to the stage.

                //Red View
                Image redImage = imageView.getImage();
                PixelReader pixelReader = redImage.getPixelReader();
                WritableImage writableImage = new WritableImage(
                        (int) redImage.getWidth(),
                        (int) redImage.getHeight());
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                for (int y = 0; y < redImage.getHeight(); y++) {
                        for (int x = 0; x < redImage.getWidth(); x++) {
                                Color color = pixelReader.getColor(x, y);

                                pixelWriter.setColor(x, y, new Color(color.getRed(), 0.0, 0.0, 1.0));
                        }

                        redView.setImage(writableImage);
                }

                //Blue View
                Image blueImage = imageView.getImage();
                PixelReader pixelReader2 = redImage.getPixelReader();
                WritableImage writableImage2 = new WritableImage(
                        (int) blueImage.getWidth(),
                        (int) blueImage.getHeight());
                PixelWriter pixelWriter2 = writableImage2.getPixelWriter();

                for (int y = 0; y < blueImage.getHeight(); y++) {
                        for (int x = 0; x < blueImage.getWidth(); x++) {
                                Color color = pixelReader2.getColor(x, y);

                                pixelWriter2.setColor(x, y, new Color(0.0, 0.0, color.getBlue(), 1.0));
                        }

                        blueView.setImage(writableImage2);
                }

                //Green View
                Image greenImage = imageView.getImage();
                PixelReader pixelReader3 = greenImage.getPixelReader();
                WritableImage writableImage3 = new WritableImage(
                        (int) greenImage.getWidth(),
                        (int) greenImage.getHeight());
                PixelWriter pixelWriter3 = writableImage3.getPixelWriter();

                for (int y = 0; y < greenImage.getHeight(); y++) {
                        for (int x = 0; x < greenImage.getWidth(); x++) {
                                Color color = pixelReader3.getColor(x, y);

                                pixelWriter3.setColor(x, y, new Color(0.0, color.getGreen(), 0.0, 1.0));
                        }

                        greenView.setImage(writableImage3);
                }

                //hue Change
                //https://java-buddy.blogspot.com/2012/12/create-and-adjust-color-using-hue.html
//                adjHue = sliderHue.valueProperty().doubleValue();
//
//                Image hueImage = imageView.getImage();
//                PixelReader pixelReader4 = hueImage.getPixelReader();
//                WritableImage writableImage4 = new WritableImage(
//                        (int) hueImage.getWidth(),
//                        (int) hueImage.getHeight());
//                PixelWriter pixelWriter4 = writableImage4.getPixelWriter();
//
//                for (int y = 0; y < hueImage.getHeight(); y++){
//                        for (int x = 0; x < hueImage.getWidth(); x++){
//                                Color color = pixelReader.getColor(x, y);
//                                pixelWriter.setColor(x, y, color);
//
//                                double hue = color.getHue() + adjHue;
//                                if(hue > 360.0){
//                                        hue = hue - 360;
//                                }else if(hue < 0.0){
//                                        hue = hue + 360.0;
//                                }

        }

        private Stage stage;

        public void init(Stage stage) {
                this.stage = stage;
        }

        public void quit(ActionEvent actionEvent) {
                System.exit(0);
        }

        public void grayscaleChange(MouseEvent mouseEvent) {
                Image image = imageView.getImage();
                PixelReader pixelReader = image.getPixelReader();
                System.out.println("Image Width: " + image.getWidth());
                System.out.println("Image Height: " + image.getHeight());
                // Create WritableImage
                WritableImage writableImage = new WritableImage(
                        (int) image.getWidth(),
                        (int) image.getHeight());
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                                Color color = pixelReader.getColor(x, y);
                                double r = color.getRed();
                                double g = color.getGreen();
                                double b = color.getBlue();
                                int red = (int) ((r + g + b) / 3 * 255);
                                int green = (int) ((r + g + b) / 3 * 255);
                                int blue = (int) ((r + g + b) / 3 * 255);
                                color = Color.rgb(red, green, blue);
                                pixelWriter.setColor(x, y, color);
                        }
                }
                grayImageView.setImage(writableImage);

        }

        public void adjustSaturation(MouseEvent mouseEvent) {

        }

        public void adjustHue(MouseEvent mouseEvent) {

        }

        public void adjustBrightness(MouseEvent mouseEvent) {

        }


        //HUE

        public void increaseHue(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust();
                c.setHue(0.3);

                hueChange.setEffect(c);
        }

        public void decreaseHue(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust();
                c.setHue(-0.3);

                hueChange.setEffect(c);


        }

        //SATURATION

        public void increaseSat(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust();
                c.setSaturation(0.3);

                satChange.setEffect(c);

        }

        public void decreaseSat(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust();
                c.setSaturation(-0.3);

                satChange.setEffect(c);

        }

        //BRIGHTNESS

        public void increaseBright(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust(); // creating the instance of the ColorAdjust effect.
                c.setBrightness(0.3); // setting the brightness of the color.

                exposureChange.setEffect(c);
                exposureChange.setEffect(c);
        }

        public void decreaseBright(MouseEvent mouseEvent) {

                ColorAdjust c = new ColorAdjust(); // creating the instance of the ColorAdjust effect.
                c.setBrightness(-0.3); // setting the brightness of the color.
                exposureChange.setEffect(c);

        }

        public void changeBright(MouseEvent mouseEvent) {
                changeBright.valueProperty().addListener(observable -> {
                        if (imageView.getImage() == null) return;

                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setBrightness(changeBright.getValue());
                        exposureChange.setEffect(colorAdjust);
                });
        }

        public void changeSat() {
                changeSat.valueProperty().addListener(observable -> {
                        if (imageView.getImage() == null) return;

                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setSaturation(changeSat.getValue());
                        satChange.setEffect(colorAdjust);
                });
        }

        public void changeHue() {
                changeHue.valueProperty().addListener(observable -> {
                        if (imageView.getImage() == null) return;

                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setHue(changeHue.getValue());
                        hueChange.setEffect(colorAdjust);
                });
        }

        public void rgbChannels(ActionEvent actionEvent) {
                rgbTab.setDisable(false);
                tabControlPane.getSelectionModel().select(rgbTab);
        }

        public void blackIdentify(MouseEvent mouseEvent) {

        }

        public void identifyComp(MouseEvent mouseEvent) {
                Image image = imageView.getImage();
                PixelReader pixelReader = image.getPixelReader();
                ColorAdjust colorAdjust = new ColorAdjust();
                WritableImage writableImage = new WritableImage(
                        (int) image.getWidth(),
                        (int) image.getHeight());
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                                Color color = pixelReader.getColor(x, y);
                                if (color.getGreen() <= color.getRed() + color.getBlue()) {
                                        pixelWriter.setColor(x, y,Color.BLACK);
                                }

                        }
                }

                componentChoose.setImage(writableImage);
        }
}
             //   }
//        }
//}




