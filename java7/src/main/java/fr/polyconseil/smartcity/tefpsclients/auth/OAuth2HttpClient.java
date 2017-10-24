package fr.polyconseil.smartcity.tefpsclients.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polyconseil.smartcity.tefpsclients.auth.dto.Oauth2ResponseDTO;
import fr.polyconseil.smartcity.tefpsclients.auth.dto.TefpsError;
import fr.polyconseil.smartcity.tefpsclients.dto.PatchObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;


public class OAuth2HttpClient  {
    private static final String DIRECTORY_API = "/api/oauth2/v1/token";
    private static final String GRANT_CLIENT_CREDENTIALS = "client_credentials";

    private final String tokenUrl;
    private final String clientId;
    private final String clientSecret;
    private final ObjectMapper mapper;
    private final HttpClient httpClient;

    private String currentAccessToken;
    private Date currentAccessTokenExpiration;

    public OAuth2HttpClient(String tokenUrl, String clientId, String clientSecret) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.httpClient = HttpClientBuilder.create().build();
        this.mapper = new ObjectMapper();
    }

    private <T> T doExecute(HttpRequestBase requestBase, Class<T> valueType) throws IOException {
        HttpResponse response = httpClient.execute(requestBase);

        byte[] content = IOUtils.toByteArray(response.getEntity().getContent());

        if (response.getStatusLine().getStatusCode() != HTTP_OK) {
            TefpsError error = mapper.readValue(content, TefpsError.class);
            throw new TefpsCoreClientErrorException(error.getMessage(), error.getError(), error.getStatus());
        }

        return content.length != 0 ? mapper.readValue(content, valueType) : null;
    }

    private <T> T execute(HttpRequestBase requestBase, Class<T> valueType) throws IOException {
        String accessToken = getOrFetchAccessToken();
        requestBase.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        requestBase.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        return doExecute(requestBase, valueType);
    }

    private <T> T executeAuthenticated(HttpRequestBase requestBase, Class<T> valueType) throws IOException {
        try {
            return execute(requestBase, valueType);
        } catch (TefpsCoreClientErrorException e) {
            if (e.getStatus() == HTTP_UNAUTHORIZED) {
                currentAccessToken = null;
                return execute(requestBase, valueType);
            } else {
                throw e;
            }
        }
    }

    public synchronized String getOrFetchAccessToken() {
        if (currentAccessToken != null
                && currentAccessTokenExpiration != null
                && currentAccessTokenExpiration.after(new Date())) {
            return currentAccessToken;
        }

        try {
            Oauth2ResponseDTO oauth2Response = oauth2TokenClientCredentials();
            currentAccessToken = oauth2Response.getAccessToken();
            currentAccessTokenExpiration = new Date(System.currentTimeMillis() + oauth2Response.getExpiresIn() * 1000);
        } catch (IOException e) {
            throw new TefpsCoreOauth2TokenRetrievalException(e.getMessage(), e);
        } catch (Exception e) {
            throw new TefpsCoreOauth2TokenRetrievalException(e);
        }
        return currentAccessToken;
    }

    private Oauth2ResponseDTO oauth2TokenClientCredentials() throws IOException {
        List<NameValuePair> request = new ArrayList<NameValuePair>();
        request.add(new BasicNameValuePair("grant_type", GRANT_CLIENT_CREDENTIALS));
        request.add(new BasicNameValuePair("client_id", clientId));
        request.add(new BasicNameValuePair("client_secret", clientSecret));

        HttpPost postRequest = new HttpPost(tokenUrl + DIRECTORY_API);
        postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        postRequest.setEntity(new UrlEncodedFormEntity(request));

        return this.doExecute(postRequest, Oauth2ResponseDTO.class);
    }

    public <T> T get(URI uri, Class<T> valueType) throws IOException {
        HttpGet getRequest = new HttpGet(uri);
        return this.executeAuthenticated(getRequest, valueType);
    }

    public <T> T put(URI uri, Object entity, Class<T> valueType) throws IOException {
        HttpPut putRequest = new HttpPut(uri);
        putRequest.setEntity(new ByteArrayEntity(mapper.writeValueAsBytes(entity)));

        return this.executeAuthenticated(putRequest, valueType);
    }

    public void delete(URI uri) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(uri);
        this.executeAuthenticated(deleteRequest, Void.class);
    }

    public <T> T patch(URI uri, List<PatchObject> patchList, Class<T> valueType) throws IOException {
        HttpPatch patchRequest = new HttpPatch(uri);
        patchRequest.setEntity(new StringEntity(mapper.writeValueAsString(patchList)));

        return this.executeAuthenticated(patchRequest, valueType);
    }

    public static URI buildURI(String host, String path, String cityId, String id) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(host);
        builder.setPath(path.replace("{cityId}", cityId).replace("{id}", id));
        return builder.build();
    }

    public static class TefpsCoreOauth2TokenRetrievalException extends RuntimeException {

        TefpsCoreOauth2TokenRetrievalException(String message, Throwable cause) {
            super(message, cause);
        }

        TefpsCoreOauth2TokenRetrievalException(Exception e) {
            super(e);
        }
    }

    public static class TefpsCoreClientErrorException extends RuntimeException {
        final int status;
        final String error;

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        TefpsCoreClientErrorException(String message, String error, int status) {
            super(message);
            this.status = status;
            this.error = error;
        }

    }
}
