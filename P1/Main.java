import java.util.Scanner;
import Database.DB;
import UI.ui;

public class Main {
    public static void main(String[] args) throws Exception{
        DB data = new DB();
        ui io = new ui(data);
        io.program();
    }
}
