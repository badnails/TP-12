package Database;

import java.util.HashMap;
import java.util.Scanner;

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

    Scanner sc = new Scanner(System.in);
    
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
        players.putIfAbsent(ob.getName().toLowerCase(), ob);
        playerNum++;
    }

    public void addClub(club ob)
    {
        clubs.putIfAbsent(ob.getName().toLowerCase(), ob);
        clubNum++;
    }

    public void addCountry(country ob)
    {
        countries.putIfAbsent(ob.getName().toLowerCase(), ob);
        countryNum++;
    }

    public player findPlayer(String name)
    {
        return players.get(name.toLowerCase());
    }

    public club findClub(String name)
    {
        return clubs.get(name.toLowerCase());
    }

    public country findCountry(String name)
    {
        return countries.get(name.toLowerCase());
    }

    public void playerSearch(String name)
    {
        System.out.println("\nInformation for "+name+":");

        players.get(name.toLowerCase()).display();
        System.out.println("\n(0) Back");
        
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }
    
}
