package anvil.addonscript;

import java.util.List;

public class AddonScriptJSON {

    public String id;
    public String name;
    public String type;
    public List<Version> versions;
    public List<Contributor> contributors;

    public static class Version {
        public String versionname;
        public int versionid;
        public int timestamp;
        public String changelog;
        public String versionAddonScript;
        public List<File> files;
        public List<Relation> relations;

        public static class File {

            public String dir; //File, Maven
            public String version; //Maven, AddonScript
            public String addonID; //AddonScript, Maven (artifact id)
            public String repository; //AddonScript, Maven

            public String file; //AddonScript, File

            //For all
            public String type; //Can be file, addonscript or maven
            public boolean client;
            public boolean server;
            public boolean alternative;
        }

        public static class Relation {
            public String link;
            public String linkType; //Can be addonscript or file
            public String dir; // Only for file type
            public String type;
            public int minVersion;
            public int maxVersion;
            public int[] versions;
        }
    }

    public static class Contributor {
        public String name;
        public List<String> roles;

    }

    public String toJSON() {
        return AddonScriptManager.gson.toJson(this, this.getClass());
    }

    public static AddonScriptJSON fromJSON(String json) {
        return AddonScriptManager.gson.fromJson(json, AddonScriptJSON.class);
    }

}
