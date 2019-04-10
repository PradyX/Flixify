package prady.flixify;

public class DBAdapter {

    public String image,name,year,url,rating,season,plot,genre,cast,yt;

    public DBAdapter() {

    }

//    public DBAdapter(String image, String name, String year, String url, String rating, String season) {
//        this.image = image;
//        this.name = name;
//        this.year = year;
//        this.url = url;
//        this.rating = rating;
//        this.season = season;
//    }

    public DBAdapter(String image, String name, String year, String url, String rating, String season, String plot, String genre, String cast, String yt) {
        this.image = image;
        this.name = name;
        this.year = year;
        this.url = url;
        this.rating = rating;
        this.season = season;
        this.plot = plot;
        this.genre = genre;
        this.cast = cast;
        this.yt = yt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String rUrl) {
        this.url = rUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }
}
