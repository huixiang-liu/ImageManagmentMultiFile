import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;

public class ImageMT extends Application {

    private static Stage window;
    private static Scene startScene;
    private static Button uploadButton, applyFiltersButton;
    private static UpLoadInterface upLoadInterface;
    private static FiltersInterface filtersInterface;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize
        window = primaryStage;

        //Layout1 - children are laid out in vertical column
        VBox layout1 = new VBox(15);
        layout1.setAlignment(Pos.CENTER);
        layout1.setPadding(new Insets(15));
        BackgroundFill backgroundFill = new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        layout1.setBackground(background);

        //Label  setting for the output window
        Label label = new Label("Welcome To Image Management Tool");
        label.setFont(Font.font("serif", FontWeight.BOLD, 28));

        //uploadButton setting facilitates image upload
        uploadButton = new Button("Upload Images to view as thumbnails");
        uploadButton.setFont(Font.font("serif", FontWeight.BOLD, 16));

        applyFiltersButton = new Button("Upload an image to get filtered views");
        applyFiltersButton.setFont(Font.font("serif", FontWeight.BOLD, 16));

        //applyFiltersButton.setFont(Font.font("serif", FontWeight.BOLD, 16));
        layout1.getChildren().addAll(label, uploadButton,applyFiltersButton);//, applyFiltersButton);
        startScene = new Scene(layout1, 600, 500);
        window.setScene(startScene);
        window.setTitle("Image Management Tool");
        window.show();

        upLoadInterface = new UpLoadInterface(window, startScene);
        uploadButton.setOnAction(upLoadInterface);

        filtersInterface = new FiltersInterface(window, startScene);
        applyFiltersButton.setOnAction(filtersInterface);

    }
}





