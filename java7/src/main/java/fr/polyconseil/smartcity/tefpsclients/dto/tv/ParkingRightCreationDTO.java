package fr.polyconseil.smartcity.tefpsclients.dto.tv;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Map;


public class ParkingRightCreationDTO {

    private ParkingRightType type;

    private String source;

    @Nullable
    private String fineLegalId;

    private String zoneId;

    @Nullable
    private String parkId;

    private Plate licensePlate;

    private Date startDatetime;
    private Date endDatetime;
    private Date creationDatetime;

    @Nullable
    private Date cancelDatetime;

    private int price;

    private String tariffCode;

    @Nullable
    private Map<String, Object> pricingContext;

    public ParkingRightType getType() {
        return type;
    }

    public void setType(ParkingRightType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Nullable
    public String getFineLegalId() {
        return fineLegalId;
    }

    public void setFineLegalId(@Nullable String fineLegalId) {
        this.fineLegalId = fineLegalId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    @Nullable
    public String getParkId() {
        return parkId;
    }

    public void setParkId(@Nullable String parkId) {
        this.parkId = parkId;
    }

    public Plate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(Plate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    @Nullable
    public Date getCancelDatetime() {
        return cancelDatetime;
    }

    public void setCancelDatetime(@Nullable Date cancelDatetime) {
        this.cancelDatetime = cancelDatetime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Nullable
    public Map<String, Object> getPricingContext() {
        return pricingContext;
    }

    public void setPricingContext(@Nullable Map<String, Object> pricingContext) {
        this.pricingContext = pricingContext;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }
}
