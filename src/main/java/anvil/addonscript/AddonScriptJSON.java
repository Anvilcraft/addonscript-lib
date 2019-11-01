package anvil.addonscript;

import java.util.List;

public class AddonScriptJSON {

    /**
     * The version of the AddonScript file
     */
    public int asversion;
    /**
     * The ID of the addon
     */
    public String id;
    /**
     * The name of the addon
     */
    public String name;
    /**
     * The type of the addon
     * For example mod or modpack
     */
    public String type;
    /**
     * An array of version objects
     */
    public List<Version> versions;
    /**
     * A link to an external AddonScript file which should be loaded instead of this
     */
    public String extScript;
    /**
     * An array of contributor objects
     */
    public List<Contributor> contributors;
    /**
     * An array of repository objects (W.I.P.)
     */
    public List<Repository> repositories;

    /**
     * Version object
     */
    public static class Version {
        /**
         * The name of the version (1.0, 1.1 etc.)
         */
        public String versionname;
        /**
         * The ID of the version
         * Used to determinate, which version is newer
         * (greater number means newer version)
         * (-1 when this file only uses one version)
         */
        public int versionid = -1;
        /**
         * The timestemp when this version was released/created
         */
        public int timestamp;
        /**
         * The changelog of this version
         */
        public String changelog;
        /**
         * A link to an external AddonScript file for this version
         */
        public String versionAddonScript;
        /**
         * An array of file objects
         */
        public List<File> files;
        /**
         * An array of relation objects
         */
        public List<Relation> relations;

        /**
         * File object
         */
        public static class File {

            /**
             * The directory where to move the file
             * Used for File, Maven and Curseforge file type
             */
            public String dir;
            /**
             * The installer for the file
             * W.I.P., DO NOT CHANGE THIS!
             */
            public String installer = "anvil.addonscript.install.DirInstaller";
            /**
             * The version of the file
             * Used for Maven and AddonScript file type
             */
            public String version;
            /**
             * The ID of the addon, when using AddonScript file type
             * The artifact ID when using Maven file type
             */
            public String addonID;
            /**
             * A link to the file
             * used for File and AddonScript file type
             */
            public String file;

            /**
             * The Curseforge project ID
             * used for Curseforge file type
             */
            public String projectID;
            /**
             * The Curseforge file ID
             * used for Curseforge file type
             */
            public String fileID;

            /**
             * The foldername of a folder with override files
             * used for Local file type
             * Only usable when using archas
             */
            public String overrides;

            /**
             * The file type of this file
             * Can be file, addonscript, curseforge, maven or local
             */
            public String type;
            /**
             * If the file can be installed on a client
             */
            public boolean client;
            /**
             * If the file can be installed on a server
             */
            public boolean server;
            /**
             * If the file is alternative and don't have to be installed
             */
            public boolean alternative;
        }

        /**
         * Relation object
         */
        public static class Relation {
            /**
             * The link to relation AddonScript file
             */
            public String link;
            /**
             * The type of the relation
             * Can be included, required, recommended or optional
             */
            public String type = "included";
            /**
             * The type of the link
             * Can be addonscript, file or curseforge
             */
            public String linkType = "file";
            /**
             * The installer for the relation file
             * W.I.P. DO NOT CHANGE THIS!
             */
            public String installer = "anvil.addonscript.install.DirInstaller";
            /**
             * The directory where to install the file
             */
            public String dir;

            /**
             * The minimal required version of this relation
             */
            public int minVersion;
            /**
             * The maximal compatible version of this relation
             */
            public int maxVersion;
            /**
             * If this addon version is compatible with all greater versions of this relation
             * used for AddonScript link type
             */
            public boolean openUp;
            /**
             * If this addon version is compatible with all smaller versions of this relation
             * used for AddonScript link type
             */
            public boolean openDown;
            /**
             * The Curseforge project ID for this relation
             * used for Curseforge link type
             */
            public String projectID;
            /**
             * The Curseforge file ID for this relation
             * used for Curseforge link type
             */
            public String fileID;
        }
    }

    /**
     * Repository object
     */
    public static class Repository {
        /**
         * The type of the repository
         * Can be maven or addonscript
         */
        public String type;
        /**
         * The link to the repository
         */
        public String link;
    }

    /**
     * Contributor object
     */
    public static class Contributor {
        /**
         * The name of the contributor
         */
        public String name;
        /**
         * An array with the roles of the contributor
         */
        public List<String> roles;

    }

    public String toJSON() {
        return AddonScriptManager.gson.toJson(this, this.getClass());
    }

    public static AddonScriptJSON fromJSON(String json) {
        return AddonScriptManager.gson.fromJson(json, AddonScriptJSON.class);
    }

}
