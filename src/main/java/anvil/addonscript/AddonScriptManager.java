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

    public static AddonScriptJSON loadExtScript(AddonScriptJSON addon) {
        if (addon.extScript != null) {
            AddonScriptJSON ext = getAddonFromURL(addon.extScript);
            if (ext.versions != null) {
                if (addon.versions !=null) {
                    addon.versions.addAll(ext.versions);
                } else {
                    addon.versions = ext.versions;
                }
            }

            if (ext.repositories != null) {
                if (addon.repositories != null) {
                    for (AddonScriptJSON.Repository repository : ext.repositories) {

                        boolean included = false;
                        for (AddonScriptJSON.Repository repo : addon.repositories) {
                            if (repo.link.equals(repository.link)) {
                                included = true;
                            }
                        }

                        if (!included) {
                            addon.repositories.add(repository);
                        }
                    }
                } else {
                    addon.repositories = ext.repositories;
                }
            }

        }

        return addon;
    }

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

        return loadExtScript(AddonScriptJSON.fromJSON(json));

    }

    public static List<Dependency> parseDependencies(AddonScriptJSON addon, int version) {
        return DependencyHelper.parseDependencies(addon, version, new ArrayList<>());
    }


}
