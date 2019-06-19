package anvil.addonscript.file;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileDownloader {

    public static File downloadFile(String link, String dir) {

        new File(dir + "cache").mkdirs();

        URL url;

        String[] linkParts = link.split("/");
        String fileName = linkParts[linkParts.length - 1];

        try {
            url = new URL(link);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            FileOutputStream outputStream = new FileOutputStream(dir + "cache/" + fileName);

            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new File(dir + "cache/" + fileName);

    }

    public static File downloadFile(String link) {
        return downloadFile(link, "./");
    }

}
