package anvil.addonscript.install;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DirInstaller implements IInstaller {

    @Override
    public void installFile(File file, String params, String workingDir) {
        File dir = new File(workingDir + params +"/" );
        dir.mkdirs();
        File f = new File(workingDir + params +"/"  + file.getName());

        try {
            Files.copy(file.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
