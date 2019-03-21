package srcEnglish;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        programBegin();
    }

    public static void chooseTransport() {
        displayBorder();
        System.out.println("How do you want to get to your destination?\n(car/walk/bike)");
        Scanner sc = new Scanner(System.in);
        String transportAnswer = sc.nextLine();
        if (transportAnswer.equals("car")) {
            Questionnaire("driving");
        } else if (transportAnswer.equals("walk")) {
            Questionnaire("walking");
        } else if (transportAnswer.equals("bike")) {
            Questionnaire("bicycling");
        } else {
            System.out.println("Your answer is not correct");
            chooseTransport();
        }
    }

    public static void programBegin() {
        System.out.println("\n" +
                " ________  ________  ________  ________  ________  ________     \n" +
                "|\\   ____\\|\\   __  \\|\\   ____\\|\\   ____\\|\\   __  \\|\\   __  \\    \n" +
                "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\   \n" +
                " \\ \\  \\    \\ \\  \\\\\\  \\ \\_____  \\ \\  \\    \\ \\   __  \\ \\   _  _\\  \n" +
                "  \\ \\  \\____\\ \\  \\\\\\  \\|____|\\  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\| \n" +
                "   \\ \\_______\\ \\_______\\____\\_\\  \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\ \n" +
                "    \\|_______|\\|_______|\\_________\\|_______|\\|__|\\|__|\\|__|\\|__|\n" +
                "                       \\|_________|                             \n");
        clearScreen();
        chooseTransport();
    }

    public static void Questionnaire(String transportMethod) {
        displayBorder();
        System.out.println("Where would you like to go?");
        Scanner sc = new Scanner(System.in);
        String destination = sc.nextLine();
        System.out.println("Enter your origin address");
        String origin = sc.nextLine();
        HttpConnection routeInfo = new HttpConnection(new urlBuilder(origin, destination, transportMethod).getRoadURL());
        if (routeInfo.methodStatus().equals("OK")) {
            HttpConnection weatherInfo = new HttpConnection(new urlWeather(destination).getWeatgerURL());
            String distance = routeInfo.distance();
            String duration = routeInfo.duration();
            String temperature = weatherInfo.destinationWeather();
            displayRouteData(distance, duration, transportMethod, temperature);
            preApproach();
        } else {
            System.out.println("Sorry, but " + transportMethod + " is not avalible at this route");
            routeIsNotAvalible(routeInfo);
        }

    }

    public static void routeIsNotAvalible(HttpConnection routeName) {
        displayBorder();
        System.out.println("Would you like to chect other trasport methods for this route?\n(yes/no)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if (answer.equals("yes")) {
            otherTranportMethods(routeName);
        } else if (answer.equals("no")) {
            preApproach();
        } else {
            System.out.println("Your answer is not correct");
            routeIsNotAvalible(routeName);
        }
    }

    public static void otherTranportMethods(HttpConnection routeName) {
        displayBorder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Which methond you would like to choose?\n(car/walk)");
        String answer = sc.nextLine();
        if (answer.equals("car")) {
            String destination = routeName.destinationAddress();
            String origin = routeName.originAddress();
            HttpConnection routeInfo = new HttpConnection(new urlBuilder(origin, destination, "driving").getRoadURL());
            HttpConnection weatherInfo = new HttpConnection(new urlWeather(destination).getWeatgerURL());
            String distance = routeInfo.distance();
            String duration = routeInfo.duration();
            String temperature = weatherInfo.destinationWeather();
            displayRouteData(distance, duration, answer, temperature);
            preApproach();


        } else if (answer.equals("walk")) {
            String destination = routeName.destinationAddress();
            String origin = routeName.originAddress();
            HttpConnection routeInfo = new HttpConnection(new urlBuilder(origin, destination, "walking").getRoadURL());
            HttpConnection weatherInfo = new HttpConnection(new urlWeather(destination).getWeatgerURL());
            String distance = routeInfo.distance();
            String duration = routeInfo.duration();
            String temperature = weatherInfo.destinationWeather();
            displayRouteData(distance, duration, answer, temperature);
            preApproach();

        } else {
            System.out.println("Your answer is not correct");
            otherTranportMethods(routeName);
        }

    }

    public static void displayRouteData(String distance, String duration, String transportMethod, String temperature) {
        displayBorder();
        System.out.println("Your route by " + transportMethod + " consist of\n" +
                "distance: " + distance + "\n" +
                "lasts for " + duration + "\n" +
                "outside temperature at the destination will be " + temperature + " degrees");
    }

    public static void preApproach() {
        displayBorder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to go to another destination?\n(yes/no)");
        String answer = sc.nextLine();
        if (answer.equals("yes")) {
            chooseTransport();
        } else if (answer.equals("no")) {
            MessageGenerator mg = new MessageGenerator();
            mg.anyMessage();
        } else {
            System.out.println("Your's aswer was not correct");
            preApproach();
        }
    }

    public static void displayBorder() {
        System.out.println("=================================================");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}