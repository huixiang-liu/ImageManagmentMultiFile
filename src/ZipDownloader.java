import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDownloader extends Downloader{
    private List<File> fileList;
    private String savePath;
    public boolean download(List<File> fileList, String savePath) {
        if (fileList.size() > 1) {
            //String Zipfile = "C:/Users/Soumya/Pictures/Images.zip";

            // Using factory pattern to made different downloader, zip, jpg or anything else
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("Images.zip");
            fileChooser.getExtensionFilters().add
                    (new FileChooser.ExtensionFilter("Zip", "*.zip"));
            File Zipfile = new File(savePath);
            try {
                byte[] buffer = new byte[1024];
                FileOutputStream outputStream = new FileOutputStream(Zipfile);
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    int len;
                    while ((len = fileInputStream2.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);
                    }
                    zipOutputStream.closeEntry();
                    fileInputStream2.close();
                }
                zipOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error creating zip file" + e);
            }
        }
//
//        else {
//            int i=0;
//            File file = fileList.get(i);
//            try {
//                Download(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return true;
    }


    ZipDownloader(List<File> fileList, String savePath) {
        this.fileList = fileList;
        this.savePath = savePath;
    }
}
