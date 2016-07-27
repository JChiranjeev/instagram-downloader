import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
/**
 * Created by Dell on 27-Jul-16.
 */
public class InstagramDownloader {
    public InstagramDownloader(String url,String targetPath) {
        downloadImage(url,targetPath);
    }
    public static void main(String[] str) {
        Scanner ip = new Scanner(System.in);
        System.out.print("Enter URL: ");
        String url = ip.nextLine();
        System.out.print("Enter Targer Location: ");
        String target = ip.nextLine();
        new InstagramDownloader(url,target);
    }
    public void downloadImage(String link,String target) {
        try {
            URL url = new URL(link);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputline;
            String reqUrl;
            Path path = new File(target).toPath();
            while ((inputline = bufferedReader.readLine()) != null) {
                if(inputline.contains("<meta property=\"og:image\" ")) {
                    reqUrl = inputline.substring(inputline.indexOf("content=\"")+9,inputline.indexOf("\" />"));
                    System.out.println(reqUrl);
                    URL imageUrl = new URL(reqUrl);
                    InputStream inputStream = imageUrl.openStream();
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
