import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import Club.club;
import Country.country;
import Database.DB;
import Player.player;

public class Main {

    private static final String INPUT_FILE_NAME = "sc/players.txt";
    private static final String OUTPUT_FILE_NAME = "out.txt";
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        DB data = new DB();

        while(true)
        {
            String line = br.readLine();
            if(line == null) break;
            String[] tokens = line.split(",", 8);

            if(data.findPlayer(tokens[0])==null)
            {
                player newPlayer = new player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                data.addPlayer(newPlayer);

                country countryObject = data.findCountry(tokens[1]);

                if(countryObject==null)
                {
                    countryObject = new country(tokens[1]);
                    countryObject.addPlayer(newPlayer);
                }
                else
                {
                    countryObject.addPlayer(newPlayer);
                }

                data.addCountry(countryObject);

                club clubObject = data.findClub(tokens[4]);

                if(clubObject==null)
                {
                    clubObject = new club(tokens[4]);
                    clubObject.addPlayer(newPlayer);
                }
                else
                {
                    clubObject.addPlayer(newPlayer);
                }

                clubObject.addCountry(countryObject);
                data.addClub(clubObject);
            }
            else
            {
                System.out.println("--- Ignoring duplicate data for "+tokens[0]+" ---");
            }
        }



        br.close();
        bw.close();



    }
}
