package com.codewars.chrisgw.restapi;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;


public class CodewarsRestApiTest {

    private CodewarsRestApi codewarsRestApi;


    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(CodewarsRestApi.class.getResourceAsStream("/application.properties"));
        codewarsRestApi = new CodewarsRestApi(properties);
    }


    @Test
    public void fetchUser() {
        String username = "Chris-GW";
        User user = codewarsRestApi.fetchUser(username);
        System.out.println(user);
        Assert.assertThat(user, CoreMatchers.notNullValue());
    }


    @Test
    public void fetchCodeChallenge() {
        String id = "5277c8a221e209d3f6000b56";
        CodeChallenge codeChallenge = codewarsRestApi.fetchCodeChallenge(id);
        System.out.println(codeChallenge);
        Assert.assertThat(codeChallenge, CoreMatchers.notNullValue());
    }

}