package anvil.addonscript;

import java.util.List;

public class AddonScriptJSON {

    public String id;
    public String name;
    public String type;
    public List<Version> versions;
    public String extScript; //External AddonScript
    public List<Contributor> contributors;
    public List<Repository> repositories;

    public static class Version {
        public String versionname;
        public int versionid = -1; // can be -1 for no version
        public int timestamp;
        public String changelog;
        public String versionAddonScript; //External AddonScript
        public List<File> files;
        public List<Relation> relations;

        public static class File {

            public String dir; //File, Maven, Curseforge
            public String installer = "anvil.addonscript.install.DirInstaller";//File, Maven, Curseforge
            public String version; //Maven, AddonScript
            public String addonID; //AddonScript, Maven (artifact id)
            public String file; //AddonScript, File

            //Curseforge
            public String projectID;
            public String fileID;

            //For all
            public String type; //Can be file, addonscript, curseforge or maven
            public boolean client;
            public boolean server;
            public boolean alternative;
        }

        public static class Relation {
            public String link;
            public String type = "included";
            public String linkType = "file"; //Can be addonscript, file or curseforge
            public String installer = "anvil.addonscript.install.DirInstaller";
            public String dir;
            //Only for addonscript
            public int minVersion;
            public int maxVersion;
            public int[] versions;
            //Only for curseforge
            public String projectID;
            public String fileID;
        }
    }

    public static class Repository {
        public String type; //Can be maven or addonscript (in future)
        public String link;
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
