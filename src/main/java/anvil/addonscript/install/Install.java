package anvil.addonscript.install;

import anvil.addonscript.AddonScriptJSON;
import anvil.addonscript.file.CurseForgeDownloader;
import anvil.addonscript.file.FileDownloader;

import java.io.File;

public class Install {

    public static void installRelation(AddonScriptJSON.Version.Relation relation, String workingDir) {

        switch (relation.linkType) {
            case "file":
                File file = FileDownloader.downloadFile(relation.link, workingDir);
                InstallHandler.install(file, relation.dir, workingDir, relation.installer);
            break;
            case "addonscript":
                //TODO Implement
            break;
            case "curseforge":
                File cfile = CurseForgeDownloader.downloadFile(relation.projectID, relation.fileID, workingDir);
                InstallHandler.install(cfile, relation.dir, workingDir, relation.installer);
            break;
        }

    }

    public static void installFile(AddonScriptJSON.Version.File file, String workingDir) {
        switch (file.type) {
            case "file":
                File dlFile = FileDownloader.downloadFile(file.file, workingDir);
                InstallHandler.install(dlFile, file.dir, workingDir, file.installer);
                break;
            case "curseforge":
                File cfile = CurseForgeDownloader.downloadFile(file.projectID, file.fileID, workingDir);
                InstallHandler.install(cfile, file.dir, workingDir, file.installer);
                break;
            case "local":
                //TODO Implement
                break;
            case "addonscript":
                //TODO Implement
                break;
            case "maven":
                //TODO Implement
                break;
        }
    }

}
