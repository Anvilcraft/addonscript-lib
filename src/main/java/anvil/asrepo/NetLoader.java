package anvil.asrepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NetLoader {

    public static String readURL(String url) {
        URL url1;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        StringBuilder data = new StringBuilder();
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(url1.openStream()));

            String i;
            while ((i = read.readLine()) != null) {
                data.append(i);
            }
            read.close();

        } catch (IOException e) {
            return "";
        }

        return data.toString();

    }

}
