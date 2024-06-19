package com.codewars.chrisgw.restapi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CodewarsRestApiTest {

    private CodewarsRestApi codewarsRestApi;


    @BeforeEach
    public void setUp() throws Exception {
        codewarsRestApi = new CodewarsRestApi();
    }


    @Test
    public void fetchUser() {
        String username = "Chris-GW";
        User user = codewarsRestApi.fetchUser(username);
        System.out.println(user);
        assertNotNull(user, "user");
    }


    @Test
    public void fetchCodeChallenge() {
        String id = "5277c8a221e209d3f6000b56";
        CodeChallenge codeChallenge = codewarsRestApi.fetchCodeChallenge(id);
        System.out.println(codeChallenge);
        assertNotNull(codeChallenge, "codeChallenge");
    }

}