package fr.polyconseil.smartcity.tefpsclients.dto.pricing;

import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FnmsParkingRightPriceResultV1DTO {

    private int consumedParkingTime;

    private int remainingParkingTime;

    private List<ParkingRightDTO> currentParkingRights;

    public int getConsumedParkingTime() {
        return consumedParkingTime;
    }

    public void setConsumedParkingTime(int consumedParkingTime) {
        this.consumedParkingTime = consumedParkingTime;
    }

    public int getRemainingParkingTime() {
        return remainingParkingTime;
    }

    public void setRemainingParkingTime(int remainingParkingTime) {
        this.remainingParkingTime = remainingParkingTime;
    }

    public List<ParkingRightDTO> getCurrentParkingRights() {
        return currentParkingRights;
    }

    public void setCurrentParkingRights(List<ParkingRightDTO> currentParkingRights) {
        this.currentParkingRights = currentParkingRights;
    }
}
