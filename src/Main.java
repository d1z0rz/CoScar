import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        alakuProgramm();
    }

    private static void valiLiiklusvahend() {
        kuvaPiir();
        System.out.println("How do you want to get to your destination?\n(car/walk/bike)");
        Scanner sc = new Scanner(System.in);
        String liiklusvahendiVastus = sc.nextLine();
        if (liiklusvahendiVastus.equals("car")) {
            küsimustik("driving");
        } else if (liiklusvahendiVastus.equals("walk")) {
            küsimustik("walking");
        } else if (liiklusvahendiVastus.equals("bike")) {
            küsimustik("bicycling");
        } else {
            System.out.println("Your answer is not correct");
            valiLiiklusvahend();
        }
    }

    private static void alakuProgramm() {
        System.out.println("\n" +
                " ________  ________  ________  ________  ________  ________     \n" +
                "|\\   ____\\|\\   __  \\|\\   ____\\|\\   ____\\|\\   __  \\|\\   __  \\    \n" +
                "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
                " \\ \\  \\    \\ \\  \\\\\\  \\ \\_____  \\ \\  \\    \\ \\   __  \\ \\   _  _\\  \n" +
                "  \\ \\  \\____\\ \\  \\\\\\  \\|____|\\  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\| \n" +
                "   \\ \\_______\\ \\_______\\____\\_\\  \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\ \n" +
                "    \\|_______|\\|_______|\\_________\\|_______|\\|__|\\|__|\\|__|\\|__|\n" +
                "                       \\|_________|                             \n");
        puhastaEkraan();
        valiLiiklusvahend();
    }

    private static void küsimustik(String liiklusvahend) {
        kuvaPiir();
        System.out.println("Where would you like to go?");
        Scanner sc = new Scanner(System.in);
        String sihtkoht = sc.nextLine();
        System.out.println("Enter your origin address");
        String lähtekoht = sc.nextLine();
        HttpÜhendus teekonnaInfo = new HttpÜhendus(new UrlEhitaja(lähtekoht, sihtkoht, liiklusvahend).getTeeURL());
        if (teekonnaInfo.meetodiStaatus().equals("OK")) {
            HttpÜhendus ilmaInfo = new HttpÜhendus(new UrlIlm(sihtkoht).getIlmaURL());
            String distants = teekonnaInfo.distants();
            String kestus = teekonnaInfo.kestus();
            String temperatuur = ilmaInfo.sihtkohaIlm();
            kuvaTeekonnaInfo(distants, kestus, liiklusvahend, temperatuur);
            eelLähenemine();
        } else {
            System.out.println("Sorry, but " + liiklusvahend + " is not available at this route");
            teekondPoleSaadaval(teekonnaInfo);
        }

    }

    private static void teekondPoleSaadaval(HttpÜhendus teekonnaNimetus) {
        kuvaPiir();
        System.out.println("Would you like to check other transport methods for this route?\n(yes/no)");
        Scanner sc = new Scanner(System.in);
        String vastus = sc.nextLine();
        if (vastus.equals("yes")) {
            teisedLiiklusvahendid(teekonnaNimetus);
        } else if (vastus.equals("no")) {
            eelLähenemine();
        } else {
            System.out.println("Your answer is not correct");
            teekondPoleSaadaval(teekonnaNimetus);
        }
    }

    private static void teisedLiiklusvahendid(HttpÜhendus teekonnaNimetus) {
        kuvaPiir();
        Scanner sc = new Scanner(System.in);
        System.out.println("Which method you would like to choose?\n(car/walk)");
        String vastus = sc.nextLine();
        if (vastus.equals("car")) {
            String sihtkoht = teekonnaNimetus.sihtkohaAadress();
            String lähtekoht = teekonnaNimetus.lähtekohaAadress();
            HttpÜhendus teekonnaInfo = new HttpÜhendus(new UrlEhitaja(lähtekoht, sihtkoht, "driving").getTeeURL());
            HttpÜhendus ilmaInfo = new HttpÜhendus(new UrlIlm(sihtkoht).getIlmaURL());
            String distants = teekonnaInfo.distants();
            String kestus = teekonnaInfo.kestus();
            String temperatuur = ilmaInfo.sihtkohaIlm();
            kuvaTeekonnaInfo(distants, kestus, vastus, temperatuur);
            eelLähenemine();


        } else if (vastus.equals("walk")) {
            String sihtkoht = teekonnaNimetus.sihtkohaAadress();
            String lähtekoht = teekonnaNimetus.lähtekohaAadress();
            HttpÜhendus teekonnaInfo = new HttpÜhendus(new UrlEhitaja(lähtekoht, sihtkoht, "walking").getTeeURL());
            HttpÜhendus ilmaInfo = new HttpÜhendus(new UrlIlm(sihtkoht).getIlmaURL());
            String distants = teekonnaInfo.distants();
            String kestus = teekonnaInfo.kestus();
            String temperatuur = ilmaInfo.sihtkohaIlm();
            kuvaTeekonnaInfo(distants, kestus, vastus, temperatuur);
            eelLähenemine();

        } else {
            System.out.println("Your answer is not correct");
            teisedLiiklusvahendid(teekonnaNimetus);
        }

    }

    private static void kuvaTeekonnaInfo(String distants, String kestus, String liiklusvahend, String temperatuur) {
        kuvaPiir();
        System.out.println("Your route by " + liiklusvahend + " consist of\n" +
                "distance: " + distants + "\n" +
                "lasts for " + kestus + "\n" +
                "outside temperature at the destination will be " + temperatuur + " degrees");
    }

    private static void eelLähenemine() {
        kuvaPiir();
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to go to another destination?\n(yes/no)");
        String vastus = sc.nextLine();
        if (vastus.equals("yes")) {
            valiLiiklusvahend();
        } else if (vastus.equals("no")) {
            SõnumiGenereerija mg = new SõnumiGenereerija();
            mg.juhuslikSõnum();
        } else {
            System.out.println("Your's answer was not correct");
            eelLähenemine();
        }
    }

    private static void kuvaPiir() {
        System.out.println("=================================================");
    }

    private static void puhastaEkraan() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}