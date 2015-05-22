package com.prongbang.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prongbang
 */
public class FileDownload extends Thread {

    private Thread thread;
    private String userAgent;
    private String url;
    private File outputfile;

    public void download(String userAgent, String url, File outputfile) throws Throwable {
        setUserAgent(userAgent);
        setUrl(url);
        setOutputfile(outputfile);
    }

    @Override
    public void run() {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", userAgent);
            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                long length = con.getContentLength();
                InputStream instream2 = con.getInputStream();
                if (outputfile.exists()) {
                    outputfile.delete();
                }
                FileOutputStream outstream = new FileOutputStream(outputfile);
                try {
                    byte[] buffer = new byte[2048];
                    int count = -1;
                    System.out.println("Downloading...");
                    while ((count = instream2.read(buffer)) != -1) {
                        outstream.write(buffer, 0, count); 
                    }
                    outstream.flush(); 
                    System.out.println("Download complete!");
                } finally {
                    outstream.close();
                }
            }
        } catch (MalformedURLException ex) {
            
        } catch (IOException ex) {
            
        }
    }

    @Override
    public void start(){
        thread = new Thread(this);
        thread.start();
    }
    
    /**
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the outputfile
     */
    public File getOutputfile() {
        return outputfile;
    }

    /**
     * @param userAgent the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param outputfile the outputfile to set
     */
    public void setOutputfile(File outputfile) {
        this.outputfile = outputfile;
    }
    
}
