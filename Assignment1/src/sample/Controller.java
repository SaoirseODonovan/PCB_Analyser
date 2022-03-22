package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Slider;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Controller {

    @FXML
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
    public Label blueVal;

    @FXML
    public Label greenVal;

    @FXML
    public Label redVal;

    @FXML
    public Label hueVal;

    @FXML
    public Label brightVal;

    @FXML
    public Label satVal;

    @FXML
    public Label compCounter;

    @FXML
    public Button union;

    @FXML
    private ImageView imageView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu;

    public Color col;

    //array of pixels within chosen image

    public int[] arrayOfPixels;

    public PixelReader pixelReader;

    // arraylist of disjoint sets

    ArrayList<Integer> arrayListOfDisjointSets = new ArrayList<>();

    ArrayList<Integer> toBeDeleted = new ArrayList<>(arrayListOfDisjointSets);


    @FXML
    public void initialize() {
//                int[] toBeDeletedArray = new int[0];
//                for (int i = 0; i < arrayListOfDisjointSets.size(); i++){
//                    toBeDeletedArray[i] = arrayListOfDisjointSets.get(i);
//                    System.out.println(i);
//                }
//                // Setting default value to empty array
//                System.out.println("After Clearing Array:");
//                Arrays.fill(toBeDeletedArray, 0);
//                for (int i : toBeDeletedArray) {
//                    System.out.println(i);
//                }

        rgbTab.setDisable(true);
            }



//                blueColor.setVisible(false);
//                redColor.setVisible(false);
//                greenColor.setVisible(false);
//                saturation.setVisible(false);
//                brightness.setVisible(false);
//                hue.setVisible(false);



    public void openImage(ActionEvent actionEvent) {
        //file chooser to pick image and display it
        //system.out.println to check if working
        System.out.println("Open file");
        FileChooser fileChooser = new FileChooser();

        //file choosers require a reference to the stage to work
        //pass to the stage
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("Chosen file: " + file);
        Image chosenImage = new Image(file.toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), false, true);

        //fill all image view's with image
        imageView.setImage(chosenImage);
        grayImageView.setImage(chosenImage);
        hueChange.setImage(chosenImage);
        satChange.setImage(chosenImage);
        exposureChange.setImage(chosenImage);
        componentChoose.setImage(chosenImage);


        System.out.println("Image displayed.");

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

    }

    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //black and white conversion

    public void grayscaleChange(MouseEvent mouseEvent) {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        System.out.println("Image Width: " + image.getWidth());
        System.out.println("Image Height: " + image.getHeight());
        //create WritableImage
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
                //cast int here
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

    //showing chosen component with similar pixel colour range in black
    public void blackIdentify(MouseEvent mouseEvent) {

        imageView.setOnMouseClicked(e -> {
            int x = (int) e.getX();
            int y = (int) e.getY();
            pixelReader = imageView.getImage().getPixelReader();

            //labels displaying different values in relation to a chosen pixel, to two significant figures so that values aren't extensive
            redVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getRed()));
            greenVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getGreen()));
            satVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getSaturation()));
            brightVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getBrightness()));
            hueVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getHue()));
            blueVal.setText(String.format("%.2f", pixelReader.getColor(x, y).getBlue()));

            Image image = imageView.getImage();
            PixelReader pixelReader = image.getPixelReader();
            WritableImage writableImage = new WritableImage(
                    (int) image.getWidth(),
                    (int) image.getHeight());
            PixelWriter pixelWriter = writableImage.getPixelWriter();

            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            arrayOfPixels = new int[width * height];

            int i = 0;

            for (int a = 0; a < image.getHeight(); a++) {
                for (int b = 0; b < image.getWidth(); b++) {

                    Color color = pixelReader.getColor(b, a);
                    var Red = color.getRed();
                    var Blue = color.getBlue();
                    var Green = color.getGreen();
                    var Hue = color.getHue();
                    var redLabel = Double.parseDouble(redVal.getText());
                    var greenLabel = Double.parseDouble(greenVal.getText());
                    var blueLabel = Double.parseDouble(blueVal.getText());
                    var hueLabel = Double.parseDouble(hueVal.getText());

                    //comparing values to identify components and display pixels of similar value range as black
                    if ((Red > redLabel - 0.40) && (Red < redLabel + 0.40)
                            && (Blue > blueLabel - 0.10) && (Blue < blueLabel + 0.10)
                            && (Green > greenLabel - 0.10) && (Green < greenLabel + 0.10)
                            && (Hue > hueLabel - 2) && (Hue < hueLabel + 2)) {
                        pixelWriter.setColor(b, a, Color.BLACK);

                        arrayOfPixels[i] = i;


                    } else {

                        //everything else is coloured white
                        pixelWriter.setColor(b, a, Color.WHITE);
                        arrayOfPixels[i] = 0;
                    }
                    i++;


                }
            }
            componentChoose.setImage(writableImage);
        });


    }

    public void reduceNoise() {



    }

    //union
    public void unionUse() {
        Image image = componentChoose.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        for (int c = 0; c < height; c++) {
            for (int d = 0; d < width; d++) {
                //going through height and width of image
                //going through array of pixels and performing math, on board
                //checking if not equal to zero, if not it will check the pixel beside it and perform same check
                //if its also not equal to 0, union them
                if (arrayOfPixels[c * width + d] != 0 && arrayOfPixels[c * width + d + 1] != 0) {
                    union(arrayOfPixels, c * width + d, c * width + d + 1);
                }
                //same but checks the pixel below, this is done by + width
                if (c < height - 1 && arrayOfPixels[c * width + d] != 0 && arrayOfPixels[c * width + d + width] != 0) {
                    union(arrayOfPixels, c * width + d, c * width + d + width);
                }
            }

        }


    }


    public void unionFind(MouseEvent mouseEvent) {

    }

    public void identifyComp(MouseEvent mouseEvent) {
    }

    //Iterative version of find
    public static int find(int[] arrayOfPixels, int id) {
        while (arrayOfPixels[id] != id) id = arrayOfPixels[id];
        return id;
    }


    //Quick union of disjoint sets containing elements p and q (Version 2)
    public static void union(int[] a, int p, int q) {
        a[find(a, q)] = find(a, p); //The root of q is made reference the root of p
    }


    public void rectangleMark(MouseEvent mouseEvent) {

        //i must make an array list using the java one and just have store the disjoint sets if they match the conditions from the if statement
        //then in the foreach loop rather than having arrayofpixels, put name of the arraylist made

        //counter
        int pixelCount = 0;

        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        //arrayListOfDisjointSets.clear();

        for (int j = 0; j < arrayOfPixels.length; j++) {
            //arrayOfPixels[j], iterating through list and getting value at each position
            //goes through arrayListOfDisjointSets, which contains an id for every set which is the the root, this if statement
            //makes sure that duplicate roots aren't stored
            if (arrayOfPixels[j] != 0 && !arrayListOfDisjointSets.contains(find(arrayOfPixels, j))) {
                //adds root id if not there already
                arrayListOfDisjointSets.add(find(arrayOfPixels, j));
            }
        }


            for (int id : arrayListOfDisjointSets) { //foreach loop going through array list of disjoint set

                pixelCount++; //used to count how many times the method is run
                //pushing rectangle -1 in all directions so that we don't draw over the component
                double maximumHeight = -1;
                double minimumHeight = -1;
                double leftSide = -1;
                double rightSide = -1;

                //loop through array of pixels
                for (int i = 0; i < arrayOfPixels.length; i++) {
                    //scanning through image and declaring x and y
                    int x = i % width;
                    int y = i / width;

                    //loop through array of pixels but for every pixel that's not equal to 0 and has an ID that is already equal to one of our unique ID's
                    //once it comes across a pixel of these conditions, it checks if your maximumHeight is set to -1
                    if (arrayOfPixels[i] != 0 && find(arrayOfPixels, i) == id) {
                        if (maximumHeight == -1) {
                            maximumHeight = minimumHeight = y;
                            leftSide = rightSide = x;
                        } else {
                            if (x < leftSide)
                                leftSide = x;
                            if (x > rightSide)
                                rightSide = x;
                            if (y > minimumHeight)
                                minimumHeight = y;
                        }
                    }
                }


                //draw rectangles

                if ((((leftSide - maximumHeight) * (rightSide - minimumHeight)) > 760 && (((leftSide - maximumHeight) * (rightSide - minimumHeight)) < 1000000))) {
                    Rectangle componentLabel = new Rectangle(leftSide, maximumHeight, rightSide - leftSide, minimumHeight - maximumHeight);
                    componentLabel.setTranslateX(imageView.getLayoutX());
                    componentLabel.setTranslateY(imageView.getLayoutY());
                    ((AnchorPane) componentChoose.getParent()).getChildren().add(componentLabel);
                    //rectangle colour and set inside colour to transparent as to show the highlighted component
                    componentLabel.setStroke(Color.WHITE);
                    componentLabel.setFill(Color.TRANSPARENT);
                }

                //call method
                unionUse();

            }
        }


    public void randomColGenerate(MouseEvent mouseEvent) {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(pixelReader,
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        Random random = new Random();

        for (int j : arrayListOfDisjointSets) {

            //will not exceed 255, range between 1 and 255

            var blueRand = random.nextInt(255);
            var redRand = random.nextInt(255);
            var greenRand = random.nextInt(255);
            //variable to store random colour based on varying rgb values
            var randCol = Color.rgb(redRand, greenRand, blueRand);
            var white = Color.WHITE;

            int width = (int) image.getWidth();
            int height = (int) image.getHeight();


            for (int i = 0; i < arrayOfPixels.length; i++) {
                //only colours pixel after
                //scanning through array of pixels, looping through each item
                //root that you found is not equal to j
                if (arrayOfPixels[i] != 0 && find(arrayOfPixels, i) == j) {
                    pixelWriter.setColor(i % width, i / width, randCol);
//                } else if (arrayOfPixels[i] == 0 && find(arrayOfPixels, i) == j) {
//                    pixelWriter.setColor(i % width, i / width, white);
//                }
                }
                //  else if (!arrayListOfDisjointSets.contains(find(arrayOfPixels,i)))
                else if (arrayOfPixels[i] == 0 && find(arrayOfPixels, i) != i) {
                    pixelWriter.setColor(i % width, i / width, white);
                    //}
                }
            }
            componentChoose.setImage(writableImage);

        }
    }

    public void compColour(MouseEvent mouseEvent) {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(pixelReader,
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        Random random = new Random();

        for (int j : arrayListOfDisjointSets) {

            var blueRand = random.nextInt(255);
            var redRand = random.nextInt(255);
            var greenRand = random.nextInt(255);
            var randCol = Color.rgb(redRand, greenRand, blueRand);
            var white = Color.WHITE;

            int width = (int) image.getWidth();
            int height = (int) image.getHeight();


            for (int i = 0; i < arrayOfPixels.length; i++) {
                if (arrayOfPixels[i] == 0 && find(arrayOfPixels, i) == i) {
                    pixelWriter.setColor(i % width, i / width, randCol);
                } else if (arrayOfPixels[i] == 0 && find(arrayOfPixels, i) != i) {
                    pixelWriter.setColor(i % width, i / width, white);
                }

            }

            componentChoose.setImage(writableImage);

        }
    }
}






