package com.bonaparte.bean;

import com.bonaparte.service.CompeteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class RoomFactory {
    @Autowired
    private CompeteService competeService;

    private static int roomId = 1;

    private static Room room;

    public synchronized Room getRoom(){
        if(room != null && room.isFull()){
            room = null;
        }
        if(room == null){
            roomId ++;
            room = new Room(roomId);
            List<Map<String, Object>> quests = competeService.competeInfoMore();
            room.setQuests(quests);
        }

        return room;
    }

    public Room join(String userName){
        Room room = getRoom();
        room.addUser(userName);
        int i = 0;
        while(!room.isFull()){
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i>600){
                room.cleanUser();
                return null;
            }
        }
        return room;
    }
}
