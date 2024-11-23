package com.example.graphql.graphql.service;

import com.example.graphql.graphql.config.PlayerWebSocketService;
import com.example.graphql.graphql.model.Player;
import com.example.graphql.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerService {

    private final List<Player> players = new ArrayList<>();
    private final AtomicLong id = new AtomicLong(0);
    private final PlayerWebSocketService webSocketService;

    public PlayerService(PlayerWebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Optional<Player> getPlayerById(long id) {
        return players.stream().filter(player -> player.id() == id).findFirst();
    }

    public Player add(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        webSocketService.broadcastPlayerUpdate(player, "create");
        return player;
    }

    public Player delete(long id) {
        Player player = getPlayerById(id).orElseThrow(() -> new IllegalArgumentException("Player not found"));
        players.remove(player);
        webSocketService.broadcastPlayerUpdate(player, "delete");
        return player;
    }

    public Player update(Long id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> opPlayer = getPlayerById(id);
        if (opPlayer.isPresent()) {
            Player player = opPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);
            webSocketService.broadcastPlayerUpdate(updatedPlayer, "update");
        } else {
            throw new IllegalArgumentException("Player not found");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "Perspolis", Team.IR));
        players.add(new Player(id.incrementAndGet(), "London", Team.UK));
        players.add(new Player(id.incrementAndGet(), "LosAn", Team.USA));
        players.add(new Player(id.incrementAndGet(), "Showrtz", Team.GE));
    }
}
