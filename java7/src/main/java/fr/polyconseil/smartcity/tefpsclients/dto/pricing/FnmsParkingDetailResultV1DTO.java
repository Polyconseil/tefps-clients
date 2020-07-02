package fr.polyconseil.smartcity.tefpsclients.dto.pricing;

import fr.polyconseil.smartcity.tefpsclients.dto.tv.ParkingRightDTO;
import fr.polyconseil.smartcity.tefpsclients.dto.subscriber.LightSubscriptionPublicDTO;

import java.util.List;

public class FnmsParkingDetailResultV1DTO {
    private List<ParkingRightDTO> currentParkingRights;
    private List<LightSubscriptionPublicDTO> currentSubscriptions;

    public List<ParkingRightDTO> getCurrentParkingRights() {
        return currentParkingRights;
    }

    public void setCurrentParkingRights(List<ParkingRightDTO> currentParkingRights) {
        this.currentParkingRights = currentParkingRights;
    }

    public List<LightSubscriptionPublicDTO> getCurrentSubscriptions() {
        return currentSubscriptions;
    }

    public void setCurrentSubscriptions(List<LightSubscriptionPublicDTO> currentSubscriptions) {
        this.currentSubscriptions = currentSubscriptions;
    }
}
