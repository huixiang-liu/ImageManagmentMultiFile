import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDownloader extends Downloader{
    public static boolean download(List<File> fileList, File zipFile) {
        if (zipFile != null) {

            System.out.println("Save path is " + zipFile.getPath());

            try {
                byte[] buffer = new byte[1024];
                FileOutputStream outputStream = new FileOutputStream(zipFile);
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

            return true;
        } else {
            return false;
        }

    }
}
