package com.project.emissionsapi.model;



public class MessageResponse {

    private boolean success;
    private String message;
    private String detail;

    public MessageResponse(boolean success, String message, String detail) {
        this.success = success;
        this.message = message;
        this.detail = detail;
    }

    public MessageResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}