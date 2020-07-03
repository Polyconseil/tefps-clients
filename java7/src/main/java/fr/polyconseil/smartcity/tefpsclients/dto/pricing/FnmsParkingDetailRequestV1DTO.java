package fr.polyconseil.smartcity.tefpsclients.dto.pricing;

import fr.polyconseil.smartcity.tefpsclients.dto.tv.Plate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FnmsParkingDetailRequestV1DTO {
    private Plate licensePlate;

    public Plate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(Plate licensePlate) {
        this.licensePlate = licensePlate;
    }
}
