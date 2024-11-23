package com.example.graphql.graphql.service;

import com.example.graphql.graphql.model.Player;
import com.example.graphql.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PLayerService {

    private List<Player> players = new ArrayList<Player>();
    AtomicLong id = new AtomicLong(0);

    public List<Player> getPlayers() {
        return players;
    }

    public Optional<Player> getPlayerById(long id) {
        return players.stream()
                .filter(player -> player.id() == id).findFirst();
    }

    public Player add(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(long id) {
        Player player = players.stream().filter(player1 -> player1.id() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("cant find player"));
        players.remove(player);
        return player;
    }

    public Player update(Long id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> opPlayer = getPlayerById(id);
        if (opPlayer.isPresent()) {
            Player player = opPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);
        } else {
            throw new IllegalArgumentException("cant find player");
        }
        return updatedPlayer;
    }


    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(),"Perspolis",Team.IR));
        players.add(new Player(id.incrementAndGet(),"London",Team.UK));
        players.add(new Player(id.incrementAndGet(),"LosAn",Team.USA));
        players.add(new Player(id.incrementAndGet(),"Showrtz",Team.GE));

    }

}
