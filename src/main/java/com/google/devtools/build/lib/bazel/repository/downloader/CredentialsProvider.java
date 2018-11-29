package com.google.devtools.build.lib.bazel.repository.downloader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsProvider {
    public CredentialsProvider() {
    }

    public String[] getCredentials(String host) throws IOException {
        return getCredentialsFromNetrc(host);
    }

    private static final Pattern NETRC_PATTERN = Pattern
            .compile("^\\s*machine\\s+([^\\s]+)\\s+login\\s+([^\\s]+)\\s+password\\s+([^\\s]+).*$");

    private String[] getCredentialsFromNetrc(String host) throws IOException {
        File home = new File(System.getProperty("user.home"));
        File netrc = new File(home, ".netrc");
        if (!netrc.exists())
            netrc = new File(home, "_netrc");
        if (!netrc.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(netrc))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Matcher m = NETRC_PATTERN.matcher(line);
                if (m.matches()) {
                    String netrcHost = m.group(1);
                    if (host.equals(netrcHost)) {
                        String login = m.group(2);
                        String password = m.group(3);
                        return new String[]{login, password};
                    }
                }
            }
        }

        return null;
    }
}
