import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.im4java.core.IMOperation;
import java.io.*;
import java.util.List;
import static javafx.scene.paint.Color.*;

public final class UpLoadInterface implements EventHandler<ActionEvent> {

    private static Stage window;
    private static Scene currentScene,prevScene;
    private static GridPane gridPane = new GridPane();
    private static Button downloadButton,backButton;
    private static List<File> fileList;
    private static final FileChooser fileChooser = new FileChooser();
    private static CheckBox zipBox;

    @Override
    public void handle(ActionEvent actionEvent) {

        // Initialization
        FileInputStream input;
        Image image;
        ImageView imageView;

        // Each time we clear the children to clear the contents of the window
        gridPane.getChildren().clear();

        //filelist is used to upload more than one image
        fileList = fileChooser.showOpenMultipleDialog(window);
        if (fileList == null) {
            // return to start page if the user click "Cancel" Button
            return;
        }
        if (fileList != null) {
            int indexX = 0;
            int indexY = 0;
            //to iterate through the number of images to be uploaded
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                indexX = i % 5;
                indexY = i / 5;
                try {
                    input = new FileInputStream(file);
                    image = new Image(input);
                    imageView = new ImageView(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    IMOperation op = new IMOperation();
                    gridPane.add(imageView, indexX, indexY);

                    //using javaxt.io.image to get the image metadata
                    javaxt.io.Image imageInfo = new javaxt.io.Image(file.getPath());
                    java.util.HashMap<Integer, Object> exif = imageInfo.getExifTags();
                    double[] coord = imageInfo.getGPSCoordinate();

                    //if the gps coordinate information of the image is not null
                    if (coord!=null) {
                        //tooltip to display metadata on the imageview
                        Tooltip t1 = new Tooltip("Camera: " + exif.get(0x0110) +"\n"
                                + "Width x Height: " +imageInfo.getWidth() + " x " + imageInfo.getHeight() +"\n"
                                + "GPS Coordinate: " + coord[0] + ", " + coord[1]+"\n"
                                + "Date: " + exif.get(0x0132) + "\n"
                                + "Manufacturer: " + exif.get(0x010F));
                        Tooltip.install(imageView, t1);
                    }
                    //if the gps coordinate information of the image is unavailable
                    else
                    {
                        Tooltip t = new Tooltip("Camera: " + exif.get(0x0110) +"\n"
                                +"Width x Height: " +imageInfo.getWidth() + " x " + imageInfo.getHeight() +"\n"
                                +"Date: " + exif.get(0x0132) + "\n"
                                +"Manufacturer: " + exif.get(0x010F));
                        Tooltip.install(imageView, t);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        window.setScene(currentScene);
        window.show();
        downloadButton.setOnAction(new DownloadInterface(fileList, window, zipBox));
    }

    UpLoadInterface(Stage window, Scene prevScene) {
        // Initialize
        this.window = window;
        this.prevScene = prevScene;
        DropShadow shadow = new DropShadow(10,MEDIUMPURPLE);

        //Get Button Icons from saved path
        File imageFile = new File("C:\\Users\\Soumya\\Downloads\\ImageManagmentMultiFile\\ImageManagmentMultiFile\\Images\\download.png");
        File imageFile2 = new File("C:\\Users\\Soumya\\Downloads\\ImageManagmentMultiFile\\ImageManagmentMultiFile\\Images\\back.png");

        // Set download button
        Image imageDownload = new Image(imageFile.toURI().toString());
        downloadButton = new Button(" Download",new ImageView(imageDownload));

        // set back button
        Image imageOfBackButton = new Image(imageFile2.toURI().toString());
        backButton = new Button("Go Back",new ImageView(imageOfBackButton));
        backButton.setMaxSize(180,15);
        backButton.setStyle("-fx-background-color: white; -fx-text-fill: DARKSLATEBLUE; -fx-font-size: 18px; -fx-font-family: serif; -fx-font-weight: Bold");
        backButton.setOnAction(e -> {
            window.setScene(prevScene);
            return;
        });

        // Set gridPane
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        //Set HBox
        HBox hbox=new HBox(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(backButton,downloadButton);

        zipBox = new CheckBox("Save as zip");

        // Set label
        Label label=new Label("Thumbnail Image Viewer");
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.TOP_CENTER);

        //Layout2 - children are laid out in vertical column
        layout2.getChildren().addAll(label,gridPane,hbox, zipBox);

        BackgroundFill backgroundFill = new BackgroundFill(	STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        layout2.setBackground(background);

        // Initialize currentScene
        currentScene = new Scene(layout2, 570, 500);

        //Set Styles for Buttons, labels and Zipbox
        downloadButton.setMaxSize(180,15);
        downloadButton.setEffect(shadow);
        downloadButton.setStyle("-fx-background-color: white; -fx-text-fill: DARKSLATEBLUE; -fx-font-size: 18px; -fx-font-family: serif; -fx-font-weight: Bold");
        label.setStyle("-fx-text-fill: White; -fx-font-size: 26px; -fx-font-family: serif; -fx-font-weight:Bold");
        zipBox.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: serif;-fx-font-weight:Bold");

    }

}
