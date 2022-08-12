package de.featjar.bin.sharpsat;

import de.featjar.util.bin.Binary;
import de.featjar.util.bin.OperatingSystem;
import org.sosy_lab.common.NativeLibraries;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Set;

public class JavaSMTBinary extends Binary {
    static {
        new JavaSMTBinary();
    }

    public JavaSMTBinary() {
        Field nativePathField = null;
        try {
            nativePathField = NativeLibraries.class.getDeclaredField("nativePath");
            nativePathField.setAccessible(true);
            nativePathField.set(null, Binary.BINARY_DIRECTORY);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    @Override
    public Path getPath() {
        return null;
    }

    @Override
    public Set<String> getResourceNames() {
        if (OperatingSystem.IS_WINDOWS)
            return Set.of("mpir.dll", "mathsat.dll", "mathsat5j.dll");
        else
            return Set.of("libmathsat5j.so");
    }
}
