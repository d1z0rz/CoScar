public class urlBuilder {
    String origin;
    String destination;
    String travelingMode;
    private String googleKey = "AIzaSyCyqqrpoqeQfD-NmANqfjIMk4rCfAmg_00";

    public urlBuilder(String origin, String destination, String travelingMode) {
        this.origin = origin;
        this.destination = destination;
        this.travelingMode = travelingMode;
    }

    public String getRoadURL() {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
        QueryString qs = new QueryString("origins", origin);
        qs.add("destinations", destination);
        qs.add("mode", travelingMode);
        qs.add("key", googleKey);
        return url + qs;
    }

    public void setGoogleKey(String key) {
        this.googleKey = key;
    }
}