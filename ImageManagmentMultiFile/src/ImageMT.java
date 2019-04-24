import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import static javafx.scene.paint.Color.DARKSLATEBLUE;

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
        BackgroundFill backgroundFill = new BackgroundFill(DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        layout1.setBackground(background);

        //Label  setting for the output window
        DropShadow shadow = new DropShadow(10,DARKSLATEBLUE);
        Label label = new Label("Welcome To Image Management Tool");

        //Include images in this project
        File imageFile = new File(this.getClass().getResource("uploadImage.png").toString().substring(6));
        File imageFile2 = new File(this.getClass().getResource("Effects.png").toString().substring(6));
        File gallery = new File(this.getClass().getResource("gallery.png").toString().substring(6));

        //uploadButton setting facilitates image upload
        Image imageUpload = new Image(imageFile.toURI().toString());
        uploadButton = new Button(" Upload Images",new ImageView(imageUpload));
        uploadButton.setMaxSize(220,20);

        //uploadButton setting facilitates image upload to view filters of the uploaded image
        Image imageFilter = new Image(imageFile2.toURI().toString());
        applyFiltersButton = new Button("  Image Filters",new ImageView(imageFilter));
        applyFiltersButton.setMaxSize(220,20);

        Image galleryImage = new Image(gallery.toURI().toString());

        layout1.getChildren().addAll(new ImageView(galleryImage),label, uploadButton,applyFiltersButton);
        startScene = new Scene(layout1, 570, 500);
        window.setScene(startScene);
        window.setTitle("Image Management Tool");
        window.setResizable(true);
        window.show();

        upLoadInterface = new UpLoadInterface(window, startScene);
        uploadButton.setOnAction(upLoadInterface);

        filtersInterface = new FiltersInterface(window, startScene);
        applyFiltersButton.setOnAction(filtersInterface);

        //SetStyle for Buttons and label
        uploadButton.setStyle("-fx-background-color: white; -fx-text-fill: DARKSLATEBLUE; -fx-font-size: 19px; -fx-font-family: serif; -fx-font-weight: Bold");
        applyFiltersButton.setStyle("-fx-background-color:white ; -fx-text-fill: DARKSLATEBLUE; -fx-font-size: 19px; -fx-font-family: serif; -fx-font-weight: Bold");
        label.setStyle("-fx-text-fill: White; -fx-font-size: 28px; -fx-font-family: serif; -fx-font-weight:Bold");
        uploadButton.setEffect(shadow);
        applyFiltersButton.setEffect(shadow);
        label.setEffect(shadow);

    }
}





