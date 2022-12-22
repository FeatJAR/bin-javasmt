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
package de.featjar.bin.javasmt;

import de.featjar.base.data.Sets;
import de.featjar.base.env.ABinary;
import de.featjar.base.env.HostEnvironment;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import org.sosy_lab.common.NativeLibraries;

public class JavaSMTBinary extends ABinary {
    public JavaSMTBinary() throws IOException {
        Field nativePathField;
        try {
            nativePathField = NativeLibraries.class.getDeclaredField("nativePath");
            nativePathField.setAccessible(true);
            nativePathField.set(null, ABinary.BINARY_DIRECTORY);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    @Override
    public LinkedHashSet<String> getResourceNames() {
    	return HostEnvironment.isWindows()
    			? Sets.of("mpir.dll", "mathsat.dll", "mathsat5j.dll")
    			: Sets.of("libmathsat5j.so");
    }
}
