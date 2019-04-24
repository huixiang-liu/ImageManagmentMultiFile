import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.util.List;

public class DownloadInterface implements EventHandler<ActionEvent> {
    private List<File> fileList;
    private Window window;
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;
    private static Downloader downloader;
    private CheckBox isZip;

    public DownloadInterface(List<File> fileList, Window window, CheckBox isZip) {
        downloader = new Downloader();
        this.fileList = fileList;
        this.window = window;
        this.isZip = isZip;
    }

    @Override
    public void handle(ActionEvent event) {
        String path;

        //if user checks the "Save as Zip" checkbox
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
            //FileChooser properties for Save Dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("Images");
            fileChooser.getExtensionFilters().addAll
                            (new FileChooser.ExtensionFilter("JPG","*.jpg"),
                            new FileChooser.ExtensionFilter("PNG","*.png"),
                            new FileChooser.ExtensionFilter("BMP","*.bmp"),
                            new FileChooser.ExtensionFilter("GIF", "*.gif"),
                            new FileChooser.ExtensionFilter("WebP", "*.WebP"));
            File zipFile = fileChooser.showSaveDialog(window);

            //Download method is called when a specific image format is chosen
            if (zipFile != null) {
                path=zipFile.getAbsolutePath();
                if(path.endsWith(".jpg"))
                    JPGDownloader.download(fileList, zipFile.getPath());
                else if(path.endsWith(".png"))
                    PNGDownloader.download(fileList, zipFile.getPath());
                else if(path.endsWith(".bmp"))
                    BMPDownloader.download(fileList, zipFile.getPath());
                else if(path.endsWith(".gif"))
                    GIFDownloader.download(fileList, zipFile.getPath());
                else if(path.endsWith(".WebP"))
                    WebPDownloader.download(fileList, zipFile.getPath());
            }
        }
    }

}
