import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");
        questionnaire();
    }
    public static void questionnaire(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your origin address: ");
        String origin  = sc.nextLine();
        System.out.println("Enter your destination address: ");
        String destination  = sc.nextLine();
        urlBuilder url = new urlBuilder(origin,destination);
        HttpConnection data = new HttpConnection(url.getURL());
        System.out.println("Your's trip will last "+data.duration()+"\n"
        +"and will drive "+data.distance());
        preApproach();
    }
    public static void preApproach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to check another address? (yes/no)");
        String aswer = sc.nextLine();
        if (aswer.equals("yes")){
            questionnaire();
        } else if (aswer.equals("no")) {
            //todo from messages
            System.out.println("Have a nice day! Good bye!");
        } else {
            System.out.println("Your's aswer was not correct");
            preApproach();
        }
    }
}

//https://maps.googleapis.com/maps/api/directions/json?origin=Tallinn,Estonia&destination=Tartu,Estonia&key=AIzaSyCyqqrpoqeQfD-NmANqfjIMk4rCfAmg_00