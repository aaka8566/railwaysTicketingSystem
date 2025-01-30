package org.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("date_of_travel")
    private String dateOfTravel;
    @JsonProperty("train_id")
    private String trainId;
    @JsonProperty("ticket_info")
    private String ticketInfo;
    @JsonProperty("seat_no")
    private String seatNo;

    public Ticket(){}

    public Ticket(String ticketId,String userId,String source,String destination,String dateOfTravel,String trainId,String ticketInfo,String seatNo ){
        this.ticketId=ticketId;
        this.userId=userId;
        this.source=source;
        this.destination=destination;
        this.dateOfTravel=dateOfTravel;
        this.trainId=trainId;
        this.ticketInfo=ticketInfo;
        this.seatNo=seatNo;
    }

    public Ticket(String ticketId,String userId,String source,String destination,String dateOfTravel,String trainId,String seatNo ){
        this.ticketId=ticketId;
        this.userId=userId;
        this.source=source;
        this.destination=destination;
        this.dateOfTravel=dateOfTravel;
        this.trainId=trainId;
        this.seatNo=seatNo;
    }

    public Ticket(String ticketId,String userId,String source,String destination,String dateOfTravel,String trainId){
        this.ticketId=ticketId;
        this.userId=userId;
        this.source=source;
        this.destination=destination;
        this.dateOfTravel=dateOfTravel;
        this.trainId=trainId;
        this.ticketInfo=getTicketInfo();
    }

    public String getTicketInfo(){
        return String.format("TicketID: %s belongs to User %s from %s to %s on %s",ticketId,userId,source,destination,dateOfTravel);
    }

    public String getTicketId(){
        return ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId=ticketId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getSource(){
        return source;
    }

    public void setSource(String source){
        this.source=source;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination=destination;
    }

    public String getDateOfTravel(){
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel){
        this.dateOfTravel=dateOfTravel;
    }

    public String getTrainId(){
        return trainId;
    }

    public void setTrainId(String trainId){
        this.trainId= this.trainId;
    }

    public String getSeatNo() { return seatNo; }

    public void setSeatNo(String seatNo) { this.seatNo=seatNo; }
}

