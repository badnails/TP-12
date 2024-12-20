package Database;
import java.util.HashMap;

public class country {
    String name;
    int playerNum;
    HashMap<String, player> players;

    public country(String name) {
        this.name = name;
        this.playerNum = 0;
        this.players = new HashMap<>();
    }

    public void addPlayer(player object)
    {
        players.put(object.getName().toLowerCase(), object);
        playerNum++;
    }

    public String getName()
    {
        return name;
    }

    public int getPlayerCount()
    {
        return playerNum;
    }

    public HashMap<String, player> getPlayers()
    {
        return players;
    }

}   