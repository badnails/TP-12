package Database;

import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class DB {
    private HashMap<String, player> players;
    private HashMap<String, club> clubs;
    private HashMap<String, country> countries;

    Scanner sc = new Scanner(System.in);
    
    public DB() {
        players = new HashMap<>();
        clubs = new HashMap<>();
        countries = new HashMap<>();
    }

    public void addPlayer(player ob)
    {
        players.putIfAbsent(ob.getName().toLowerCase(), ob);
    }

    public void addClub(club ob)
    {
        clubs.putIfAbsent(ob.getName().toLowerCase(), ob);
    }

    public void addCountry(country ob)
    {
        countries.putIfAbsent(ob.getName().toLowerCase(), ob);
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

        player playerObject = players.get(name.toLowerCase());
        if(playerObject==null)
        {
            System.out.println("--- "+name+" not present in database ---\n");
            return;
        }
        playerObject.display();
        System.out.println("\n(0) Back");
        
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void searchClubCountry(String clubName, String countryName)
    {
        System.out.println("\nPlayers from "+countryName+" at "+clubName+"\n");
        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- Club "+clubName+" not present in database ---\n");
            return;
        }
        boolean exists = false;
        int index = 1;
        
        for(player temp: clubObject.getPlayers().values())
        {
            if(temp.getCountry().toLowerCase().equals(countryName.toLowerCase()))
            {
                System.out.println("("+(index++)+") ");
                temp.display();
                exists = true;
            }
        }

        if(exists == false)
        {
            System.out.println("\n--- No Players found ---");
        }

        System.out.println("\n(0) Back");
        
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }

    }

    public void searchByPosition(String positionName)
    {
        System.out.println("\nPlayers with position "+positionName+"\n");
        boolean exists = false;
        int index = 1;
        
        for(player temp: players.values())
        {
            if(temp.getPosition().toLowerCase().equals(positionName.toLowerCase()))
            {
                System.out.println("("+(index++)+") ");
                temp.display();
                exists = true;
            }
        }

        if(exists == false)
        {
            System.out.println("\n--- No Players found ---");
        }

        System.out.println("\n(0) Back");
        
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void searchBySalary(int lsalary, int rsalary)
    {
        System.out.println("\nPlayers with salary between "+lsalary+" and "+rsalary+"\n");
        boolean exists = false;
        int index = 1;
        
        for(player temp: players.values())
        {
            if(temp.getSalary()>=lsalary && temp.getSalary()<=rsalary)
            {
                System.out.println("("+(index++)+") ");
                temp.display();
                exists = true;
            }
        }

        if(exists == false)
        {
            System.out.println("\n--- No Players found ---");
        }

        System.out.println("\n(0) Back");
        
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void countryPlayerCount()
    {
        System.out.println("\nPlayers by country: \n");
        int index = 1;

        for(country temp: countries.values())
        {
            System.out.println("("+(index++)+") "+temp.getName()+": "+temp.getPlayerCount());
        }

        System.out.println("\n(0) Back");
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void maxSalaryClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum salary of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            return;
        }
        for(player temp : clubObject.getPlayers().values())
        {
            if(clubObject.getMaxSalary()==temp.getSalary())
            {
                System.out.println("("+(index++)+") ");
                temp.display();
            }
        }

        System.out.println("\n(0) Back");
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void maxAgeClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum age of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            return;
        }
        for(player temp : clubObject.getPlayers().values())
        {
            if(clubObject.getMaxAge()==temp.getAge())
            {
                System.out.println("("+(index++)+") ");
                temp.display();
            }
        }

        System.out.println("\n(0) Back");
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void maxHeightClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum height of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            return;
        }
        for(player temp : clubObject.getPlayers().values())
        {
            if(clubObject.getMaxHeight()==temp.getHeight())
            {
                System.out.println("("+(index++)+") ");
                temp.display();
            }
        }

        System.out.println("\n(0) Back");
        
        int ret = sc.nextInt(); sc.nextLine();
        while(ret!=0){
            ret = sc.nextInt(); sc.nextLine();
        }
    }

    public void totalClubSalary(String clubName)
    {
        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            return;
        }
        System.out.println("\nTotal salary of "+clubObject.getName()+": "+clubObject.getTotalSalary()+"\n");
    }    

    public void writeToFile(String FILE_NAME) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for(player temp: players.values())
        {
            bw.write(temp.getName()+","+temp.getCountry()+","+temp.getAge()+","+temp.getHeight()+","+temp.getClub()+","+temp.getPosition()+","+temp.getJersey()+","+temp.getSalary());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
}
