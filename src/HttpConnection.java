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
        } catch (Exception e) {
            System.out.println(e);
        }
        return response.toString();
    }

    public String distance() {
        JSONObject response = new JSONObject(getJSON());
        JSONObject rows = (JSONObject) response.getJSONArray("rows").get(0);
        JSONObject elements = (JSONObject) rows.getJSONArray("elements").get(0);
        JSONObject distance = elements.getJSONObject("distance");
        return (String) distance.get("text");

    }

    public String duration() {
        JSONObject response = new JSONObject(getJSON());
        JSONObject rows = (JSONObject) response.getJSONArray("rows").get(0);
        JSONObject elements = (JSONObject) rows.getJSONArray("elements").get(0);
        JSONObject duration = elements.getJSONObject("duration");
        return (String) duration.get("text");
    }

    public String methodStatus() {
        JSONObject response = new JSONObject(getJSON());
        JSONObject rows = (JSONObject) response.getJSONArray("rows").get(0);
        JSONObject elements = (JSONObject) rows.getJSONArray("elements").get(0);
        return (String) elements.get("status");
    }

    public String destinationAddress() {
        JSONObject response = new JSONObject(getJSON());
        JSONArray address = (JSONArray) response.get("destination_addresses");
        return (String) address.get(0);
    }

    public String originAddress() {
        JSONObject response = new JSONObject(getJSON());
        JSONArray address = (JSONArray) response.get("origin_addresses");
        return (String) address.get(0);
    }

    public String routeStatus() {
        JSONObject response = new JSONObject(getJSON());
        return (String) response.get("status");
    }

    public String destinationWeather() {
        JSONObject response = new JSONObject(getJSON().toString());
        JSONObject location = response.getJSONObject("current");
        return (String) location.get("temp_c").toString();
    }
}
