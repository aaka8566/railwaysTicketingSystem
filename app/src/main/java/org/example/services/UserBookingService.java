package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private List<User> userList=new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH="app/src/main/java/org/example/localDB/users.json";

    public UserBookingService() throws IOException {
        this.userList=loadUsers();
    }

    public List<User> loadUsers() throws IOException{
        File users=new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public UserBookingService(User user1) throws IOException {
        this.user=user1;
        this.userList=loadUsers();
    }

    public Boolean isUsernameExists(String userName) throws IOException {
        this.userList=loadUsers();
        System.out.println(userList);
        boolean existingUsername=userList.stream().anyMatch(user-> userName.equals(user.getName()));
        return existingUsername;
    }

    public Boolean loginUser(User user) throws IOException {
        Optional<User> foundUser=userList.stream().filter(user1 -> user1.getName().equals(user.getName())
                && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword())).findFirst();
       if(foundUser.isPresent()){
           this.user=foundUser.get();
           new TrainService(this.user);
           return true;
       }
       return false;
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch(IOException e){
            return Boolean.FALSE;
        }
    }

    public User getCurrentUser(){
        return this.user;
    }

    private void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList); //SERIALIZATION
    }

    public boolean fetchBooking(){
        System.out.println("Inside fetch booking func");
        if (this.user == null) {
            System.out.println("You need to login first!");
            return false;
        }else{
//            this.user.getName();
            System.out.println("We're in else block!!!");
            user.printTickets();
            return true;
        }
    }

    public Boolean cancelBooking(String ticketId){
        // CODE
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService=new TrainService();
            return trainService.searchTrains(source,destination);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
