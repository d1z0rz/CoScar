import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //System.out.println("Welcome!");
        //urlBuilder info = new urlBuilder("tallinn,estonia", "tartu,estonia");
        //HttpConnection weatherData = new HttpConnection(info.getWeatgerURL());
        //System.out.println(weatherData.destinationWeather());
        programBegin();
    }
    public static void programBegin(){
        System.out.println("Welcome to CoScar program!");
        System.out.println("Where would you like to go today?");
        questionnaire();
    }
    public static void questionnaire(){
        Scanner sc = new Scanner(System.in);
        String destination  = sc.nextLine();
        System.out.println("Enter your origin address");
        String origin  = sc.nextLine();
        urlBuilder info = new urlBuilder(origin,destination);
        HttpConnection roadData = new HttpConnection(info.getRoadURL());
        HttpConnection weatherData = new HttpConnection(info.getWeatgerURL());
        System.out.println("Outside temperature where is "+weatherData.destinationWeather()+" degrees\n"+
                "Your's trip will last "+ roadData.duration()+"\n"
                +"and will drive "+roadData.distance());
        preApproach();
    }
    public static void preApproach(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to check another address? (yes/no)");
        String aswer = sc.nextLine();
        if (aswer.equals("yes")){
            System.out.println("Enter you destination address");
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