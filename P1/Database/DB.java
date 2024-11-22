package Database;

import java.util.HashMap;

import Club.club;
import Country.country;
import Player.player;

public class DB {
    private HashMap<String, player> players;
    private HashMap<String, club> clubs;
    private HashMap<String, country> countries;
    private int playerNum;
    private int clubNum;
    private int countryNum;
    
    public DB() {
        players = new HashMap<>();
        clubs = new HashMap<>();
        countries = new HashMap<>();
        playerNum = 0;
        clubNum = 0;
        countryNum = 0;
    }

    public void addPlayer(player ob)
    {
        players.putIfAbsent(ob.getName(), ob);
        playerNum++;
    }

    public void addClub(club ob)
    {
        clubs.putIfAbsent(ob.getName(), ob);
        clubNum++;
    }

    public void addCountry(country ob)
    {
        countries.putIfAbsent(ob.getName(), ob);
        countryNum++;
    }

    public player findPlayer(String name)
    {
        return players.get(name);
    }

    public club findClub(String name)
    {
        return clubs.get(name);
    }

    public country findCountry(String name)
    {
        return countries.get(name);
    }
    
}
