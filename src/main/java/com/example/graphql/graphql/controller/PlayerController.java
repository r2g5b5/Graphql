package com.example.graphql.graphql.controller;

import com.example.graphql.graphql.model.Player;
import com.example.graphql.graphql.model.Team;
import com.example.graphql.graphql.service.PLayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

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

    @QueryMapping
    public Optional<Player> getPlayer(@Argument Long id) {
        return service.getPlayerById(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name, @Argument Team team) {
        return service.add(name, team);
    }

    @MutationMapping
    public Player updatePlayer(@Argument Long id, @Argument String name, @Argument Team team) {
        return service.update(id, name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument Long id) {
        return service.delete(id);
    }


}
