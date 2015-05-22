package com.prongbang.findlink;

/**
 *
 * @author prongbang
 */
public class VideoQuality { 
    private String videoTitle; 
    private String extension;
    private String downloadUrl;
    private String videoUrl; 
    private String videoSize;
    private String Dimission;
    private String length;

    /**
     * @return the videoTitle
     */
    public String getVideoTitle() {
        return videoTitle.replace("/", "-").replace("\\", "");
    }

    /**
     * @param videoTitle the videoTitle to set
     */
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the downloadUrl
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @param downloadUrl the downloadUrl to set
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * @return the videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl the videoUrl to set
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return the videoSize
     */
    public String getVideoSize() {
        return videoSize;
    }

    /**
     * @param videoSize the videoSize to set
     */
    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    /**
     * @return the Dimission
     */
    public String getDimission() {
        return Dimission;
    }

    /**
     * @param Dimission the Dimission to set
     */
    public void setDimission(String Dimission) {
        this.Dimission = Dimission;
    }

    /**
     * @return the length
     */
    public String getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(String length) {
        this.length = length;
    } 
}
