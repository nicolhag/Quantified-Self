import java.util.Scanner;
import java.util.HashMap;
import java.io.File;

class Kalkulator{
    public static void main(String[] args) throws Exception {
        DeltakerBehandler d = new DeltakerBehandler();
        d.lesInnFraFil("sample_fysisk.csv");
    }
}

class DeltakerBehandler{
    HashMap<String, Deltaker> deltakere = new HashMap<String, Deltaker>();

    private void lesInnFraFil(String filnavn){
        Scanner leser = new Scanner(new File(filnavn));
        int nummer = 0;

        while (leser.hasNextLine()){
            String innlest = leser.nextLine();
            String[] parsed = innlest.split(",");

            System.out.println("*********************");
            System.out.println("*********************");

            String timestamp = parsed[0];
            String navn = parsed[1];
            String antallSkritt = parsed[2];
            String antalltimerSovn = parsed[3];

            System.out.println("Navn: " + navn);
            System.out.println("Timestamp: " + timestamp);
            System.out.println("Antall skritt: " + antallSkritt);
            System.out.println("Antall timer søvn: " + antalltimerSovn);

            System.out.println("*********************");
            System.out.println("*********************");

        }
    }
}

class Deltaker{
    String navn;

}
