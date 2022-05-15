package snw.svredirector;

import java.util.Arrays;

public class Util {

    // standard format:
    // USER - username
    // REPO - repository name
    // BRANCH - branch
    // FILE - path to file
    // ORIGINAL_URL - the value of format parameter
    // "format" format: https://raw.githubusercontent.com/{USER}/{REPO}/{BRANCH}/{FILE}
    public static String redirectGithubToMirror(String target, String format) {
        String[] parts = target.split("/");
        String user = parts[3];
        String repo = parts[4];
        String branch = parts[5];
        String file = String.join("/", Arrays.copyOfRange(parts, 6, parts.length));
        return format
                .replace("{ORIGINAL_URL}", target)
                .replace("{USER}", user)
                .replace("{REPO}", repo)
                .replace("{BRANCH}", branch)
                .replace("{FILE}", file);
    }
}
