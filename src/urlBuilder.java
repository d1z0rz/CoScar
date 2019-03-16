public class urlBuilder {
    String origin;
    String destination;
    String key = "AIzaSyCyqqrpoqeQfD-NmANqfjIMk4rCfAmg_00";
    private String url = "https://maps.googleapis.com/maps/api/directions/json?";

    public urlBuilder(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getURL() {
        QueryString qs = new QueryString("origin", origin);
        qs.add("destination", destination);
        qs.add("key", key);
        return url + qs;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

