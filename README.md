# Tefps Clients

## Available clients

- Java7 TefpsTvClient
- Java7 TefpsSubscriberClient
- Java7 TefpsPricingClient

### Java7 All clients - Installation

Add maven dependency to your pom.xml

```xml
<dependency>
    <groupId>fr.polyconseil.smartcity</groupId>
    <artifactId>tefps-clients</artifactId>
    <version>1.4.0</version>
</dependency>
```


### Java7 TefpsTvClient - Usage

```java
import fr.polyconseil.smartcity.tefpsclient.tv.TefpsTvClient;
import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclient.dto.tv.ParkingRightDTO;

//...
// Instantiate client
TefpsTvClient client = new TefpsTvClient(new OAuth2HttpClient(
        "http://tefps-directory-host:port",
        "clientId",
        "clientSecret"),
        "http://tefps-tv-host:port"
);

// Fetch Parking Right Sample
ParkingRightDTO parkingRight = client.fetchTv("cityId", "tvId");

// Delete Parking Right Sample
ParkingRightDTO parkingRight = client.deleteTv("cityId", "tvId");
```


### Java7 TefpsSubscriberClient - Usage

```java
import fr.polyconseil.smartcity.tefpsclient.subscriber.TefpsSubscriberClient;
import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclient.dto.subscriber.LightSubscriberDTO;

//...
// Instantiate client
TefpsSubscriberClient client = new TefpsSubscriberClient(new OAuth2HttpClient(
        "http://tefps-directory-host:port",
        "clientId",
        "clientSecret"),
        "http://tefps-subscription-host:port"
);

// Fetch Subscriber Sample
LightSubscriberDTO subscriber = client.fetchSubscriber("cityId", "subscriberId");
```

### Java7 TefpsPricingClient - Usage

```java
import fr.polyconseil.smartcity.tefpsclient.pricing.TefpsPricingClient;
import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclient.dto.pricing.FnmsParkingDetailResultV1DTO ;

//...
// Instantiate client
TefpsPricingClient client = new TefpsPricingClient(new OAuth2HttpClient(
        "http://tefps-directory-host:port",
        "clientId",
        "clientSecret"),
        "http://tefps-pricing-host:port"
);

FnmsParkingDetailResultV1DTO result = client.fetchParkingDetail("cityId", "AA-123-AA", "FR", null);
```
