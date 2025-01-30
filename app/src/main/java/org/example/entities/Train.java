package org.example.entities;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.*;
public class Train {
    @JsonProperty("train_id")
    private String trainId;
    @JsonProperty("train_no")
    private String trainNo;
    @JsonProperty("train_name")
    private String trainName;
    @JsonProperty("seats")
    private List<List<Integer>> seats;
    @JsonProperty("station_times")
    private Map<String, String> stationTimes;
    @JsonProperty("stations")
    private List<String> stations;
    @JsonProperty("train_info")
    private String trainInfo;

    public Train(){}

    public Train(String trainId,String trainNo,List<List<Integer>> seats,Map<String,String> stationTimes,List<String> stations,String trainInfo,String trainName){
        this.trainId=trainId;
        this.trainNo=trainNo;
        this.seats=seats;
        this.stationTimes=stationTimes;
        this.stations=stations;
        this.trainInfo=trainInfo;
        this.trainName=trainName;
    }

    public String getTrainId(){
        return trainId;
    }

    public void setTrainId(String trainId){
        this.trainId=trainId;
    }

    public String getTrainNo(){
        return trainNo;
    }

    public void setTrainNo(String trainNo){
        this.trainNo=trainNo;
    }

    public List<List<Integer>> getSeats(){
        return seats;
    }

    public void setSeats(List<List<Integer>> seats){
        this.seats=seats;
    }

    public Map<String,String> getStationTimes(){
        return stationTimes;
    }

    public void setStationTimes(Map<String,String> stationTimes){
        this.stationTimes=stationTimes;
    }

    public List<String> getStations(){
        return stations;
    }

    public void setStations(List<String> stations){
        this.stations=stations;
    }

    public String getTrainName(){ return trainName;}

    public void setTrainName(String newName){ this.trainName =newName;}

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }
}
