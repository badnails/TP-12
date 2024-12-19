package com.example.practice.Database;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class club implements Serializable {
    String name;
    String password;
    String logoSrc;
    int maxSalary;
    int maxAge;
    double maxHeight;
    long totalSalary;
    long budget;
    Map<String, player> players;
	
    public club(String name, String password, String logoSrc, long budget) {
		this.name = name;
        this.password = password;
        this.logoSrc = logoSrc;
        this.budget = budget;
        this.maxAge = 0;
        this.maxSalary = 0;
        this.maxHeight = 0;
        this.totalSalary = 0;
        this.players = new LinkedHashMap<>();
	}

    public void addPlayer(player newPlayer)
    {
        players.put(newPlayer.getName().toLowerCase(), newPlayer);
        
        maxAge = Math.max(newPlayer.getAge(), maxAge);
        maxHeight = Math.max((newPlayer.getHeight()), maxHeight);
        maxSalary = Math.max((newPlayer.getSalary()), maxSalary);
        totalSalary+=newPlayer.getSalary();
    }

	public String getName() {
		return name;
	}

	public int getMaxSalary() {
		return maxSalary;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public double getMaxHeight() {
		return maxHeight;
	}

	public long getTotalSalary() {
		return totalSalary;
	}

	public Map<String, player> getPlayers() {
		return players;
	}

    public String getPassword() {
        return password;
    }
    public String getLogoSrc() {
        return logoSrc;
    }
    public void deletePlayer(String name)
    {
        player playerObj = players.get(name.toLowerCase());
        players.remove(name.toLowerCase());
        totalSalary -= playerObj.getSalary();
        if(playerObj.getAge() > maxAge || playerObj.getSalary() > maxSalary || playerObj.getHeight() > maxHeight)
        {
            maxAge = 0;
            maxSalary = 0;
            maxHeight = 0;
            for(player player : players.values())
            {
                if(player.getAge() > maxAge)
                {
                    maxAge = player.getAge();
                }
                if(player.getSalary() > maxSalary)
                {
                    maxSalary = player.getSalary();
                }
                if(player.getHeight() > maxHeight)
                {
                    maxHeight = player.getHeight();
                }
            }
        }
    }
}
