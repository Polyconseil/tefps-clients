# Tefps Clients

## Available clients

- Java7 TefpsTvClient

### Java7 TefpsTvClient - Installation

Add maven dependency to your pom.xml

```xml
<dependency>
    <groupId>fr.polyconseil.smartcity</groupId>
    <artifactId>tefps-clients</artifactId>
    <version>1.0.0</version>
</dependency>
```


### Java7 TefpsTvClient - Usage

```java
import fr.polyconseil.smartcity.tefpsclient.tv.TefpsTvClient;
import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclient.dto.tv.ParkingRightDTO;

//...

TefpsTvClient client = new TefpsTvClient(new OAuth2HttpClient(
        "http://tefps-directory-host:port",
        "clientId",
        "clientSecret"),
        "http://tefps-tv-host:port"
);

ParkingRightDTO parkingRight = client.fetchTv("cityId", "tvId");
```