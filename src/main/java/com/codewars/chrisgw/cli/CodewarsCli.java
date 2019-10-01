package com.codewars.chrisgw.cli;

import com.codewars.chrisgw.restapi.CodeChallenge;
import com.codewars.chrisgw.restapi.CodewarsRestApi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


public class CodewarsCli {

    private static CodewarsRestApi codewarsRestApi;


    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(CodewarsRestApi.class.getResourceAsStream("/application.properties"));
        codewarsRestApi = new CodewarsRestApi(properties);

        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("codeChallenge id or slug: ");
                String idOrSlug = sc.nextLine();
                if ("exit".equalsIgnoreCase(idOrSlug)) {
                    return;
                } else {
                    generateCodeChallengeFiles(idOrSlug);
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private static void generateCodeChallengeFiles(String idOrSlug) throws IOException {
        CodeChallenge codeChallenge = codewarsRestApi.fetchCodeChallenge(idOrSlug);
        if (codeChallenge != null) {
            writeCodeChallengeJavaFile(codeChallenge, false);
            writeCodeChallengeJavaFile(codeChallenge, true);
        } else {
            System.out.println("Could not find CodeChallenge for id or slug: " + idOrSlug);
        }
    }


    private static void writeCodeChallengeJavaFile(CodeChallenge codeChallenge, boolean test) throws IOException {
        Path javaSrcPath;
        if (test) {
            javaSrcPath = Paths.get("src/test/java");
        } else {
            javaSrcPath = Paths.get("src/main/java");
        }
        String codeChallengeJavaFileName = getCodeChallengeJavaFileName(codeChallenge, test);
        Path packagePath = getPackagePath(codeChallenge);
        Path javaFilePath = javaSrcPath.resolve(packagePath).resolve(codeChallengeJavaFileName + ".java");
        System.out.println("Write Java File: " + javaFilePath);
        Files.createDirectories(javaSrcPath.resolve(packagePath));

        try (BufferedWriter writer = Files.newBufferedWriter(javaFilePath, CREATE_NEW)) {
            StringBuilder sb = new StringBuilder("package ");
            packagePath.iterator().forEachRemaining(path -> sb.append(path).append('.'));
            sb.deleteCharAt(sb.length() - 1);
            sb.append(";\n\n");
            if (!test) {
                sb.append("/**\n");
                sb.append(" * ").append(descriptionAsClassJavaDoc(codeChallenge)).append("\n */\n");
            }
            sb.append("public class ").append(codeChallengeJavaFileName).append(" {\n");
            sb.append("\n}\n");
            writer.write(sb.toString());
        }
    }

    private static String descriptionAsClassJavaDoc(CodeChallenge codeChallenge) {
        System.out.println(codeChallenge.getDescription());
        StringBuffer description = new StringBuffer(codeChallenge.getDescription().length());
        Pattern codeExamplePattern = Pattern.compile("``+(\\w+)\\s+(.+)``+\\s*", Pattern.DOTALL);
        Matcher codeExampleMatcher = codeExamplePattern.matcher(codeChallenge.getDescription());
        while (codeExampleMatcher.find()) {
            String codeExampleLanguage = codeExampleMatcher.group(1);
            if (!"java".equalsIgnoreCase(codeExampleLanguage)) {
                codeExampleMatcher.appendReplacement(description, "");
            }
        }
        codeExampleMatcher.appendTail(description);
        return description.toString().replaceAll("\n", "\n * ").trim();
    }

    private static String getCodeChallengeJavaFileName(CodeChallenge codeChallenge, boolean test) {
        String className = toCamelCase(codeChallenge.getSlug());
        if (test) {
            className += "Test";
        }
        return className;
    }


    static Pattern dashOrUnderscoreDelimiterPattern = Pattern.compile("[\\-_]+([a-zA-Z0-9])");

    static String toCamelCase(String s) {
        StringBuffer sb = new StringBuffer(s.length());
        Matcher matcher = dashOrUnderscoreDelimiterPattern.matcher(s);
        while (matcher.find()) {
            String followingUpperCaseLetter = matcher.group(1).toUpperCase();
            matcher.appendReplacement(sb, followingUpperCaseLetter);
        }
        matcher.appendTail(sb);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return matcher.toString();
    }


    private static String rankPackageName(CodeChallenge codeChallenge) {
        String[] splitRankName = codeChallenge.getRank().getName().split("\\s+");
        String rank = splitRankName[0];
        String name = splitRankName[1];
        return name + "_" + rank;
    }


    private static Path getPackagePath(CodeChallenge codeChallenge) {
        return Paths.get("com/codewars/chrisgw")
                .resolve(codeChallenge.getCategory())
                .resolve(rankPackageName(codeChallenge));
    }


}
