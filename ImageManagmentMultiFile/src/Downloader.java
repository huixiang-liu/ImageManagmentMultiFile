public class Downloader {
    static JPGDownloader jpgDownloader;
    static PNGDownloader pngDownloader;
    static ZipDownloader zipDownloader;
    static BMPDownloader bmpDownloader;
    static GIFDownloader gifDownloader;
    static WebPDownloader WebPDownloader;

    public Downloader getDownloader(String type) {
        if (type.equalsIgnoreCase("JPG")) {
            return jpgDownloader;
        } else if (type.equalsIgnoreCase("ZIP")) {
            return zipDownloader;
        }
        else if (type.equalsIgnoreCase("PNG")) {
            return pngDownloader;
        }
        else if (type.equalsIgnoreCase("BMP")) {
            return bmpDownloader;
        }
        else if (type.equalsIgnoreCase("GIF")) {
            return gifDownloader;
        }
        else if (type.equalsIgnoreCase("WebP")) {
            return WebPDownloader;
        }
        return null;
    }
}
