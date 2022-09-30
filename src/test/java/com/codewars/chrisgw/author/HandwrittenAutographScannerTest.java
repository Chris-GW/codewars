package com.codewars.chrisgw.author;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.StringUtils.capitalize;


public class HandwrittenAutographScannerTest {

    private static List<String> vornamen;
    private static List<String> nachnamen;
    private Random random;


    @BeforeClass
    public static void beforeClass() throws Exception {
        Path vornamenPath = Paths.get(HandwrittenAutographScannerTest.class.getResource("/firstNames.txt").toURI());
        vornamen = Files.readAllLines(vornamenPath, StandardCharsets.UTF_8);
        Path nachnamenPath = Paths.get(HandwrittenAutographScannerTest.class.getResource("/lastNames.txt").toURI());
        nachnamen = Files.readAllLines(nachnamenPath, StandardCharsets.UTF_8);
    }


    @Before
    public void setUp() throws Exception {
        random = new Random();
    }


    @Test
    public void test1() {
        int dictonarySize = 300_000;
        List<String> dictonary = new ArrayList<>(dictonarySize);
        for (int i = 0; i < dictonarySize; i++) {
            int vornameIndex = random.nextInt(vornamen.size());
            String vorname = vornamen.get(vornameIndex);

            int nachnameIndex = random.nextInt(nachnamen.size());
            String nachname = nachnamen.get(nachnameIndex);
            String name = capitalize(vorname.toLowerCase()) + " " + capitalize(nachname.toLowerCase());
            if (!dictonary.stream()
                    .filter(HandwrittenAutographScanner.autographMatcher(nachname))
                    .findAny()
                    .isPresent()) {
                dictonary.add(name);
            }
        }
        System.out.println("dictonarySize: " + dictonary.size());

        for (int i = 0; i < 100_000; i++) {
            int dictonaryIndex = random.nextInt(dictonarySize);
            String readAutograph = dictonary.get(dictonaryIndex);

            String resolveAutograph = HandwrittenAutographScanner.resolveAutograph(dictonary, readAutograph);
            Assert.assertEquals("resolveAutograph", readAutograph, resolveAutograph);
        }
    }

}