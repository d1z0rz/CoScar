import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SõnumiGenereerija {

    private Random juhuarvugeneraator;
    private List<String> sõnumid;

   SõnumiGenereerija() {
        sõnumid = Arrays.asList("message 1", "message 2", "message 3");
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
