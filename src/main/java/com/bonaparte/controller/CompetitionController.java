package com.bonaparte.controller;

import com.alibaba.fastjson.JSON;
import com.bonaparte.bean.Room;
import com.bonaparte.bean.RoomFactory;
import com.bonaparte.bean.SocketMessage;
import com.bonaparte.bean.SocketSessionRegistry;
import com.karakal.commons.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompetitionController {

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private RoomFactory roomFactory;

    @Autowired
    @GetMapping({"/"})
    public String index()
    {
        return "index";
    }

    @MessageMapping({"/changePoint"})
    public void changePoint(SocketMessage message)
            throws Exception
    {
        System.out.println("mgs:" + JSON.toJSONString(message));
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try
        {
            String targetName = message.getTargetName();
            String sessionId = this.webAgentSessionRegistry.getSessionId(targetName);
            if (sessionId != null)
            {
                System.out.println(sessionId);
                map.put("data", message);
                this.template.convertAndSendToUser(sessionId, "/queue/point", map, createHeaders(sessionId));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private MessageHeaders createHeaders(String sessionId)
    {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

    @MessageMapping({"/startGame"})
    @SendToUser(value={"/topic/newGame"}, broadcast=false)
    public Object startGame(SocketMessage message)
            throws Exception
    {
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try
        {
            System.out.println("mgs:" + JSON.toJSONString(message));
            String userName = message.getUserName();
            Room room = this.roomFactory.join(userName);
            if (room != null)
            {
                room.setTargetName(room.getPartner(userName));
                map.put("data", room);
            }
            else
            {
                map.put("status", "0");
                map.put("msg", "未找到对手");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            map = ControllerUtil.defaultErrResult();
        }
        return map;
    }

}
