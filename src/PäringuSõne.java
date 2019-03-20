import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PäringuSõne {

    private String päring = "";

    PäringuSõne(String nimi, String väärtus) {
        encode(nimi, väärtus);
    }

    void add(String nimi, String väärtus) {
        päring += "&";
        encode(nimi, väärtus);
    }

    private void encode(String nimi, String väärtus) {
        try {
            päring += URLEncoder.encode(nimi, "UTF-8");
            päring += "=";
            päring += URLEncoder.encode(väärtus, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    private String getPäring() {
        return päring;
    }

    public String toString() {
        return getPäring();
    }

}
