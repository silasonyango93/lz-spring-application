package livelihoodzone.dto;

public class GenericResponse {
    private boolean success;
    private String eventMessage;

    public GenericResponse() {
    }

    public GenericResponse(boolean success, String eventMessage) {
        this.success = success;
        this.eventMessage = eventMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}
