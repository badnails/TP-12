package Database;

import java.util.HashMap;

public class club {
    String name;
    int maxSalary;
    int maxAge;
    double maxHeight;
    long totalSalary;
    HashMap<String, player> players;
	
    public club(String name) {
		this.name = name;
        this.maxAge = 0;
        this.maxSalary = 0;
        this.maxHeight = 0;
        this.totalSalary = 0;
        this.players = new HashMap<>();
	}

    public void addPlayer(player newPlayer)
    {
        players.put(newPlayer.getName().toLowerCase(), newPlayer);
        
        maxAge = newPlayer.getAge()>maxAge?newPlayer.getAge():maxAge;
        maxHeight = (newPlayer.getHeight())>maxHeight?newPlayer.getHeight():maxHeight;
        maxSalary = (newPlayer.getSalary())>maxSalary?newPlayer.getSalary():maxSalary;
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

	public HashMap<String, player> getPlayers() {
		return players;
	}

}
