package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import Club.club;
import Country.country;
import Database.DB;
import Player.player;

public class ui {
    //private static final String OUTPUT_FILE_NAME = "out.txt";   
    // BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
    private static final String INPUT_FILE_NAME = "sc/players.txt";

    int state;
    DB data;
    Scanner sc = new Scanner(System.in);

    public ui(DB data)
    {
        state = -2025;
        this.data = data;
    }

    public void program()throws Exception
    {
        input();
        while(state!=4)
        {
            System.out.println("Main menu:");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");
            System.out.print("\nCommand: ");
            state = sc.nextInt(); sc.nextLine();
            System.out.println();

            switch(state)
            {
                case 1:
                    searchPlayersMenu();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("--- Invalid option ---\n");
                    break;
            }
        }
    }

    void searchPlayersMenu()
    {
        while(state!=6)
        {
            System.out.println("Player Searching Options:");
            System.out.println("(1) By Player Name");
            System.out.println("(2) By Club and Country");
            System.out.println("(3) By Position");
            System.out.println("(4) By Salary Range");
            System.out.println("(5) Country-wise player count");
            System.out.println("(6) Back to Main Menu");

            System.out.print("\nCommand: ");
            state = sc.nextInt(); sc.nextLine();
            System.out.println();

            switch(state)
            {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    data.playerSearch(name);
                    break;

            }
        }
    }

    void input() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while(true)
        {
            String line = br.readLine();
            if(line == null) break;
            String[] tokens = line.split(",", 8);

            if(data.findPlayer(tokens[0].toLowerCase())==null)
            {
                player newPlayer = new player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                data.addPlayer(newPlayer);

                country countryObject = data.findCountry(tokens[1].toLowerCase());

                if(countryObject==null)
                {
                    countryObject = new country(tokens[1]);
                    countryObject.addPlayer(newPlayer);
                    data.addCountry(countryObject);
                }
                else
                {
                    countryObject.addPlayer(newPlayer);
                }

                club clubObject = data.findClub(tokens[4].toLowerCase());

                if(clubObject==null)
                {
                    clubObject = new club(tokens[4]);
                    clubObject.addPlayer(newPlayer);
                    data.addClub(clubObject);
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
}
