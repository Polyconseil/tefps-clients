package fr.polyconseil.smartcity.tefpsclients.subscriber;

import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclients.dto.subscriber.LightSubscriberDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

public class TefpsSubscriberClient {

    private static final String SUBSCRIBER_API = "/api/cities/{cityId}/subscribers/{id}";
    private String subscriberUrl;
    private OAuth2HttpClient oAuth2Client;

    public TefpsSubscriberClient(OAuth2HttpClient oAuth2Client, String subscriberUrl) {
        this.oAuth2Client = oAuth2Client;
        this.subscriberUrl = subscriberUrl;
    }

    public void createOrUpdateSubscriber(
            String cityId,
            String subscriberId,
            String plate,
            String subscriptionPlanId,
            Date startOfValidityDatetime,
            Date endOfValidityDatetime,
            List<String> validityAreas
            ) throws IOException, URISyntaxException {

        LightSubscriberDTO dto = new LightSubscriberDTO();
        dto.setSubscriberId(subscriberId);
        dto.setPlate(plate);
        dto.setSubscriptionPlanId(subscriptionPlanId);
        dto.setStartOfValidityDatetime(startOfValidityDatetime);
        dto.setEndOfValidityDatetime(endOfValidityDatetime);
        dto.setValidityAreas(validityAreas);

        oAuth2Client.put(
            buildURI(cityId, subscriberId),
            dto,
            null
        );
    }

    public LightSubscriberDTO fetchSubscriber(String cityId, String subscriberId) throws IOException, URISyntaxException {
        return oAuth2Client.get(
            buildURI(cityId, subscriberId),
            LightSubscriberDTO.class
        );
    }

    public void deleteSubscriber(String cityId, String subscriberId) throws IOException, URISyntaxException {
        oAuth2Client.delete(buildURI(cityId, subscriberId));
    }

    private URI buildURI(String cityId, String subscriberId) throws URISyntaxException {
        return OAuth2HttpClient.buildURI(subscriberUrl, SUBSCRIBER_API, cityId, subscriberId);
    }
}
