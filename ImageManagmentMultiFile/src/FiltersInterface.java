import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.scene.paint.Color.MEDIUMPURPLE;

public class FiltersInterface implements EventHandler<ActionEvent>  {
    private static Stage window;
    private static Scene currentFiltersScene,prevScene;
    private static Button backButton;
    private static GridPane filtersGridPane = new GridPane();

    @Override
    public void handle(ActionEvent actionEvent) {

        // Each time we clear the children
        filtersGridPane.getChildren().clear();
        try {
            final FileChooser fileChooser = new FileChooser();
            FileInputStream input;
            File file = fileChooser.showOpenDialog(window);
            Image image;
            ImageView imageView1,imageView2,imageView3,imageView4;
            filtersGridPane.setHgap(25);
            filtersGridPane.setVgap(25);
            filtersGridPane.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));

            ColorAdjust contrast = new ColorAdjust();
            contrast.setContrast(0.1);
            contrast.setBrightness(0.2);
            contrast.setSaturation(0.2);

            ColorAdjust contrast2 = new ColorAdjust();
            contrast2.setContrast(0.3);
            contrast2.setBrightness(0.5);
            contrast2.setHue(-0.01);
            contrast2.setSaturation(0.6);

            ColorAdjust BlackAndWhite = new ColorAdjust();

            BlackAndWhite.setSaturation(-1);
            if (file != null) {
                input = new FileInputStream(file);
                image = new Image(input);

                //Set grid properties for colored image
                imageView1 = new ImageView(image);
                imageView1.setFitWidth(150);
                imageView1.setFitHeight(150);
                imageView1.setEffect(contrast);
                filtersGridPane.add(imageView1, 1, 1);

                //Set grid properties for high contrast
                imageView2 = new ImageView(image);
                imageView2.setFitWidth(150);
                imageView2.setFitHeight(150);
                imageView2.setEffect(contrast2);
                filtersGridPane.add(imageView2, 2, 1);

                //set grid properties for Greyscale image
                imageView3 = new ImageView(image);
                imageView3.setFitWidth(150);
                imageView3.setFitHeight(150);
                imageView3.setEffect(BlackAndWhite);
                filtersGridPane.add(imageView3, 3, 1);

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        window.setScene(currentFiltersScene);
        window.show();
    }
    FiltersInterface(Stage window, Scene prevScene) {
        this.window = window;
        this.prevScene = prevScene;
        File imageFile = new File("C:\\Users\\Soumya\\Downloads\\ImageManagmentMultiFile\\ImageManagmentMultiFile\\Images\\back.png");
        File imageFile2 = new File("C:\\Users\\Soumya\\Downloads\\ImageManagmentMultiFile\\ImageManagmentMultiFile\\Images\\ImageEffects.png");
        Image imageOfBackButton = new Image(imageFile.toURI().toString());
        Image imageEffects = new Image(imageFile2.toURI().toString());
        backButton = new Button("Go Back",new ImageView(imageOfBackButton));
        backButton.setOnAction(e -> {
            window.setScene(prevScene);
            return;
        });

        filtersGridPane.setHgap(25);
        filtersGridPane.setVgap(25);

        // Set label
        Label label=new Label("Image filters",new ImageView(imageEffects));
        VBox layout3 = new VBox(20);

        //Layout3 - children are laid out in vertical column
        layout3.getChildren().addAll(label,filtersGridPane,backButton);
        BackgroundFill backgroundFill=new BackgroundFill(Color.	DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        layout3.setBackground(background);
        // Initialize currentFiltersScene
        currentFiltersScene = new Scene(layout3, 570, 500);

        //Set Button and label Styles
        backButton.setMaxSize(160,15);
        backButton.setStyle("-fx-background-color: white; -fx-text-fill: DARKSLATEBLUE; -fx-font-size: 18px; -fx-font-family: serif; -fx-font-weight: Bold");
        DropShadow shadow = new DropShadow(10,MEDIUMPURPLE);
        label.setEffect(shadow);
        label.setStyle("-fx-text-fill: White; -fx-font-size: 28px; -fx-font-family: serif; -fx-font-weight:Bold");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
        layout3.setAlignment(Pos.TOP_CENTER);
    }

}
