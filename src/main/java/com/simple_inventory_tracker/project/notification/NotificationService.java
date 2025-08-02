package com.simple_inventory_tracker.project.notification;

public class NotificationService{
    private String sendTo;

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public void sendNotification(String message){
        System.out.println("Send notification to ......");
        System.out.println(this.getSendTo());
        System.out.println("Message: Reorder product : "+ message);
        
    }
}