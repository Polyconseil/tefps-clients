package fr.polyconseil.smartcity.tefpsclients.dto.subscriber;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LightSubscriptionPublicDTO {
    private String subscriptionId;
    private String plate;
    private String subscriptionPlanId;
    private List<String> validityAreas;
    private Date startOfValidityDatetime;
    private Date endOfValidityDatetime;

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

    public Date getStartOfValidityDatetime() {
        return startOfValidityDatetime;
    }

    public void setStartOfValidityDatetime(Date startOfValidityDatetime) {
        this.startOfValidityDatetime = startOfValidityDatetime;
    }

    public Date getEndOfValidityDatetime() {
        return endOfValidityDatetime;
    }

    public void setEndOfValidityDatetime(Date endOfValidityDatetime) {
        this.endOfValidityDatetime = endOfValidityDatetime;
    }
}
