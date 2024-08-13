package src.main.java.characters;

import java.util.ArrayList;
import java.util.List;

public class PlayerPool {
    private List<Player> alive = new ArrayList<>();
    private List<Player> dead = new ArrayList<>();

    public void addPlayer(Player player) {
        alive.add(player);
    }

    public void removePlayer(Player player) {
        alive.remove(player);
        dead.add(player);
    }

    public List<Player> getAlive() {
        return alive;
    }

    public List<Player> getDead() {
        return dead;
    }
}
