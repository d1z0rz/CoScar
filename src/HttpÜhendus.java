import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpÜhendus {
    private String url;

    public HttpÜhendus(String url) {
        this.url = url;
    }

    private String getJSON() {
        StringBuilder vastus = new StringBuilder();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String sisendiRida;
            while ((sisendiRida = in.readLine()) != null) {
                vastus.append(sisendiRida);
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return vastus.toString();
    }

    String distants() {
        JSONObject vastus = new JSONObject(getJSON());
        JSONObject read = (JSONObject) vastus.getJSONArray("rows").get(0);
        JSONObject elemendid = (JSONObject) read.getJSONArray("elements").get(0);
        JSONObject distants = elemendid.getJSONObject("distance");
        return (String) distants.get("text");

    }

    String kestus() {
        JSONObject vastus = new JSONObject(getJSON());
        JSONObject read = (JSONObject) vastus.getJSONArray("rows").get(0);
        JSONObject elemendid = (JSONObject) read.getJSONArray("elements").get(0);
        JSONObject kestus = elemendid.getJSONObject("duration");
        return (String) kestus.get("text");
    }

    String meetodiStaatus() {
        JSONObject vastus = new JSONObject(getJSON());
        JSONObject read = (JSONObject) vastus.getJSONArray("rows").get(0);
        JSONObject elemendid = (JSONObject) read.getJSONArray("elements").get(0);
        return (String) elemendid.get("status");
    }

    String sihtkohaAadress() {
        JSONObject vastus = new JSONObject(getJSON());
        JSONArray aadress = (JSONArray) vastus.get("destination_addresses");
        return (String) aadress.get(0);
    }

    String lähtekohaAadress() {
        JSONObject vastus = new JSONObject(getJSON());
        JSONArray aadress = (JSONArray) vastus.get("origin_addresses");
        return (String) aadress.get(0);
    }

    String sihtkohaIlm() {
        JSONObject vastus = new JSONObject(getJSON().toString());
        JSONObject asukoht = vastus.getJSONObject("current");
        return (String) asukoht.get("temp_c").toString();
    }
}
