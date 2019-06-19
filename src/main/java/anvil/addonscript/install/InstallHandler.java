package anvil.addonscript.install;

import java.io.File;

public class InstallHandler {

    public static void install(File file, String params, String dir, String installClass) {

        try {
            Class clazz = Class.forName(installClass);
            Object object = clazz.newInstance();

            if (object instanceof IInstaller) {

                IInstaller installer = (IInstaller) object;
                installer.installFile(file, params, dir);

            } else {
                throw new RuntimeException("Installer class is not implementing IInstaller");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void install(File file, String params, String dir) {
        install(file, params, dir,"anvil.addonscript.install.DirInstaller");
    }

}
