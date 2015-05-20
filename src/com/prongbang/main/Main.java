package com.prongbang.main;

import com.prongbang.findlink.FindVideoYouTube;
import com.prongbang.findlink.VideoQuality;
import java.util.List;

/**
 *
 * @author prongbang
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        FindVideoYouTube ytd = new FindVideoYouTube();
        String url = "https://www.youtube.com/watch?v=tOZZJMsAUYk";
        List<VideoQuality> videoList = ytd.getYouTubeVideoFromUrls(url);
        for (VideoQuality video : videoList) {
            System.out.println(video.getVideoTitle() + " : " + video.getExtension() + " : " + video.getDimission() + " : " + video.getVideoSize() + " : " + video.getLength() + " : " + video.getDownloadUrl());
        }
    }
}
