import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.process.ProcessStarter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class UpLoadInterface implements EventHandler<ActionEvent> {

    private static Stage window;
    private static Scene currentScene, scene2,scene3, prevScene;
    private static GridPane gridPane = new GridPane();
    private static Button uploadButton, downloadButton,backButton;
    private static List<File> fileList;
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;
    private static final FileChooser fileChooser = new FileChooser();

    @Override
    public void handle(ActionEvent actionEvent) {
        // Initialization

        FileInputStream input;
        Image image;
        javaxt.io.Image imageInfo;
        ImageView imageView;

        // Each time we clear the children
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
                    imageInfo = new javaxt.io.Image(file.getPath());
                    HashMap<Integer, Object> exif = imageInfo.getExifTags();
                    if (exif != null && exif.get(0x010F) != null) { // see http://www.javaxt.com/javaxt-core/io/Image
                        System.out.println(exif.get(0x010F).toString());
                        String camera = exif.get(0x0110).toString();
                        Label l1 = new Label(camera);
                        gridPane.add(l1, 1, 1);
                    }
                    imageView = new ImageView(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    IMOperation op = new IMOperation();
                    gridPane.add(imageView, indexX, indexY);

                    System.out.println(file.getPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        window.setScene(currentScene);
        window.show();

    }


    UpLoadInterface(Stage window, Scene prevScene) {
        // Initialize
        this.window = window;
        this.prevScene = prevScene;

        // Set download button
        downloadButton = new Button("Download Images");
        downloadButton.setOnAction(new DownloadInterface(fileList, window));
        downloadButton.setMaxWidth(200);

        // set back button
        backButton = new Button("Back");
        backButton.setMaxWidth(200);
        backButton.setOnAction(e -> {
            window.setScene(prevScene);
            return;
        });

        // Set grinPane
        gridPane.setHgap(25);
        gridPane.setVgap(25);

        // Set label
        Label label=new Label("Thumbnail Image Viewer");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.TOP_CENTER);

        //Layout2 - children are laid out in vertical column
        layout2.getChildren().addAll(label,gridPane,downloadButton,backButton);
        BackgroundFill backgroundFill=new BackgroundFill(Color.PALEVIOLETRED, CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        layout2.setBackground(background);

        // Initialize currentScene
        currentScene = new Scene(layout2, 600, 500);
    }

}
