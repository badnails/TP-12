package com.example.practice.Database;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Objects;
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


//    public void searchClubCountry(String clubName, String countryName)
//    {
//        System.out.println("\nPlayers from "+countryName+" at "+clubName+"\n");
//        club clubObject = clubs.get(clubName.toLowerCase());
//        if(clubObject==null)
//        {
//            if(clubName.toLowerCase().equals("any"))
//            {
//                country countryObject = countries.get(countryName.toLowerCase());
//                if(countryObject == null)
//                {
//                    System.out.println("Country "+countryName+" not present in database ---");
//                    System.out.println("\n(Any Key) Back");
//                    sc.nextLine();
//                    return;
//                }
//                int index = 1;
//
//                for(player temp: countryObject.getPlayers().values())
//                {
//                    System.out.println("("+(index++)+") ");
//                    temp.display();
//                }
//                System.out.println("\n(Any Key) Back");
//                sc.nextLine();
//                return;
//            }
//            System.out.println("--- Club "+clubName+" not present in database ---");
//            System.out.println("\n(Any Key) Back");
//            sc.nextLine();
//            return;
//        }
//
//        boolean exists = false;
//        int index = 1;
//
//        for(player temp: clubObject.getPlayers().values())
//        {
//            if(temp.getCountry().toLowerCase().equals(countryName.toLowerCase()))
//            {
//                System.out.println("("+(index++)+") ");
//                temp.display();
//                exists = true;
//            }
//        }
//
//        if(exists == false)
//        {
//            System.out.println("\n--- No Players found ---");
//        }
//
//        System.out.println("\n(Any Key) Back");
//        sc.nextLine();
//    }

    public ArrayList<player> searchByPosition(String positionName)
    {
        ArrayList<player> ret = new ArrayList<>();
        for(player temp: players.values())
        {
            if(temp.getPosition().equalsIgnoreCase(positionName))
            {
                ret.add(temp);
            }
        }
        return ret;
    }

    public ArrayList<player> searchBySalary(int lsalary, int rsalary)
    {
        ArrayList<player> ret = new ArrayList<>();
        for(player temp: players.values())
        {
            if(temp.getSalary() >= lsalary && temp.getSalary() <= rsalary)
            {
                ret.add(temp);
            }
        }
        return ret;
    }

    public void countryPlayerCount()
    {
        System.out.println("\nPlayers by country: \n");
        int index = 1;

        for(country temp: countries.values())
        {
            System.out.println("("+(index++)+") "+temp.getName()+": "+temp.getPlayerCount());
        }

        System.out.println("\n(Any Key) Back");
        sc.nextLine();
    }

    public void maxSalaryClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum salary of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            System.out.println("\n(Any Key) Back");
            sc.nextLine();
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

        System.out.println("\n(Any Key) Back");
        sc.nextLine();
    }

    public void maxAgeClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum age of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            System.out.println("\n(Any Key) Back");
            sc.nextLine();
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
        
        System.out.println("\n(Any Key) Back");
        sc.nextLine();
    }

    public void maxHeightClub(String clubName)
    {
        System.out.println("\nPlayer(s) with maximum height of "+clubName+"\n");
        int index = 1;

        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            System.out.println("\n(Any Key) Back");
            sc.nextLine();
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

        System.out.println("\n(Any Key) Back");
        sc.nextLine();
    }

    public void totalYearlyClubSalary(String clubName)
    {
        club clubObject = clubs.get(clubName.toLowerCase());
        if(clubObject==null)
        {
            System.out.println("--- "+clubName+" not present in database ---\n");
            System.out.println("\n(Any Key) Back");
            sc.nextLine();
            return;
        }
        System.out.println("\nTotal salary of "+clubObject.getName()+": "+clubObject.getTotalSalary()*52+"\n");
        System.out.println("\n(Any Key) Back");
        sc.nextLine();
    }    

    public void writeToFile(String FILE_NAME) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for(player temp: players.values())
        {
            bw.write(temp.getName()+","+temp.getCountry()+","+temp.getAge()+","+temp.getHeight()+","+temp.getClub()+","+temp.getPosition()+","+((temp.getJersey()==-1)?"":temp.getJersey())+","+temp.getSalary());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

    public void input(){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dreamboat\\Desktop\\JavaFxDemo\\Practice\\src\\main\\java\\com\\example\\practice\\Database\\players.txt"));
            while(true)
            {
                String line = br.readLine();
                if(line == null) break;
                String[] tokens = line.split(",", 8);

                int jerseyNumber;
                try
                {
                    jerseyNumber = Integer.parseInt(tokens[6]);
                }
                catch(NumberFormatException e)
                {
                    jerseyNumber = -1;
                }

                if(findPlayer(tokens[0].toLowerCase())==null)
                {
                    player newPlayer = new player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], jerseyNumber, Integer.parseInt(tokens[7]));
                    addPlayer(newPlayer);

                    country countryObject = findCountry(tokens[1].toLowerCase());

                    if(countryObject==null)
                    {
                        countryObject = new country(tokens[1]);
                        countryObject.addPlayer(newPlayer);
                        addCountry(countryObject);
                    }
                    else
                    {
                        countryObject.addPlayer(newPlayer);
                    }

                    club clubObject = findClub(tokens[4].toLowerCase());

                    if(clubObject==null)
                    {
                        clubObject = new club(tokens[4]);
                        clubObject.addPlayer(newPlayer);
                        addClub(clubObject);
                    }
                    else
                    {
                        clubObject.addPlayer(newPlayer);
                    }

                }
                else
                {
                    System.out.println("--- Ignoring duplicate data for "+tokens[0]+" ---");
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException at ");
            e.printStackTrace();
            return;
        }

    }

    public ArrayList<player> searchFunction(searchQuery query)
    {
        ArrayList<player> ret = new ArrayList<>(players.values());

        if(query.getName()!=null)
        {
            ret.clear();
            player playerObject = findPlayer(query.getName());
            if(playerObject!=null)
            {
                ret.add(playerObject);
            }
        }
        if(query.getCountry()!=null)
        {
            if(ret.size()==1)
            {
                if(!ret.getFirst().getCountry().equals(query.getCountry())) ret.clear();
            }
            else
            {
                ret.clear();
                country countryObject = findCountry(query.getCountry());
                if(countryObject!=null)
                {
                    ret = new ArrayList<>(countryObject.getPlayers().values());
                }
            }
        }
        if(query.getAge()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : ret)
            {
                if(tempPlayer.getAge()>query.getAge())
                {
                    temp.add(tempPlayer);
                }
            }
            ret = temp;
        }
        if(query.getJersey()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : ret)
            {
                if(tempPlayer.getJersey()==query.getJersey())
                {
                    temp.add(tempPlayer);
                }
            }
            ret = temp;
        }
        if(query.getSalary()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : ret)
            {
                if(tempPlayer.getSalary()==query.getSalary())
                {
                    temp.add(tempPlayer);
                }
            }
            ret = temp;
        }
        if(query.getPosition()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for (player tempPlayer : ret)
            {
                if(tempPlayer.getPosition().equals(query.getPosition()))
                {
                    temp.add(tempPlayer);
                }
            }
            ret = temp;
        }

        return ret;
    }

}