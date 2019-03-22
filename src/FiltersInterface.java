import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
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
            ImageView imageView1;
            ImageView imageView2;
            filtersGridPane.setHgap(25);
            filtersGridPane.setVgap(25);
            filtersGridPane.setPadding(new Insets(0, 10, 0, 10));
            ColorAdjust contrast = new ColorAdjust();
            contrast.setContrast(0.1);
            contrast.setBrightness(0.2);
            contrast.setHue(-0.05);
            contrast.setSaturation(0.2);
            ColorAdjust BlackAndWhite = new ColorAdjust();
            BlackAndWhite.setSaturation(-1);
            if (file != null) {
                input = new FileInputStream(file);
                image = new Image(input);

                //Set grid properties for colored image
                imageView1 = new ImageView(image);
                imageView1.setFitWidth(250);
                imageView1.setFitHeight(250);
                imageView1.setEffect(contrast);
                filtersGridPane.add(imageView1, 1, 1);

                //set grid properties for Greyscale image
                imageView2 = new ImageView(image);
                imageView2.setFitWidth(250);
                imageView2.setFitHeight(250);
                imageView2.setEffect(BlackAndWhite);
                filtersGridPane.add(imageView2, 2, 1);
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
        backButton = new Button("Back");
        backButton.setMaxWidth(200);
        backButton.setOnAction(e -> {
            window.setScene(prevScene);
            return;
        });

        filtersGridPane.setHgap(25);
        filtersGridPane.setVgap(25);

        // Set label
        Label label=new Label("Image filters");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.TOP_CENTER);

        //Layout3 - children are laid out in vertical column
        layout3.getChildren().addAll(label,filtersGridPane,backButton);
        BackgroundFill backgroundFill=new BackgroundFill(Color.PALEVIOLETRED, CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        layout3.setBackground(background);
        // Initialize currentFiltersScene
        currentFiltersScene = new Scene(layout3, 600, 500);
    }
}
