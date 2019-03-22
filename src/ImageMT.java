import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageMT extends Application {

        private static Stage window;
        private static Scene startScene, scene2,scene3;
        private static Button uploadButton, downloadButton,applyFiltersButton,backButton;

        private static UpLoadInterface upLoadInterface;


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            // Initialize
            window = primaryStage;

            //uploadButton setting that facilitates image upload
            uploadButton = new Button("Upload Images to view as thumbnails");

            //Layout1 - children are laid out in vertical column
            VBox layout1 = new VBox(15);
            layout1.setAlignment(Pos.CENTER);
            layout1.setPadding(new Insets(15));
            BackgroundFill backgroundFill=new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY,Insets.EMPTY);
            Background background=new Background(backgroundFill);
            layout1.setBackground(background);

            //Label  setting for the output window
            Label label = new Label("Welcome To Image Management Tool");
            label.setFont(Font.font("serif", FontWeight.BOLD, 28));
            uploadButton.setFont(Font.font("serif", FontWeight.BOLD, 16));

            uploadButton.setOnAction(upLoadInterface);



            // Filter Button
            applyFiltersButton = new Button("Upload an image to get filtered views");
            applyFiltersButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent) {
                    final FileChooser fileChooser = new FileChooser();
                    try {
                        SetContrast();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            //uploadButton.setStyle("-fx-font: normal bold 16px 'serif' ");
            applyFiltersButton.setFont(Font.font("serif", FontWeight.BOLD, 16));
            layout1.getChildren().addAll(label, uploadButton,applyFiltersButton);
            startScene = new Scene(layout1, 600, 500);
            upLoadInterface = new UpLoadInterface(window, startScene);

            //window for the first scene which included the label and upload button
            window.setScene(startScene);
            window.setTitle("Image Management Tool");
            window.show();
        }


        private void SetContrast() throws FileNotFoundException {
            final FileChooser fileChooser = new FileChooser();
            FileInputStream input2;
            File file2=fileChooser.showOpenDialog(window);
            Image image2;
            ImageView imageView2;
            ImageView imageView3;
            GridPane gridPane = new GridPane();
            gridPane.setHgap(25);
            gridPane.setVgap(25);
            gridPane.setPadding(new Insets(0,10,0,10));
            ColorAdjust contrast=new ColorAdjust();
            contrast.setContrast(0.1);
            contrast.setBrightness(0.2);
            contrast.setHue(-0.05);
            contrast.setSaturation(0.2);
            ColorAdjust BlackAndWhite = new ColorAdjust();
            BlackAndWhite.setSaturation(-1);
            if (file2 != null) {
                input2 = new FileInputStream(file2);
                image2 = new Image(input2);

                //Set grid properties for colored image
                imageView2 = new ImageView(image2);
                imageView2.setFitWidth(250);
                imageView2.setFitHeight(250);
                imageView2.setEffect(contrast);
                gridPane.add(imageView2,1, 1);

                //set grid properties for Greyscale image
                imageView3 = new ImageView(image2);
                imageView3.setFitWidth(250);
                imageView3.setFitHeight(250);
                imageView3.setEffect(BlackAndWhite);
                gridPane.add(imageView3,2, 1);
            }
            else
            {
                System.out.println("No image file was chosen");

            }

            VBox vbox=new VBox(30);
            BackgroundFill backgroundFill=new BackgroundFill(Color.PALEVIOLETRED, CornerRadii.EMPTY,Insets.EMPTY);
            Background background=new Background(backgroundFill);
            vbox.setBackground(background);
            vbox.setAlignment(Pos.CENTER);
            Label label2=new Label("Image Viewer With Filters");
            label2.setFont(Font.font("Cambria", FontWeight.BOLD, 22));
            backButton.setMaxWidth(200);
            backButton = new Button("Back");
            backButton.setOnAction(e -> window.setScene(startScene));
            window.show();
            vbox.getChildren().addAll(label2,gridPane,backButton);
            scene3 = new Scene(vbox, 600, 500);
            window.setScene(scene3);
            window.setTitle("Filtered Images");
            window.show();
        }
    }



