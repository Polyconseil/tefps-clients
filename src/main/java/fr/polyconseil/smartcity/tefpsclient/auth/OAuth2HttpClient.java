package fr.polyconseil.smartcity.tefpsclient.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polyconseil.smartcity.tefpsclient.auth.dto.Oauth2RequestDTO;
import fr.polyconseil.smartcity.tefpsclient.auth.dto.Oauth2ResponseDTO;
import fr.polyconseil.smartcity.tefpsclient.auth.dto.TefpsError;
import fr.polyconseil.smartcity.tefpsclient.dto.PatchObject;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;


public class OAuth2HttpClient  {

    private final String tokenUrl;
    private final String clientId;
    private final String clientSecret;

    private String currentAccessToken;
    private Calendar currentAccessTokenExpiration;

    private HttpClient httpClient;

    public OAuth2HttpClient(String tokenUrl, String clientId, String clientSecret) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.httpClient = HttpClientBuilder.create().build();
    }

    private <T> T execute(HttpRequestBase requestBase, Class<T> valueType) throws IOException {
        HttpResponse response = httpClient.execute(requestBase);

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        StringBuilder result = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            result.append(line);
        }
        br.close();

        ObjectMapper mapper = new ObjectMapper();

        if (response.getStatusLine().getStatusCode() != HTTP_OK) {
            TefpsError error = mapper.readValue(result.toString(), TefpsError.class);
            throw new TefpsCoreClientErrorException(error.getMessage(), error.getError(), error.getStatus());
        }

        return result.toString().length() != 0 ? mapper.readValue(result.toString(), valueType) : null;
    }

    private <T> T executeAuthenticated(HttpRequestBase requestBase, Class<T> valueType) throws IOException {
        String accessToken = getOrFetchAccessToken();
        requestBase.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        requestBase.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return execute(requestBase, valueType);
    }

    private synchronized String getOrFetchAccessToken() {
        if (currentAccessToken != null) {
            return currentAccessToken;
        }

        try {
            Oauth2ResponseDTO oauth2Response = oauth2TokenClientCredentials();
            currentAccessToken = oauth2Response.getAccessToken();
            currentAccessTokenExpiration = new GregorianCalendar();
            currentAccessTokenExpiration.add(Calendar.SECOND, oauth2Response.getExpiresIn());
        } catch (IOException e) {
            throw new TefpsCoreOauth2TokenRetrievalException(e.getMessage(), e);
        } catch (Exception e) {
            throw new TefpsCoreOauth2TokenRetrievalException(e);
        }
        return currentAccessToken;
    }

    private String prepareUri(String uri, String cityId) {
        return uri.replace("{cityId}", cityId);
    }

    private Oauth2ResponseDTO oauth2TokenClientCredentials() throws IOException {
        List<NameValuePair> request = new ArrayList<NameValuePair>();
        request.add(new BasicNameValuePair("grant_type", Oauth2RequestDTO.GRANT_CLIENT_CREDENTIALS));
        request.add(new BasicNameValuePair("client_id", clientId));
        request.add(new BasicNameValuePair("client_secret", clientSecret));

        HttpPost postRequest = new HttpPost(tokenUrl);
        postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        postRequest.setEntity(new UrlEncodedFormEntity(request));

        return this.execute(postRequest, Oauth2ResponseDTO.class);
    }

    public <T> T get(String uri, String cityId, Class<T> valueType) throws IOException {
        HttpGet getRequest = new HttpGet(prepareUri(uri, cityId));
        return this.executeAuthenticated(getRequest, valueType);
    }

    public <T> T put(String uri, String cityId, Object entity, Class<T> valueType) throws IOException {
        HttpPut putRequest = new HttpPut(prepareUri(uri, cityId));

        ObjectMapper mapper = new ObjectMapper();
        putRequest.setEntity(new StringEntity(mapper.writeValueAsString(entity)));

        return this.executeAuthenticated(putRequest, valueType);
    }

    public void delete(String uri, String cityId) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(prepareUri(uri, cityId));
        this.executeAuthenticated(deleteRequest, Void.class);
    }

    public <T> T patch(String uri, String cityId, List<PatchObject> patchList, Class<T> valueType) throws IOException {
        HttpPatch patchRequest = new HttpPatch(prepareUri(uri, cityId));

        ObjectMapper mapper = new ObjectMapper();
        patchRequest.setEntity(new StringEntity(mapper.writeValueAsString(patchList)));

        return this.executeAuthenticated(patchRequest, valueType);
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

        TefpsCoreClientErrorException(String message, String error, int status) {
            super(message);
            this.status = status;
            this.error = error;
        }

    }
}
