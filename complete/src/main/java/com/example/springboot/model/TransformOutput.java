package com.example.springboot;
public class TransformOutput {
    private String eventId;
    private String device_os;

    public TransformOutput() {
    }


    public TransformOutput(String eventId, String device_os) {
        this.eventId = eventId;
        this.device_os = device_os;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}