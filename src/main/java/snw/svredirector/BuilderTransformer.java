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

import static snw.svredirector.Util.redirectGithubToMirror;

public class BuilderTransformer implements ClassFileTransformer {

    private final String format;

    public BuilderTransformer(String format) {
        this.format = format; // format will be used to replace https://hub.spigotmc.org/versions/ .
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (Objects.equals(className, "org/spigotmc/builder/Builder")) {
            System.out.println("正在修改 org.spigotmc.builder.Builder ...");
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass builderClass = classPool.get("org.spigotmc.builder.Builder");
                CtMethod get = builderClass.getDeclaredMethod("get");
                get.insertBefore("{url = url.replaceFirst(\"https://hub.spigotmc.org/versions/\", \"" +
                        redirectGithubToMirror("https://raw.githubusercontent.com/SNWCreations/spigotversions/main/data", format) + "/" +
                        "\");}");
                builderClass.detach();
                System.out.println("成功! BuildTools 即将启动。享受构建过程吧! :D");
                System.out.println();
                return builderClass.toBytecode();
            } catch (IOException | NotFoundException | CannotCompileException e) {
                System.err.println("修改失败! 请将以下内容复制并到仓库新建 Issue 来联系作者。");
                e.printStackTrace();
                System.exit(1);
            }
        }

        return classfileBuffer;
    }
}
