/*
 * Copyright (C) 2022 Sebastian Krieter, Elias Kuiter
 *
 * This file is part of bin-javasmt.
 *
 * bin-javasmt is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * bin-javasmt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with bin-javasmt. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-bin-javasmt> for further information.
 */
package de.featjar.bin.sharpsat;

import de.featjar.base.bin.Binary;
import de.featjar.base.bin.OperatingSystem;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Set;
import org.sosy_lab.common.NativeLibraries;

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
    	return (OperatingSystem.IS_WINDOWS)
    			? Set.of("mpir.dll", "mathsat.dll", "mathsat5j.dll")
    			: Set.of("libmathsat5j.so");
    }
}
