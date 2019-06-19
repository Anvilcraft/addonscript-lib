package anvil.addonscript.dependencies;

import anvil.addonscript.AddonScriptJSON;
import anvil.addonscript.AddonScriptManager;

import java.util.List;

public class DependencyHelper {

    public static List<Dependency> parseDependencies(AddonScriptJSON addon, int version, List<Dependency> list) {

        AddonScriptJSON.Version versionScritp = null;

        for (AddonScriptJSON.Version v : addon.versions) {
            if (v.versionid == version) {
                versionScritp = v;
                break;
            }
        }

        if (versionScritp == null) {
            throw new RuntimeException("The given Version don't equals a Version of the Script");
        } else if (versionScritp.relations != null) {

            for (AddonScriptJSON.Version.Relation relation : versionScritp.relations) {
                if (relation.type.equals("dependency")) {
                    if (relation.linkType.equals("addonscript")) {

                        AddonScriptJSON dependency = AddonScriptManager.getAddonFromURL(relation.link);

                        boolean included = false;
                        Dependency depen = null;

                        for (Dependency dep : list) {
                            if (dep.addonscript.id.equals(dependency.id)) {
                                included = true;

                                dep.version = Math.max(dep.version, relation.minVersion);

                                depen = dep;

                            }
                        }

                        if (!included) {
                            depen = new Dependency();
                            depen.addonscript = dependency;
                            depen.version = relation.minVersion;
                            list.add(depen);
                        }

                        parseDependencies(depen.addonscript, depen.version, list);


                    }
                }
            }
        }

        return list;

    }


}
