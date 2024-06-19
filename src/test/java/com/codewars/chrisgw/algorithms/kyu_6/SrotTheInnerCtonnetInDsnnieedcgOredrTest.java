package com.codewars.chrisgw.algorithms.kyu_6;

import org.junit.jupiter.api.Test;

import static com.codewars.chrisgw.algorithms.kyu_6.SrotTheInnerCtonnetInDsnnieedcgOredr.sortTheInnerContent;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SrotTheInnerCtonnetInDsnnieedcgOredrTest {


    @Test
    public void exampleTests() {
        assertEquals("srot the inner ctonnet in dsnnieedcg oredr",
                sortTheInnerContent("sort the inner content in descending order"));
        assertEquals("wiat for me", sortTheInnerContent("wait for me"));
        assertEquals("tihs ktaa is esay", sortTheInnerContent("this kata is easy"));
    }

}
