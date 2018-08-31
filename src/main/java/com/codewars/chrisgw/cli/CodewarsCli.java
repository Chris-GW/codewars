package com.codewars.chrisgw.cli;

import com.codewars.chrisgw.restapi.CodeChallenge;
import com.codewars.chrisgw.restapi.CodewarsRestApi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * asdas dasdasd
 */
public class CodewarsCli {

    private static CodewarsRestApi codewarsRestApi;


    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("usage: <id_or_slug>");
                return;
            }
            Properties properties = new Properties();
            properties.load(CodewarsRestApi.class.getResourceAsStream("/application.properties"));
            codewarsRestApi = new CodewarsRestApi(properties);

            String idOrSlug = args[0];
            generateCodeChallengeFiles(idOrSlug);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateCodeChallengeFiles(String idOrSlug) throws IOException {
        CodeChallenge codeChallenge = codewarsRestApi.fetchCodeChallenge(idOrSlug);
        writeCodeChallengeJavaFile(codeChallenge, false);
        writeCodeChallengeJavaFile(codeChallenge, true);
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

        try (BufferedWriter writer = Files.newBufferedWriter(javaFilePath)) {
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
        StringBuilder description = new StringBuilder(codeChallenge.getDescription());
        Pattern codeExamplePattern = Pattern.compile("```(\\w+)");
        Matcher codeExampleMatcher = codeExamplePattern.matcher(description);
        int searchFrom = 0;
        while (codeExampleMatcher.find(searchFrom)) {
            String codeExampleLanguage = codeExampleMatcher.group(1);
            int codeExampleEnd = description.indexOf("```\n", codeExampleMatcher.end()) + "```\n\n".length();
            if ("java".equalsIgnoreCase(codeExampleLanguage)) {
                searchFrom = codeExampleEnd;
            } else {
                // delete other language code examples
                description.delete(codeExampleMatcher.start(), codeExampleEnd);
            }
        }
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
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return matcher.appendTail(sb).toString();
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
