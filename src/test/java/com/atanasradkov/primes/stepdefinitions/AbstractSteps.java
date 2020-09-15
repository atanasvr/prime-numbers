package com.atanasradkov.primes.stepdefinitions;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract step definition implementation
 * Http request are made using {@link RestTemplate} RestTemplate
 */
@SuppressWarnings("unchecked")
public class AbstractSteps {

  protected String getBaseUrl() {
    return "http://localhost:8080";
  }

  //Parsed http request json body
  Map<String, Object> body;

  private HttpHeaders httpHeaders;

  //@Autowired
  private final TestRestTemplate restTemplate;

  //Stored http response
  private ResponseEntity<String> responseEntity;
  private final ObjectMapper objectMapper;

  //List of query params for future use
  private final Map<String, String> queryParameters;

  public AbstractSteps() {
    this.restTemplate = new TestRestTemplate();
    this.objectMapper = new ObjectMapper();
    this.httpHeaders = new HttpHeaders();
    this.httpHeaders = new HttpHeaders();
    this.queryParameters = new HashMap<>();
  }

  /**
   * Add an http header
   * {@link #httpHeaders}
   * @param name header name
   * @param value header value
   */
  void setHeader(String name, String value) {
    Assert.notNull(name, "name should not be empty");
    Assert.notNull(value, "value should not be null");
    httpHeaders.set(name, value);
  }

  /**
   * Add an HTTP query parameter
   * {@link #queryParameters}
   * @param newParams Map of parameters
   */
  void addQueryParameters(Map<String, String> newParams){
    Assert.notNull(newParams, "Parameters should not be null");
    Assert.isTrue(!newParams.isEmpty(), "new params should not be empty");
    queryParameters.putAll(newParams);
  }

  /**
   * Perform an http request
   * Store the http response to responseEntity {@link #responseEntity}
   * @param resource resource to consume
   * @param method HttpMethod
   */
  void request(String resource, HttpMethod method) {
    Assert.notNull(resource, "resource should not be null");
    Assert.isTrue(!resource.isEmpty(), "resource should not be empty");

    Assert.notNull(method, "http method not supplied");

    boolean writeMode = !HttpMethod.GET.equals(method)
            && !HttpMethod.DELETE.equals(method)
            && !HttpMethod.OPTIONS.equals(method)
            && !HttpMethod.HEAD.equals(method);

    if(!resource.contains("/")) {
      resource = "/" + resource;
    }

    HttpEntity httpEntity;

    if(writeMode) {
      Assert.notNull(body, "body should not be null");
      httpEntity = new HttpEntity(body, httpHeaders);
    } else {
      httpEntity = new HttpEntity(httpHeaders);
    }

    responseEntity = this.restTemplate.exchange(getBaseUrl()+resource, method, httpEntity, String.class,queryParameters);
    Assert.notNull(responseEntity, "response should not be null");
  }

  void executeGetRequest(String resource) {
    responseEntity = this.restTemplate.getForEntity(getBaseUrl()+resource, String.class);
    Assert.notNull(responseEntity, "response should not be null");
  }

  /**
   * Check http response status code
   *
   * @param status the expected or unexpected status
   * @param isNot  if true, test equality, inequality if false
   */
  void checkStatus(int status, boolean isNot) {
    Assert.isTrue(status > 0, "should return valid status");
    Assert.isTrue(isNot == (responseEntity.getStatusCodeValue() != status),
            "status code is not expected");
  }

  /**
   * Test response body is ot type json
   * {@link #responseEntity}
   * @throws IOException json parse exception
   */
  void checkJsonBody() throws IOException {
    String body = responseEntity.getBody();
    Assert.notNull(body, "body should not be null");
    Assert.isTrue(!body.isEmpty(), "body should not be empty");

    // Check body json structure is valid
    objectMapper.readValue(body,Map.class);
  }

  /**
   * Test body content
   * {@link #responseEntity}
   * @param bodyValue expected content
   */
  void checkBodyContains(String bodyValue) {
    Assert.notNull(bodyValue, "value should not be null");
    Assert.isTrue(!bodyValue.isEmpty(), "value should not be empty");

    Assert.isTrue(responseEntity.getBody().contains(bodyValue), "unexpected content in body");
  }

}
