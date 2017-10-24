# Tefps Clients

## Available clients

- Java7 TefpsTvClient

### Java7 TefpsTvClient - Usage

```
import fr.polyconseil.smartcity.tefpsclient.tv.TefpsTvClient;
import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclient.dto.tv.ParkingRightDTO;

        TefpsTvClient client = new TefpsTvClient(new OAuth2HttpClient(
                "http://tefps-directory-host:port",
                "clientId",
                "clientSecret"),
                "http://tefps-tv-host:port"
        );

        ParkingRightDTO parkingRight = client.fetchTv("cityId", "tvId");
```