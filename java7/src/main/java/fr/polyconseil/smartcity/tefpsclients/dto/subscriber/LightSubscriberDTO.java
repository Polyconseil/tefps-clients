package fr.polyconseil.smartcity.tefpsclients.dto.subscriber;

import java.util.Date;
import java.util.List;

public class LightSubscriberDTO {
    private String subscriberId;

    private String plate;

    private String subscriptionPlanId;

    private Date startOfValidityDatetime;

    private Date endOfValidityDatetime;

    private List<String> validityAreas;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
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

    public List<String> getValidityAreas() {
        return validityAreas;
    }

    public void setValidityAreas(List<String> validityAreas) {
        this.validityAreas = validityAreas;
    }
}