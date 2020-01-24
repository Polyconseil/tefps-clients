package fr.polyconseil.smartcity.tefpsclients.tv;

import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclients.dto.PatchObject;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightCreationDTO;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightDTO;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightType;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.Plate;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TefpsTvClient {
    private static final String TV_API = "/api/cities/{cityId}/tickets/v1/{id}";

    private String tvUrl;
    private OAuth2HttpClient oAuth2Client;

    public TefpsTvClient (OAuth2HttpClient oAuth2Client, String tvUrl) {
        this.oAuth2Client = oAuth2Client;
        this.tvUrl = tvUrl;
    }

    public ParkingRightDTO createTv(
            String cityId,
            String tvId,
            ParkingRightType type,
            String source,
            @Nullable String fineLegalId,
            String zoneId,
            String parkId,
            String tariffCode,
            String plate,
            @Nullable String plateCountry,
            String pricingCategory,
            Date startDatetime,
            Date endDatetime,
            Date creationDatetime,
            @Nullable Date cancelDatetime,
            int price,
            @Nullable Map<String, Object> pricingContext
    ) throws IOException, URISyntaxException {
        ParkingRightCreationDTO dto = new ParkingRightCreationDTO();

        dto.setType(type);
        dto.setFineLegalId(fineLegalId);
        dto.setSource(source);
        dto.setZoneId(zoneId);
        dto.setParkId(parkId);
        dto.setTariffCode(tariffCode);

        Plate licensePlate = new Plate();
        licensePlate.setPlate(plate);
        licensePlate.setPricingCategory(pricingCategory);
        licensePlate.setPlateCountry(plateCountry);
        dto.setLicensePlate(licensePlate);

        dto.setStartDatetime(startDatetime);
        dto.setEndDatetime(endDatetime);
        dto.setCreationDatetime(creationDatetime);
        dto.setCancelDatetime(cancelDatetime);
        dto.setPrice(price);
        dto.setPricingContext(pricingContext);

        return oAuth2Client.put(
                buildURI(cityId, tvId),
                dto,
                ParkingRightDTO.class
        );
    }

    public ParkingRightDTO patchTv(
            String cityId,
            String tvId,
            @Nullable Date endDatetime,
            @Nullable Integer price,
            @Nullable Map<String, Object> pricingContext
    ) throws IOException, URISyntaxException {
        List<PatchObject> patchList = new ArrayList<>();

        if (endDatetime != null) {
            PatchObject patch = new PatchObject();
            patch.setOp(PatchObject.Operation.replace);
            patch.setPath("/endDatetime");
            patch.setValue(endDatetime);
            patchList.add(patch);
        }

        if (price != null) {
            PatchObject patch = new PatchObject();
            patch.setOp(PatchObject.Operation.replace);
            patch.setPath("/price");
            patch.setValue(price);
            patchList.add(patch);
        }

        if (pricingContext != null) {
            PatchObject patch = new PatchObject();
            patch.setOp(PatchObject.Operation.replace);
            patch.setPath("/pricingContext");
            patch.setValue(pricingContext);
            patchList.add(patch);
        }

        return oAuth2Client.patch(
                buildURI(cityId, tvId),
                patchList,
                ParkingRightDTO.class
        );
    }

    public ParkingRightDTO fetchTv(String cityId, String tvId) throws IOException, URISyntaxException {
        return oAuth2Client.get(
                buildURI(cityId, tvId),
                ParkingRightDTO.class
        );
    }

    public void deleteTv(String cityId, String tvId) throws IOException, URISyntaxException {
        oAuth2Client.delete(buildURI(cityId, tvId));
    }

    private URI buildURI(String cityId, String tvId) throws URISyntaxException {
        return OAuth2HttpClient.buildURI(tvUrl, TV_API, cityId, tvId);
    }
}
