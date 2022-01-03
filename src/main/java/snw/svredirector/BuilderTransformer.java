/*
 *     This file is part of svredirector.
 *
 *     svredirector is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     svredirector is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with svredirector.  If not, see <https://www.gnu.org/licenses/>.
 */

package snw.svredirector;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Objects;

public class BuilderTransformer implements ClassFileTransformer {
    private boolean isGoToFastGit;

    public BuilderTransformer(boolean isGoToFastGit) {
        this.isGoToFastGit = isGoToFastGit;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (Objects.equals(className, "org/spigotmc/builder/Builder")) {
            System.out.println("Transforming org.spigotmc.builder.Builder ...");
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass builderClass = classPool.get("org.spigotmc.builder.Builder");
                CtMethod get = builderClass.getDeclaredMethod("get");
                get.insertBefore("{url = url.replaceFirst(\"https://hub.spigotmc.org/versions/\", \"https://" + (isGoToFastGit ? "raw.fastgit.org" : "cdn.jsdelivr.net/gh") + "/SNWCreations/spigotversions" + (isGoToFastGit ? "/" : "@") + "main/data/\");}");
                builderClass.detach();
                System.out.println("Success! We will launch BuildTools. Enjoy!");
                System.out.println();
                return builderClass.toBytecode();
            } catch (IOException | NotFoundException | CannotCompileException e) {
                System.err.println("Failed to inject. Please contact the author with this stack trace.");
                e.printStackTrace();
                System.exit(1);
            }
        }

        return classfileBuffer;
    }
}
