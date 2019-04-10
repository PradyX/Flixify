package prady.flixify;

public class SliderAdapter {
    public String imageS,nameS,urlS;

    public SliderAdapter() {
    }

    public SliderAdapter(String imageS, String nameS, String urlS) {
        this.imageS = imageS;
        this.nameS = nameS;
        this.urlS = urlS;
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public String getUrlS() {
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }
}
