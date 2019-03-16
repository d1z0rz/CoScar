import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    String url;

    public HttpConnection(String url) {
        this.url = url;
    }

    public String getJSON() {
        //String url;
        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return response.toString();
    }

    public String distance() {
        JSONObject myresponse = new JSONObject(getJSON().toString());
        JSONArray routes = new JSONArray(myresponse.getJSONArray("routes").toString());
        JSONObject routesClean = (JSONObject) routes.get(0);
        JSONArray legs = routesClean.getJSONArray("legs");
        JSONObject legsClean = (JSONObject) legs.get(0);
        JSONObject distance = legsClean.getJSONObject("distance");
        return (String) distance.get("text");
    }
    public String duration() {
        JSONObject myresponse = new JSONObject(getJSON().toString());
        JSONArray routes = new JSONArray(myresponse.getJSONArray("routes").toString());
        JSONObject routesClean = (JSONObject) routes.get(0);
        JSONArray legs = routesClean.getJSONArray("legs");
        JSONObject legsClean = (JSONObject) legs.get(0);
        JSONObject distance = legsClean.getJSONObject("duration");
        return (String) distance.get("text");
    }
}
