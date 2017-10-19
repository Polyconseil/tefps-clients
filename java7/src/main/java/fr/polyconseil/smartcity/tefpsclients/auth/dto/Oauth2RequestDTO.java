package fr.polyconseil.smartcity.tefpsclients.auth.dto;

public class Oauth2RequestDTO {
    public static final String GRANT_CLIENT_CREDENTIALS = "client_credentials";
    public static final String GRANT_REFRESH_TOKEN = "refresh_token";

    private String grant_type;

    /// Always (password or client_credentials)

    private String client_id;

    private String client_secret;

    /// Or if having a refresh token from a previous call

    private String refresh_token;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
