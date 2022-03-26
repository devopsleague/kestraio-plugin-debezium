package io.kestra.plugin.debezium.postgres;

import com.google.common.io.CharStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Objects;

public abstract class TestUtils {
    public static File resourceToFile(String file) {
        try {
            return new File(Objects.requireNonNull(TestUtils.class.getClassLoader()
                    .getResource(file))
                .toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hostname() {
        return "127.0.0.1";
    }

    public static String port() {
        return "65432";
    }

    public static String username() {
        return "postgres";
    }

    public static String password() {
        return "pg_passwd";
    }

    public static PostgresInterface.SslMode sslMode() {
        return PostgresInterface.SslMode.REQUIRE;
    }

    public static String content(String file) {
        try {
            return CharStreams.toString(new InputStreamReader(new FileInputStream(resourceToFile(file))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String cert() {
        return content("ssl/client.crt");
    }

    public static String key() {
        return content("ssl/client.key");
    }

    public static String keyNoPass() {
        return content("ssl/client-no-pass.key");
    }

    public static String keyPass() {
        return "p4ssphrase";
    }

    public static String ca() {
        return content("ssl/ca.crt");
    }
}
