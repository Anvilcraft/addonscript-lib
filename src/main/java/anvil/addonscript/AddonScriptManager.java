package anvil.addonscript;

import anvil.addonscript.dependencies.Dependency;
import anvil.addonscript.dependencies.DependencyHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddonScriptManager {


    public static final Gson gson = new GsonBuilder().create();

    public static AddonScriptJSON getAddonFromURL(String urlString) {
        String json = "";
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AddonScriptJSON.fromJSON(json);

    }

    public static List<Dependency> parseDependencies(AddonScriptJSON addon, int version) {
        return DependencyHelper.parseDependencies(addon, version, new ArrayList<>());
    }


}
