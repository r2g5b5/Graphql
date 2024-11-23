package com.example.graphql.graphql.controller;

import com.example.graphql.graphql.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerWebSocketController {

    @MessageMapping("/player")
    @SendTo("/topic/players")
    public Player broadcastPlayerUpdate(Player player) {
        return player;
    }
}
