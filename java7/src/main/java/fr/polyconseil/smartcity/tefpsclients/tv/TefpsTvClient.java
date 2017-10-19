package fr.polyconseil.smartcity.tefpsclients.tv;

import fr.polyconseil.smartcity.tefpsclients.auth.OAuth2HttpClient;
import fr.polyconseil.smartcity.tefpsclients.dto.PatchObject;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightCreationDTO;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightDTO;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightType;
import fr.polyconseil.smartcity.tefpsclients.dto.tv.Plate;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class TefpsTvClient {
    private final String TV_API = "/api/cities/{cityId}/tickets/v1";

    private String tvUrl;
    private OAuth2HttpClient oAuth2Client;

    public TefpsTvClient (OAuth2HttpClient oAuth2Client, String tvUrl) {
        this.oAuth2Client = oAuth2Client;
        this.tvUrl = tvUrl;
    }

    public ParkingRightDTO createTv(
            String cityId,
            String tvId,
            String source,
            @Nullable String fineLegalId,
            String zoneId,
            String parkId,
            String plate,
            @Nullable String plateCountry,
            String pricingCategory,
            Calendar startDatetime,
            Calendar endDatetime,
            Calendar creationDatetime,
            @Nullable Calendar cancelDatetime,
            int price,
            @Nullable Map<String, Object> pricingContext
    ) throws IOException {
        ParkingRightCreationDTO dto = new ParkingRightCreationDTO();

        dto.setType(ParkingRightType.TICKET);
        dto.setFineLegalId(fineLegalId);
        dto.setSource(source);
        dto.setZoneId(zoneId);
        dto.setParkId(parkId);

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
                buildPath(tvId),
                cityId,
                dto,
                ParkingRightDTO.class
        );
    }

    public ParkingRightDTO patchTv(
            String cityId,
            String tvId,
            @Nullable Calendar endDatetime,
            @Nullable Integer price,
            @Nullable Map<String, Object> pricingContext
    ) throws IOException {
        List<PatchObject> patchList = new ArrayList<PatchObject>();

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
                buildPath(tvId),
                cityId,
                patchList,
                ParkingRightDTO.class
        );
    }

    public ParkingRightDTO fetchTv(String cityId, String tvId) throws IOException {
        return oAuth2Client.get(
                buildPath(tvId),
                cityId,
                ParkingRightDTO.class
        );
    }

    public void deleteTv(String cityId, String tvId) throws IOException {
        oAuth2Client.delete(
                buildPath(tvId),
                cityId
        );
    }

    private String buildPath(String tvId) {
        return tvUrl + "/" + TV_API + "/" + tvId;
    }
}
