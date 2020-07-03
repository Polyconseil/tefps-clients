package fr.polyconseil.smartcity.tefpsclients.dto.subscriber;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LightSubscriptionPublicDTO {
    private String subscriptionId;
    private String plate;
    private String subscriptionPlanId;
    private List<String> validityAreas;
    private String startOfValidityDatetime;
    private String endOfValidityDatetime;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(String subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public List<String> getValidityAreas() {
        return validityAreas;
    }

    public void setValidityAreas(List<String> validityAreas) {
        this.validityAreas = validityAreas;
    }

    public String getStartOfValidityDatetime() {
        return startOfValidityDatetime;
    }

    public void setStartOfValidityDatetime(String startOfValidityDatetime) {
        this.startOfValidityDatetime = startOfValidityDatetime;
    }

    public String getEndOfValidityDatetime() {
        return endOfValidityDatetime;
    }

    public void setEndOfValidityDatetime(String endOfValidityDatetime) {
        this.endOfValidityDatetime = endOfValidityDatetime;
    }
}
