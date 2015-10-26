import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
class Calculator{
    public static void main(String[] args) throws Exception {
        Handler handler = new Handler();

        if (args.length == 0){
            Scanner term = new Scanner(System.in);
            System.out.println("Hva heter filen med fysiske data?");
            String fysisk = term.nextLine();

            System.out.println("Hva heter filen med psykiske data?");
            String psykisk = term.nextLine();
            handler.readFromFile(fysisk, psykisk);

        } else if (args[0].trim().equals("default")){
            handler.readFromFile("fysisk.csv", "psykisk.csv");
        } else {
            handler.readFromFile(args[0],args[1]);
        }

        handler.ordrelokke();

    }
}
