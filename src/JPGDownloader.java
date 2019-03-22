import java.io.File;
import java.util.List;

public class JPGDownloader extends Downloader{
    private List<File> fileList;
    private String savePath;
    public boolean download(List<File> fileList, String savePath) {
        // TO-DO
        return true;
    }

    JPGDownloader(List<File> fileList, String savePath) {
        this.fileList = fileList;
        this.savePath = savePath;
    }
}
