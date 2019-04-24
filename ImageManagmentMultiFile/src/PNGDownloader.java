import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import java.io.File;
import java.util.List;

public class PNGDownloader extends Downloader{
    public static boolean download(List<File> fileList, String path) {
        System.out.println("Please wait for all selected files to be downloaded");
        // Change this path to your ImageMagick path
        String myPath="C://Users//Soumya//Documents//NEU//INFO5100//ImageMagick-7.0.8-25-portable-Q16-x64";
        ProcessStarter.setGlobalSearchPath(myPath);
        String folder = path.substring(0, path.lastIndexOf('.'));
        String extention = path.substring(path.lastIndexOf('.'));
        System.out.println("Save folder is " + folder);
        System.out.println("Save extention is " + extention);


        for (File file : fileList) {
            //Create ImageStick command instance for entire program
            ConvertCmd cmd = new ConvertCmd();

            //Create the ImageStick operation instance for entire program
            IMOperation op = new IMOperation();

            String filePath = file.getName();
            String fileName = filePath.substring(0, filePath.lastIndexOf('.'));

            String destinationPath = folder + fileName + extention;

            System.out.println("Source Path is " + file.getPath());
            System.out.println("destination Path is " + destinationPath);

            op.addImage(file.getPath());
            op.addImage(destinationPath);

            try {
                cmd.run(op);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("Images saved in the desired PNG format");
        return true;
    }
}
