package com.example.graphql.graphql.config;

import com.example.graphql.graphql.model.Player;

import com.example.graphql.graphql.model.PlayerUpdateMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public PlayerWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastPlayerUpdate(Player player, String action) {
        PlayerUpdateMessage message = new PlayerUpdateMessage(action, player);
        messagingTemplate.convertAndSend("/topic/players", message);
    }

}

