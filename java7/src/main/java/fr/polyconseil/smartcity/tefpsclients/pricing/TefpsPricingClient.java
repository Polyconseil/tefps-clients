package fr.polyconseil.smartcity.tefpsclients.pricing;

import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclients.dto.pricing.*;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.Plate;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TefpsPricingClient {
    public static final String API_FNMS_TICKET_VALUES = "/api/fnms/cities/{cityId}/ticket-values/v1";
    public static final String API_FNMS_PARKING_DETAIL = "/api/fnms/cities/{cityId}/parking-detail/v1";

    private String pricingUrl;
    private OAuth2HttpClient oAuth2Client;

    public TefpsPricingClient(OAuth2HttpClient oAuth2Client, String pricingUrl) {
        this.oAuth2Client = oAuth2Client;
        this.pricingUrl = pricingUrl;
    }

    public FnmsParkingRightPriceResultV1DTO fetchTicketValues(
            String cityId,
            String plate,
            String plateCountry,
            @Nullable String pricingCategory,
            String tariffCode,
            @Nullable String zoneId
    ) throws IOException, URISyntaxException {
        Plate plateDTO = new Plate();
        plateDTO.setPlate(plate);
        plateDTO.setPlateCountry(plateCountry);
        plateDTO.setPricingCategory(pricingCategory);

        FnmsParkingRightPriceRequestV1DTO parkingRightPriceRequestV1DTO = new FnmsParkingRightPriceRequestV1DTO();
        parkingRightPriceRequestV1DTO.setLicensePlate(plateDTO);
        parkingRightPriceRequestV1DTO.setTariffCode(tariffCode);
        parkingRightPriceRequestV1DTO.setZoneId(zoneId);

        return oAuth2Client.post(
                buildURI(cityId, API_FNMS_TICKET_VALUES),
                parkingRightPriceRequestV1DTO,
                FnmsParkingRightPriceResultV1DTO.class
        );
    }

    public FnmsParkingDetailResultV1DTO fetchParkingDetail(
            String cityId,
            String plate,
            String plateCountry,
            @Nullable String pricingCategory
    ) throws IOException, URISyntaxException {
        Plate plateDTO = new Plate();
        plateDTO.setPlate(plate);
        plateDTO.setPlateCountry(plateCountry);
        plateDTO.setPricingCategory(pricingCategory);

        FnmsParkingDetailRequestV1DTO fnmsParkingDetailRequestV1DTO = new FnmsParkingDetailRequestV1DTO();
        fnmsParkingDetailRequestV1DTO.setLicensePlate(plateDTO);
        return oAuth2Client.post(
                buildURI(cityId, API_FNMS_PARKING_DETAIL),
                fnmsParkingDetailRequestV1DTO,
                FnmsParkingDetailResultV1DTO.class
        );
    }

    private URI buildURI(String cityId, String endpoint) throws URISyntaxException {
        return OAuth2HttpClient.buildURI(pricingUrl, endpoint, cityId);
    }
}
