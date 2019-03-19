public class urlBuilder {
    String origin;
    String destination;
    private String googleKey = "AIzaSyCyqqrpoqeQfD-NmANqfjIMk4rCfAmg_00";
    private String apixuKey = "2c69350d0eb5431c930130942191803";


    public urlBuilder(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getRoadURL() {
        String url = "https://maps.googleapis.com/maps/api/directions/json?";
        QueryString qs = new QueryString("origin", origin);
        qs.add("destination", destination);
        qs.add("key", googleKey);
        return url + qs;
    }
    public String getWeatgerURL() {
        String url = "http://api.apixu.com/v1/current.json?";
        QueryString qs = new QueryString("key",apixuKey);
        qs.add("q",destination);
        return url + qs;

        //key=2c69350d0eb5431c930130942191803&q=Voru%20100,%20Tartu
    }

    public void setGoogleKey(String key) {
        this.googleKey = key;
    }
}

