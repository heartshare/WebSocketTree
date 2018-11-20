package com.bonaparte.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Room {
    private Integer roomId;
    private List<String> users;
    private List<Map<String, Object>> quests;
    private String targetName;

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public List<Map<String, Object>> getQuests() {
        return quests;
    }

    public void setQuests(List<Map<String, Object>> quests) {
        this.quests = quests;
    }

    public Room(Integer roomId){
        this.roomId = roomId;
        users = new ArrayList<>();
    }

    public void addUser(String userName){
        users.add(userName);
    }

    public void cleanUser(){
        users = new ArrayList<>();
    }

    public String getPartner(String userName){
        for(String temp : this.users){
            if(!temp.equals(userName)){
                return temp;
            }
        }
        return  null;
    }

    public boolean isFull(){
        if(users != null && users.size()>1){
            return true;
        }
        return false;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
