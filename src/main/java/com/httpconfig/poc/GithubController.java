package com.httpconfig.poc;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/github")
@ExecuteOn(TaskExecutors.BLOCKING)
public class GithubController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubController.class);
    private final GithubLowLevelClient githubLowLevelClient;
    private final GithubApiClient githubApiClient;

    private final TestClient testClient;
    public GithubController(GithubLowLevelClient githubLowLevelClient,
                            GithubApiClient githubApiClient, TestClient testClient) {
        this.githubLowLevelClient = githubLowLevelClient;
        this.githubApiClient = githubApiClient;
        this.testClient = testClient;
    }

    @Get("/test-client-versioning")
    public String fetch_version() {
            return testClient.fetchVersion();
    }


    @Get("/releases-lowlevel")
    @SingleResult
    Publisher<GithubRelease> releasesWithLowLevelClient() {
        LOGGER.info("Calling Low level Client");
        return githubLowLevelClient.fetchReleases();
    }

    @Get("/releases")
    @SingleResult
    Publisher<List<GithubRelease>> fetchReleases() {
        return githubApiClient.fetchReleases();
    }
}
