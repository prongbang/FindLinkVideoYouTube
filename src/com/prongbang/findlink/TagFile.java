package com.prongbang.findlink;

/**
 *
 * @author prongbang
 */
public class TagFile {
    
    /*
    * reference : http://www.codeproject.com/Tips/323771/YouTube-Downloader-Using-Csharp-NET
    */
    final String extentionsTag = "5=flv,6=flv,17=3gp,18=mp4,22=mp4,34=flv,35=flv,36=3gp,37=mp4,38=mp4,43=webm,44=webm,45=webm,46=webm,82=3D.mp4,83=3D.mp4,84=3D.mp4,85=3D.mp4,100=3D.webm,101=3D.webm,102=3D.webm,120=live.flv";
    final String wideDimensionsTag = "5=320x180,6=480x270,17=176x99,18=640x360,22=1280x720,34=640x360,35=854x480,36=320x180,37=1920x1080,38=2048x1152,43=640x360,44=854x480,45=1280x720,46=1920x1080,82=480x270,83=640x360,84=1280x720,85=1920x1080,100=640x360,101=640x360,102=1280x720,120=1280x720";
    final String dimensionsTag = "5=320x240,6=480x360,17=176x144,18=640x480,22=1280x960,34=640x480,35=854x640,36=320x240,37=1920x1440,38=2048x1536,43=640x480,44=854x640,45=1280x960,46=1920x1440,82=480x360,83=640x480,84=1280x960,85=1920x1440,100=640x480,101=640x480,102=1280x960,120=1280x960";
    
    private String videoExtension;
    private String videoDimension;
    
    public TagFile(String tag){
        tag = tag.trim() + "=";
        String start = extentionsTag.substring(extentionsTag.indexOf(tag));
        String ext = start.substring(tag.length(),start.indexOf(","));
        this.setVideoExtension(ext);
        start = wideDimensionsTag.substring(wideDimensionsTag.indexOf(tag));
        String size = start.substring(tag.length(),start.indexOf(","));
        this.setVideoDimension(size);
    }
    
    /**
     * @return the videoExtension
     */
    public String getVideoExtension() {
        return videoExtension;
    }

    /**
     * @return the videoDimension
     */
    public String getVideoDimension() {
        return videoDimension;
    }

    /**
     * @param videoExtension the videoExtension to set
     */
    public void setVideoExtension(String videoExtension) {
        this.videoExtension = videoExtension;
    }

    /**
     * @param videoDimension the videoDimension to set
     */
    public void setVideoDimension(String videoDimension) {
        this.videoDimension = videoDimension;
    }

}
