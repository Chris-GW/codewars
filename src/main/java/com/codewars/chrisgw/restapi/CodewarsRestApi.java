package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.util.Properties;

import static java.util.Objects.requireNonNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;


public class CodewarsRestApi {

    public static final String CODEWARS_API_URL_PROPERTY = "codewars.apiUrl";
    public static final String CODEWARS_API_TOKEN_PROPERTY = "codewars.apiToken";

    private Properties properties;
    private Client client;
    private WebTarget codewarsRestApi;


    public CodewarsRestApi(Properties properties) {
        this(properties, ClientBuilder.newClient());
    }

    public CodewarsRestApi(Properties properties, Client client) {
        this.properties = requireNonNull(properties);
        this.client = requireNonNull(client);
        this.client.register(ObjectMapperContextResolver.class);
        this.client.register(apiTokenAuthorizationHeaderFilter());
        this.client.register(handle404asNullResponseFilter());
        this.codewarsRestApi = client.target(properties.getProperty(CODEWARS_API_URL_PROPERTY));
    }

    private ClientResponseFilter handle404asNullResponseFilter() {
        return (requestContext, responseContext) -> {
            if (Status.NOT_FOUND.getStatusCode() == responseContext.getStatus()) {
                responseContext.setStatus(200);
                responseContext.setEntityStream(new ByteArrayInputStream(new byte[0]));
            }
        };
    }


    public User fetchUser(String idOrUsername) {
        WebTarget usersWebTarget = codewarsRestApi.path("users/{idOrUsername}")
                .resolveTemplate("idOrUsername", idOrUsername);
        User user = usersWebTarget.request(APPLICATION_JSON_TYPE).get(User.class);
        System.out.println("GET " + usersWebTarget + " = " + user);
        return user;
    }


    public CodeChallenge fetchCodeChallenge(String idOrSlug) {
        WebTarget codeChallengesWebTarget = codewarsRestApi.path("code-challenges/{idOrSlug}")
                .resolveTemplate("idOrSlug", idOrSlug);
        CodeChallenge codeChallenge = codeChallengesWebTarget.request(APPLICATION_JSON_TYPE).get(CodeChallenge.class);
        System.out.println("GET " + codeChallengesWebTarget + " = " + codeChallenge);
        return codeChallenge;
    }


    private ClientRequestFilter apiTokenAuthorizationHeaderFilter() {
        return requestContext -> {
            String apiToken = properties.getProperty(CODEWARS_API_TOKEN_PROPERTY);
            requestContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, apiToken);
        };
    }


    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        @Override
        public ObjectMapper getContext(Class<?> type) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jdk8Module());
            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper;
        }

    }

}
