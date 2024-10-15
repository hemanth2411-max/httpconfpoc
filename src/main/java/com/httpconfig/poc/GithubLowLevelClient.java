package com.httpconfig.poc;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import java.net.URI;
import java.util.List;
import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;
@Singleton
public class GithubLowLevelClient {
    private final HttpClient httpClient;
    private final URI uri;
    public GithubLowLevelClient(@Client(id = "github") HttpClient httpClient,
                                GithubConfiguration configuration) {
        this.httpClient = httpClient;
        uri = UriBuilder.of("/repos")
                .path(configuration.username())
                .path(configuration.repo())
                .build();

    }
    Publisher<GithubRelease> fetchReleases() {
        HttpRequest<?> req = HttpRequest.GET(uri)
                .header(USER_AGENT, "Micronaut HTTP Client")
                .header(ACCEPT, "application/json");
        return httpClient.retrieve(req, GithubRelease.class);
    }
}