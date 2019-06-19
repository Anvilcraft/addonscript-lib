package anvil.addonscript.install;

import java.io.File;

public class DirInstaller implements IInstaller {

    @Override
    public void installFile(File file, String params) {
        if (file.renameTo(new File("./" + params +"/" + file.getName()))) {
            file.delete();
        }
    }
}
