public class UrlEhitaja {
    private String lähtekoht;
    private String sihtkoht;
    private String liiklusvahend;
    private String googleAPIVõti = "AIzaSyCyqqrpoqeQfD-NmANqfjIMk4rCfAmg_00";

    UrlEhitaja(String lähtekoht, String sihtkoht, String liiklusvahend) {
        this.lähtekoht = lähtekoht;
        this.sihtkoht = sihtkoht;
        this.liiklusvahend = liiklusvahend;
    }

    String getTeeURL() {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
        PäringuSõne qs = new PäringuSõne("origins", lähtekoht);
        qs.add("destinations", sihtkoht);
        qs.add("mode", liiklusvahend);
        qs.add("key", googleAPIVõti);
        return url + qs;
    }

    public void setGoogleAPIVõti(String võti) {
        this.googleAPIVõti = võti;
    }
}