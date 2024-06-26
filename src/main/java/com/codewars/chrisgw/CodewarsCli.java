package com.codewars.chrisgw;

import com.codewars.chrisgw.restapi.CodeChallenge;
import com.codewars.chrisgw.restapi.CodeChallengeRank;
import com.codewars.chrisgw.restapi.CodewarsRestApi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


public class CodewarsCli {

    private static CodewarsRestApi codewarsRestApi;


    public static void main(String[] args) {
        codewarsRestApi = new CodewarsRestApi();

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
        Files.createDirectories(javaSrcPath.resolve(packagePath));

        if (Files.exists(javaFilePath)) {
            System.out.println("Code Challenge File already exists: " + javaFilePath);
            return;
        }
        System.out.println("Write Java File: " + javaFilePath);
        try (BufferedWriter writer = Files.newBufferedWriter(javaFilePath, CREATE_NEW)) {
            StringBuilder sb = new StringBuilder("package ");
            packagePath.iterator().forEachRemaining(path -> sb.append(path).append('.'));
            sb.deleteCharAt(sb.length() - 1).append(";\n\n");
            sb.append("/**\n");
            sb.append(" * <h2>").append(codeChallenge.getName()).append("</h2>\n");
            sb.append(" * ").append(getUrlJavaDoc(codeChallenge)).append("\n");
            sb.append(" */\n");
            sb.append("public class ").append(codeChallengeJavaFileName).append(" {\n");
            sb.append("\n}\n");
            writer.write(sb.toString());
        }
    }

    private static StringBuilder getUrlJavaDoc(CodeChallenge codeChallenge) {
        String url = codeChallenge.getUrl();
        return new StringBuilder().append("<a href=\"").append(url).append("\">").append(url).append("</a>");
    }


    private static String descriptionAsClassJavaDoc(CodeChallenge codeChallenge) {
        System.out.println(codeChallenge.getDescription());
        StringBuilder description = new StringBuilder(codeChallenge.getDescription().length());
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
        StringBuilder sb = new StringBuilder(s.length());
        Matcher matcher = dashOrUnderscoreDelimiterPattern.matcher(s);
        while (matcher.find()) {
            String followingUpperCaseLetter = matcher.group(1).toUpperCase();
            matcher.appendReplacement(sb, followingUpperCaseLetter);
        }
        matcher.appendTail(sb);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }


    private static String rankPackageName(CodeChallenge codeChallenge) {
        CodeChallengeRank codeRank = codeChallenge.getRank();
        if (codeRank.isNull()) {
            return "kyu";
        }
        String[] splitRankName = codeRank.getName().split("\\s+");
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
