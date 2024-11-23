package xyz.domknuddle.aoc.adventofcode2024.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

public class DayDownloader {

    public static void main(String[] args) throws IOException, InterruptedException {
        int day = AoCUtils.getDay();

        Path basePath = Path.of(System.getProperty("user.dir"));

        Path javaBasePath = basePath.resolve("src/main/java/xyz/domknuddle/aoc/adventofcode2024");
        Path javaDayDirPath = javaBasePath.resolve("day%02d".formatted(day));
        if (!Files.exists(javaDayDirPath)) {
            Files.createDirectory(javaDayDirPath);
        }

        Path interfacePath = javaDayDirPath.resolve("Day%02d.java".formatted(day));
        if (!Files.exists(interfacePath)) {
            InputStream input = Objects.requireNonNull(DayDownloader.class.getResourceAsStream("/util/DayInterface.java.txt"));
            String interfaceTemplate = IOUtils.toString(input);
            interfaceTemplate = interfaceTemplate.replace("{{day}}", "%02d".formatted(day));
            Files.writeString(interfacePath, interfaceTemplate, StandardOpenOption.CREATE_NEW);
        }

        String implName = "Ugly";
        Path impPath = javaDayDirPath.resolve("Day%02d%s.java".formatted(day, implName));
        if (!Files.exists(impPath)) {
            InputStream input = Objects.requireNonNull(DayDownloader.class.getResourceAsStream("/util/DayImpl.java.txt"));
            String implTemplate = IOUtils.toString(input);
            implTemplate = implTemplate.replace("{{day}}", "%02d".formatted(day));
            implTemplate = implTemplate.replace("{{name}}", "%s".formatted(implName));
            Files.writeString(impPath, implTemplate, StandardOpenOption.CREATE_NEW);
        }

        Path testBasePath = basePath.resolve("src/test/java/xyz/domknuddle/aoc/adventofcode2024");
        Path testDayDirPath = testBasePath.resolve("day%02d".formatted(day));
        if (!Files.exists(testDayDirPath)) {
            Files.createDirectory(testDayDirPath);
        }

        Path testPath = testDayDirPath.resolve("Day%02dTest.java".formatted(day));
        if (!Files.exists(testPath)) {
            InputStream input = Objects.requireNonNull(DayDownloader.class.getResourceAsStream("/util/DayTest.java.txt"));
            String testTemplate = IOUtils.toString(input);
            testTemplate = testTemplate.replace("{{day}}", "%02d".formatted(day));
            Files.writeString(testPath, testTemplate, StandardOpenOption.CREATE_NEW);
        }

        Path resourceBasePath = basePath.resolve("src/main/resources/xyz/domknuddle/aoc/adventofcode2024");
        Path resourceDayPath = resourceBasePath.resolve("day%02d".formatted(day));
        if (!Files.exists(resourceDayPath)) {
            Files.createDirectory(resourceDayPath);
        }

        HttpClient client = AoCUtils.getClient();

        Path challengeInputPath = resourceDayPath.resolve("input.txt");
        if (!Files.exists(challengeInputPath)) {
            HttpRequest challengeInputRequest = getChallengeInputRequest(day);
            HttpResponse<String> challengeInput = client.send(challengeInputRequest, HttpResponse.BodyHandlers.ofString());
            Files.writeString(challengeInputPath, challengeInput.body());
        }

        System.out.println("Challenge can be found at: https://adventofcode.com/2024/day/" + day);
    }

    private static HttpRequest getChallengeInputRequest(int day) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://adventofcode.com/2024/day/" + day + "/input"))
                .build();
    }

}
