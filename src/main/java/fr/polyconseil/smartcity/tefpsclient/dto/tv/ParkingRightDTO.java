package fr.polyconseil.smartcity.tefpsclient.dto.tv;

public class ParkingRightDTO extends ParkingRightCreationDTO {

    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
