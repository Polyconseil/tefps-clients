package fr.polyconseil.smartcity.tefpsclients.dto.pricing;

import fr.polyconseil.smartcity.tefpsclients.dto.tv.Plate;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FnmsParkingRightPriceRequestV1DTO {

    private Plate licensePlate;
    private String tariffCode;
    @Nullable
    private String zoneId;

    public Plate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(Plate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    @Nullable
    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(@Nullable String zoneId) {
        this.zoneId = zoneId;
    }
}