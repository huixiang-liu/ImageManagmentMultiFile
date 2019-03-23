import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.process.ProcessStarter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadInterface implements EventHandler<ActionEvent> {
    private List<File> fileList;
    private Window window;
    private static Scene currentScene,prevScene;
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;
    private static Downloader downloader;
    private CheckBox isZip;

    @Override
    public void handle(ActionEvent event) {
        //String Zipfile = "C:/Users/Soumya/Pictures/Images.zip";
        if (isZip.isSelected()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("Images.zip");
            fileChooser.getExtensionFilters().add
                    (new FileChooser.ExtensionFilter("Zip","*.zip"));
            File zipFile = fileChooser.showSaveDialog(window);


            if (zipFile != null) {
                ZipDownloader.download(fileList, zipFile);
            }

        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("Images");
            fileChooser.getExtensionFilters().addAll
                    (new FileChooser.ExtensionFilter("JPG","*.jpg"),
                            new FileChooser.ExtensionFilter("PNG","*.png"));
            File zipFile = fileChooser.showSaveDialog(window);

            if (zipFile != null) {
                JPGDownloader.download(fileList, zipFile.getPath());
            }


        }
    }


    private void Download(File file) throws IOException {
        //Set library environment
        String myPath="C://Users//Soumya//Documents//NEU//INFO5100//ImageMagick-7.0.8-25-portable-Q16-x64";
        ProcessStarter.setGlobalSearchPath(myPath);
        downloader = downloader.getDownloader("jpg");
        //select the destination
        FileChooser fileChooser = new FileChooser();
        //only convert jpg and png file
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setInitialFileName(String.valueOf(file));
        File fileToBeSaved = fileChooser.showSaveDialog(window);
        String destination = fileToBeSaved.toString();
        String extention = destination.substring(destination.lastIndexOf("."));

        //test the output path
        System.out.println("The output file is " + destination);
        System.out.println("The extension is " + extention);
        String sourcePath = file.toString();
        // print the path of the image
        System.out.println("The destination path is " + destination);

        //Create ImageStick command instance for entire program
        ConvertCmd cmd = new ConvertCmd();

        //Create the ImageStick operation instance for entire program
        IMOperation op = new IMOperation();

        // Get the width and Height of the image
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        Info imageInfo;
        try {
            imageInfo= new Info(sourcePath, false); // set the second parameter to "false" to get complete info of the image
            width = imageInfo.getImageWidth();
            height = imageInfo.getImageHeight();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        op.addImage(sourcePath);
        op.resize(width, height);
        op.addImage(destination);

        try {
            cmd.run(op);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public DownloadInterface(List<File> fileList, Window window, CheckBox isZip) {
        downloader = new Downloader();
        this.fileList = fileList;
        this.window = window;
        this.isZip = isZip;
    }
}
