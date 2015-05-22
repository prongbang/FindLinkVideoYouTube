package com.prongbang.main;

import com.prongbang.download.FileDownload;
import com.prongbang.findlink.FindVideoYouTube;
import com.prongbang.findlink.VideoQuality;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author prongbang
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception, Throwable {
        String USER_AGENT = "Mozilla/5.0";

        FindVideoYouTube ytd = new FindVideoYouTube();
        FileDownload fd = new FileDownload();
        String url = "https://www.youtube.com/watch?v={id}";
        List<VideoQuality> videoList = ytd.getYouTubeVideoFromUrls(url);

        int i = 0;
        for (VideoQuality video : videoList) {
            if (i == 0) {
                System.out.println(video.getVideoTitle());
            }
            String quality = "[" + i + "] " + video.getExtension().toUpperCase() + "(" + video.getDimission() + ") " + video.getVideoSize();
            System.out.println(quality);
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose Video : ");
        int ch = scanner.nextInt();
        if (ch < videoList.size()) {
            String finame = videoList.get(ch).getVideoTitle();
            String extension = videoList.get(ch).getExtension();
            String dir = "D:/Video/";
            String path = dir + finame + "." + extension;
            File theDir = new File(dir);
            if (!theDir.exists()) {
                System.out.println("Creating directory: " + dir);
                boolean result = false;
                try {
                    theDir.mkdir();
                    result = true;
                } catch (SecurityException se) {
                }
                if (result) {
                    System.out.println(dir + "created.");
                }
            }
            fd.download(USER_AGENT, videoList.get(ch).getDownloadUrl(), new File(path));
            fd.start();
        }
    }
}
