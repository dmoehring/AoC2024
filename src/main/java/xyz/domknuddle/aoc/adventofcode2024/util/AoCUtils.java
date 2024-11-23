package xyz.domknuddle.aoc.adventofcode2024.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AoCUtils {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream resourceAsStream = DayDownloader.class.getResourceAsStream("/util/application.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new FUCK(e);
        }
    }

    private AoCUtils() {
    }

    public static String getSessionCookie() {
        String property = properties.getProperty("cookie.session");
        return property;
    }

    public static int getDay() {
        String dayOfMonth = Integer.toString(LocalDate.now().getDayOfMonth());
        return Integer.parseInt(properties.getProperty("day", dayOfMonth));
    }

    public static HttpClient getClient() {
        return HttpClient.newBuilder().cookieHandler(new CookieHandler() {
            @Override
            public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) {
                return Map.of("Cookie",
                        List.of("session=" + AoCUtils.getSessionCookie()));
            }

            @Override
            public void put(URI uri, Map<String, List<String>> responseHeaders) {
            }
        }).build();
    }
}
