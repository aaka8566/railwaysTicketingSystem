package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TrainService {

    private User user;

    private Train train;

    private List<Train> trainList;

    private TicketService ticketService = new TicketService();

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAINS_PATH="app/src/main/java/org/example/localDB/trains.json";

    public TrainService() throws IOException {
        this.trainList= loadTrains();
    }

    public TrainService(Train train){
        this.train=train;
    }

    public TrainService(User user) throws IOException {
        this.user=user;
        this.trainList=loadTrains();
    }

    public TrainService(Train train,User user ) throws IOException {
        this.train=train;
        this.user=user;
        this.trainList=loadTrains();
    }

    public List<Train> loadTrains() throws IOException {
        File trains=new File(TRAINS_PATH);
        return objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public void saveTrainData() throws IOException {
        File trains=new File(TRAINS_PATH);
        System.out.println("Inside saveTrainData Func:   ");
        for(Train t: trainList){
            System.out.println(t.getTrainName()+" : "+t.getSeats());
        }
        objectMapper.writeValue(trains,trainList);
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrain(train,source,destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train,String source,String destination){
        List<String> stationOrder=train.getStations();

        int sourceIndex=stationOrder.indexOf(source.toLowerCase());
        int destinationIndex=stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex!=-1 && destinationIndex!=-1 && sourceIndex<destinationIndex;
    }

    public void instanceData(){
        System.out.println("User: "+this.user.getName()+"-> Train: "+this.train.getTrainName());
    }

    public Train getSelectedTrain(){
        return this.train;
    }

    public User getCurrentUser(){
        return this.user;
    }

    public boolean bookSeat(User user, Train train, String source, String destination, LocalDate dateOfTravel) throws IOException {
        for(int i=0;i<this.train.getSeats().size();i++){
            for(int j=0;j<this.train.getSeats().get(i).size();j++){
                if(this.train.getSeats().get(i).get(j)==0){
                    this.train.getSeats().get(i).set(j, 1);
                    for(Train t: trainList){
                        if(t.getTrainId().equals(this.train.getTrainId())){
                            t.setSeats(this.train.getSeats());
                            Ticket ticket=new Ticket(UUID.randomUUID().toString(),user.getUserId(),source,destination,dateOfTravel.toString(), train.getTrainId(), i+" "+j);
                            try{
                                ticketService.bookTicket(ticket);
                            }catch(IOException e){
                                System.out.println("Error booking tickets: "+e.getMessage());
                                return false;
                            }
                            break;
                        }
                    }
                    try {
                        saveTrainData(); // Save updated train data back to trains.json
                    } catch (IOException e) {
                        System.out.println("Error saving trains: " + e.getMessage());
                        return false;
                    }
                    System.out.println("Booked seat no: "+i+" "+j);
                    return true;
                }
            }
        }
        System.out.println("All seats are booked!");
        return false;
    }
}
