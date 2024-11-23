package com.example.graphql.graphql.controller;

import com.example.graphql.graphql.model.Player;
import com.example.graphql.graphql.service.PLayerService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    private final PLayerService service;

    public PlayerController(PLayerService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Player> getPlayers() {
        return service.getPlayers();
    }

}
