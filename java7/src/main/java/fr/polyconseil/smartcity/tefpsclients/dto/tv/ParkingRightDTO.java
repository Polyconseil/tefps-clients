package fr.polyconseil.smartcity.tefpsclients.dto.tv;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingRightDTO extends ParkingRightCreationDTO {

    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
