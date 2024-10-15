package com.httpconfig.poc;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client(id = "versioning")
@Version("1")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/json")
public interface TestClient {

    @Get("/android/1")
    @SingleResult
    public String fetchVersion();

    @Get("/android/1")
    @Version("2")
    @SingleResult
    public String fetchVersion2();
}