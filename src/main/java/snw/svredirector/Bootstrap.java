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

import java.lang.instrument.Instrumentation;
import java.util.Objects;

public class Bootstrap {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("Loading SVRedirector, by SNWCreations");
        instrumentation.addTransformer(new BuilderTransformer((Objects.equals(agentArgs, "fastgit"))));
    }
}
