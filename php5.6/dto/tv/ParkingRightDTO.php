<?php

namespace TefpsClientBundle\dto\tv;

use TefpsClientBundle\dto\tv\ParkingRightCreationDTO;

use JMS\Serializer\Annotation\Type;
use JMS\Serializer\Annotation\SerializedName;

class ParkingRightDTO extends ParkingRightCreationDTO
{
    /**
     * @Type("string")
     * @SerializedName("ticketId")
     */
    private $ticketId;

    public function getTicketId() {
        return $this->ticketId;
    }

    public function setTicketId($ticketId) {
        $this->ticketId = $ticketId;
    }
}
