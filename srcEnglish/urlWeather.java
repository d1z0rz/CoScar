public class urlWeather {
    String destination;
    private String apixuKey = "2c69350d0eb5431c930130942191803";

    public urlWeather(String destination) {
        this.destination = destination;
    }

    public String getWeatgerURL() {
        String url = "http://api.apixu.com/v1/current.json?";
        QueryString qs = new QueryString("key", apixuKey);
        qs.add("q", destination);
        return url + qs;
    }

}
