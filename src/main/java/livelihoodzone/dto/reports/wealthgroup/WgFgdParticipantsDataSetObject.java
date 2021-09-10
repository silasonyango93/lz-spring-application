package livelihoodzone.dto.reports.wealthgroup;

import java.util.List;

public class WgFgdParticipantsDataSetObject {
    private List<String> participantDetails;

    public WgFgdParticipantsDataSetObject() {
    }

    public WgFgdParticipantsDataSetObject(List<String> participantDetails) {
        this.participantDetails = participantDetails;
    }

    public List<String> getParticipantDetails() {
        return participantDetails;
    }

    public void setParticipantDetails(List<String> participantDetails) {
        this.participantDetails = participantDetails;
    }
}
