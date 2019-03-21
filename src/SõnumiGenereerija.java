import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SõnumiGenereerija {

    private Random juhuarvugeneraator;
    private List<String> sõnumid;

   SõnumiGenereerija() {
        sõnumid = Arrays.asList("Have a nice trip!", "Good luck!", "Discover our world!",
                "See you then","Best day ever!");
        juhuarvugeneraator = new Random();
    }

    void lisaSõnum(String sõnum){
        sõnumid.add(sõnum);
    }

    void juhuslikSõnum(){
        int index = juhuarvugeneraator.nextInt(sõnumid.size());
        System.out.println(sõnumid.get(index));
    }
}
