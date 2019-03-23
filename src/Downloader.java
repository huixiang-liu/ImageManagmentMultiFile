import java.io.File;
import java.util.List;

public class Downloader {
    static JPGDownloader jpgDownloader;
    static ZipDownloader zipDownloader;

    public Downloader getDownloader(String type) {
        if (type.equalsIgnoreCase("JPG")) {
            return jpgDownloader;
        } else if (type.equalsIgnoreCase("ZIP")) {
            return zipDownloader;
        }
        return null;
    }
}
