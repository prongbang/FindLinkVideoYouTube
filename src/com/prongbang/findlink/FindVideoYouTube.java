package com.prongbang.findlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author prongbang
 */
public class FindVideoYouTube {

    private final String USER_AGENT = "Mozilla/5.0";
    public String image = "http://i3.ytimg.com/vi/%s/default.jpg";

    public String getVideoIdFromURL(String url) {
        String videoId = "";
        url = url.substring(url.indexOf("?") + 1);
        String delimiters = "&";
        String[] props = url.split(delimiters);
        for (String p : props) {
            if (p.startsWith("v=")) {
                videoId = p.substring(p.indexOf("v=") + 2);
            }
        }
        return videoId;
    }

    public List<VideoQuality> getYouTubeVideoFromUrls(String videoUrl) throws Exception {
        List<VideoQuality> lists = new ArrayList<>();
        String id = getVideoIdFromURL(videoUrl);
        String url = String.format("http://www.youtube.com/get_video_info?&video_id=%s&el=detailpage&ps=default&eurl=&gl=US&hl=en", id);
        String intext = clientDownloadString(url);
        Map<String, String> intent = parseQueryString(intext);
        Map<String, String> data;
        String title = formatTitle(intent.get("title"));
        String videoDuration = intent.get("length_seconds");
        String[] video = intent.get("url_encoded_fmt_stream_map").split(",");
        String server = "";
        String signature = "";
        String urlVideo = "";
        for (String item : video) {
            data = parseQueryString(item);
            server = data.get("fallback_host");
            if (data.get("sig") != null) {
                signature = data.get("sig");
            } else if (data.get("signature") != null) {
                signature = data.get("signature");
            } else {
                signature = null;
            }
            urlVideo = data.get("url") + "&fallback_host=" + server;
            if (signature != null) {
                urlVideo += "&signature=" + signature;
            }
            VideoQuality videoItem = new VideoQuality();
            TagFile tag = new TagFile(data.get("itag"));
            videoItem.setVideoTitle(title);
            videoItem.setDownloadUrl(urlVideo);
            videoItem.setVideoSize(getSizeVideo(urlVideo));
            videoItem.setLength(convertTimeUnit(Long.parseLong(videoDuration)));
            videoItem.setExtension(tag.getVideoExtension());
            videoItem.setDimission(tag.getVideoDimension());
            lists.add(videoItem);
        }
        return lists;
    }

    private String readableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private String formatTitle(String title) {
        return title.replace("&#39;", "'").replace("&quot;", "' ").replace("&lt;", " (").replace("&gt;", " )");
    }

    private String convertTimeUnit(Long second) {
        String time = "";
        Long seconds = second % 60;
        Long minutes = ((second / 60) % 60);
        Long hours = ((second / (60 * 60)) % 24);
        if (hours > 0) {
            time = String.format("%d:%d:%s", hours, minutes, ((seconds < 10) ? "0" + seconds : seconds));
        } else {
            time = String.format("%d:%s", minutes, ((seconds < 10) ? "0" + seconds : seconds));
        }
        return time;
    }

    private String getSizeVideo(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        int bytesLength = con.getContentLength();
        return readableFileSize(Long.parseLong(bytesLength + ""));
    }

    private void queryMap(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private Map<String, String> parseQueryString(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    private String readFile(String url) throws IOException {
        URL text = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(text.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
        return "";
    }

    private String clientDownloadString(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : https://www.youtube.com");
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
