import java.util.ArrayList;
import java.util.Random;

public class MessageGenerator {
    private Random randomGenerator;
    private ArrayList<String> messages;

    public void MessageGenerator() {
        messages = new ArrayList<>();
        randomGenerator = new Random();
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public String anyMessage(){
        int index = randomGenerator.nextInt(messages.size());
        return messages.get(index);
    }
}
