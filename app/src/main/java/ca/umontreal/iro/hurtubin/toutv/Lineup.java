package ca.umontreal.iro.hurtubin.toutv;

public class Lineup {
    public String key;
    public String title;
    public String shortDescription;
    public String image;
    public String url;
    public String longDescription;

    public Lineup(String key, String title, String shortDescription, String image, String url, String longDescription) {
        this.key = key;
        this.title = title;
        this.shortDescription = shortDescription;
        this.image = image;
        this.url = url;
        this.longDescription = longDescription;
    }
}
