class UrlIlm {
    private String sihtkoht;
    private String apixuVõti = "2c69350d0eb5431c930130942191803";

    UrlIlm(String sihtkoht) {
        this.sihtkoht = sihtkoht;
    }

    String getIlmaURL() {
        String url = "http://api.apixu.com/v1/current.json?";
        PäringuSõne qs = new PäringuSõne("key", apixuVõti);
        qs.add("q", sihtkoht);
        return url + qs;
    }

}
