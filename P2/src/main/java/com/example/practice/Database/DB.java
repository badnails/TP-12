package com.example.practice.Database;

import com.example.practice.Requests.TransferTicket;
import com.example.practice.Requests.searchQuery;

import java.io.*;
import java.util.*;

public class DB {
    private Map<String, player> players;
    private Map<String, club> clubs;
    private Map<String, country> countries;
    private ArrayList<player> transferList;

    Scanner sc = new Scanner(System.in);
    
    public DB() {
        players = new LinkedHashMap<>();
        clubs = new LinkedHashMap<>();
        countries = new LinkedHashMap<>();
        transferList = new ArrayList<>();
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

    public void writeToFile(String FILE_NAME) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
        for(player temp: players.values())
        {
            bw.write(temp.getName()+","+temp.getCountry()+","+temp.getAge()+","+temp.getHeight()+","+temp.getClub()+","+temp.getPosition()+","+((temp.getJersey()==-1)?"":temp.getJersey())+","+temp.getSalary()+","+temp.isTransferListed());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

    public void input(){
        try
        {
            BufferedReader br;
            br = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\practice\\Database\\clubs.txt"));
            while(true)
            {
                String line = br.readLine();
                if(line==null) break;
                String[] tokens = line.split(",");
                club newClub = new club(tokens[0], tokens[1], tokens[2], Long.parseLong(tokens[3]));
                clubs.put(newClub.getName().toLowerCase(), newClub);
            }

            br = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\practice\\Database\\players.txt"));
            while(true)
            {
                String line = br.readLine();
                if(line == null) break;
                String[] tokens = line.split(",", 9);

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
                    player newPlayer = new player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], jerseyNumber, Integer.parseInt(tokens[7]), tokens[8].equals("true"));
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
                        clubObject = new club(tokens[4], "1234", null, 0);
                        clubObject.addPlayer(newPlayer);
                        addClub(clubObject);
                    }
                    else
                    {
                        clubObject.addPlayer(newPlayer);
                    }

                    if(newPlayer.isTransferListed())
                    {
                        transferList.add(newPlayer);
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

    synchronized public searchQuery searchFunction(searchQuery query, String clubName)
    {
        ArrayList<player> pool = new ArrayList<>();
        ArrayList<player> found = new ArrayList<>();

        if(query.getQueryType()==1)
        {
            for(player temp: players.values())
            {
                if(temp.getClub().equalsIgnoreCase(clubName))
                {
                    pool.add(temp);
                }
            }
            found = new ArrayList<>(pool);
        }
        if(query.getQueryType()==2)
        {
            for(player temp: transferList)
            {
                if(!temp.getClub().equalsIgnoreCase(clubName))
                {
                    pool.add(temp);
                }
            }
            found = new ArrayList<>(pool);
        }

        if(query.getName()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : pool)
            {
                if(tempPlayer.name.equalsIgnoreCase(query.getName()))
                {
                    temp.add(tempPlayer);
                    break;
                }
            }
            found = temp;
        }
        if(query.getCountry()!=null)
        {
            ArrayList<player> temp;
            country countryObject = findCountry(query.getCountry());
            if(countryObject!=null)
            {
                temp = new ArrayList<player>(countryObject.players.values());
            }
            else
            {
                temp = new ArrayList<>();
            }
            temp.retainAll(found);
            found = temp;
        }
        if(query.getAge()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : pool)
            {
                switch(query.getAge_op())
                {
                    case ">":
                        if(tempPlayer.age>query.getAge())
                        {
                            temp.add(tempPlayer);
                        }
                        break;
                    case "<":
                        if(tempPlayer.age<query.getAge())
                        {
                            temp.add(tempPlayer);
                        }
                        break;
                    case "==":
                        if(tempPlayer.age==query.getAge())
                        {
                            temp.add(tempPlayer);
                        }
                }
            }
            temp.retainAll(found);
            found = temp;
        }
        if(query.getJersey()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : pool)
            {
                if(tempPlayer.getJersey()==query.getJersey())
                {
                    temp.add(tempPlayer);
                }
            }
            temp.retainAll(found);
            found = temp;
        }
        if(query.getLowSalary()!=null || query.getHighSalary()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : pool)
            {
                if(query.getLowSalary()!=null && query.getHighSalary()!=null && tempPlayer.getSalary()>=query.getLowSalary() && tempPlayer.getSalary()<=query.getHighSalary())
                {
                    temp.add(tempPlayer);
                }
                else if(query.getHighSalary()!=null && tempPlayer.getSalary()<=query.getHighSalary())
                {
                    temp.add(tempPlayer);
                }
                else if(query.getLowSalary()!=null && tempPlayer.getSalary()>=query.getLowSalary())
                {
                    temp.add(tempPlayer);
                }
            }
            temp.retainAll(found);
            found = temp;
        }
        if(query.getPosition()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for (player tempPlayer : pool)
            {
                if(tempPlayer.getPosition().equalsIgnoreCase(query.getPosition()))
                {
                    temp.add(tempPlayer);
                }
            }
            temp.retainAll(found);
            found = temp;
        }
        if(query.getHeight()!=null)
        {
            ArrayList<player> temp = new ArrayList<>();
            for(player tempPlayer : pool)
            {
                switch(query.getHeight_op())
                {
                    case ">":
                        if(tempPlayer.height>query.getHeight())
                        {
                            temp.add(tempPlayer);
                        }
                        break;
                    case "<":
                        if(tempPlayer.height<query.getHeight())
                        {
                            temp.add(tempPlayer);
                        }
                        break;
                    case "==":
                        if(tempPlayer.height==query.getHeight())
                        {
                            temp.add(tempPlayer);
                        }
                }
            }
            temp.retainAll(found);
            found = temp;
        }

        if(found.isEmpty())
        {
            query.setFound(false);
            query.setResults(new ArrayList<>());
        }
        else
        {
            query.setFound(true);

            query.setResults(found);
        }

        return query;
    }

    synchronized public TransferTicket transferRequest(TransferTicket ticket, String requestFrom)
    {
        if(ticket.getMode()==1)
        {
            if(findPlayer(ticket.getPlayerObject().name).isTransferListed())
            {
                ticket.setSuccess(false);
                return ticket;
            }
            else
            {
                player playerObject = findPlayer(ticket.getPlayerObject().name);
                playerObject.setTransferListed(true);
                transferList.add(playerObject);

                System.out.println(findClub(playerObject.getClub()).players.get(playerObject.name.toLowerCase()).isTransferListed());

                ticket.setSuccess(true);
                return ticket;
            }
        }
        else if(ticket.getMode()==2)
        {
            if(findPlayer(ticket.getPlayerObject().name).isTransferListed())
            {
                club seller = findClub(ticket.getPlayerObject().club);
                club buyer = findClub(requestFrom);
                player playerObject = findPlayer(ticket.getPlayerObject().name);

                seller.deletePlayer(playerObject.name.toLowerCase());
                buyer.addPlayer(playerObject);

                if (transferList.remove(playerObject)) {
                    System.out.println("\n\n/////Off List/////");
                } else {
                    System.out.println("\n\n\\\\Not off//////\n\n");
                }
                for (player player : transferList) {
                    System.out.println(player.name.toLowerCase());
                    System.out.println("\n\n\n");
                }


                playerObject.setClub(buyer.getName());
                playerObject.setTransferListed(false);

                ticket.setSuccess(true);
                return ticket;
            }
            else
            {
                ticket.setSuccess(false);
                return ticket;
            }
        }
        else {
            return ticket;
        }
//        findPlayer(tempPlayer.name).setTransferListed(true);
//        transferList.add(tempPlayer);
    }


}