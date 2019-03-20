import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MessageGenerator {

    private Random randomGenerator;
    private List<String> messages;

   MessageGenerator() {
        messages = new ArrayList<>();
        randomGenerator = new Random();
    }

    void addMessage(String message){
        messages.add(message);
    }

    void anyMessage(){
        int index = randomGenerator.nextInt(messages.size());
        System.out.println(messages.get(index));
    }
}
