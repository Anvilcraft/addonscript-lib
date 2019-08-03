package anvil.addonscript.file;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CurseForgeDownloader {


    public static File downloadFile(String projectID, String fileID, String dir) {
        return FileDownloader.downloadFile(getFileURL(projectID, fileID), dir);
    }

    public static String getFileURL(String projectID, String fileID) {
        String projectURL = "http://minecraft.curseforge.com/projects/" + projectID;

        try {
            projectURL = getLocationHeader(projectURL);
            projectURL = projectURL.replaceAll("\\?cookieTest=1", "");
            String fileURL = projectURL + "/download/" + fileID + "/file";
            fileURL = getLocationHeader(fileURL);
            return fileURL;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getLocationHeader(String location) throws IOException, URISyntaxException {
        URI uri = new URI(location);
        HttpURLConnection connection = null;
        String userAgent="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/53.0.2785.143 Chrome/53.0.2785.143 Safari/537.36";
        for(;;) {
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setInstanceFollowRedirects(false);
            String redirectLocation = connection.getHeaderField("Location");
            if(redirectLocation == null)
                break;

            if(redirectLocation.startsWith("/"))
                uri = new URI(uri.getScheme(), uri.getHost(), redirectLocation, uri.getFragment());
            else {
                try {
                    uri = new URI(redirectLocation);
                } catch (URISyntaxException e) {
                    url = new URL(redirectLocation);
                    uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                }
            }
        }

        return uri.toString();
    }

}
