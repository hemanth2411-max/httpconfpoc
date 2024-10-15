package com.httpconfig.poc;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.ClientFilter;
import io.micronaut.http.annotation.RequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientFilter("/repos/**")
@Requires(property = GithubConfiguration.PREFIX + ".username")
@Requires(property = GithubConfiguration.PREFIX + ".token")
public class GithubFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubFilter.class);

    private final GithubConfiguration configuration;

    public GithubFilter(GithubConfiguration configuration) {
        this.configuration = configuration;
    }

    @RequestFilter
    public void doFilter(MutableHttpRequest<?> request) {
        LOGGER.info("Authenticating the user");
        request.basicAuth(configuration.username(), configuration.token());
    }
}