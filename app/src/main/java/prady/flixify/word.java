package prady.flixify;

public class word  {
    private int UrlNameID;
    private int UrlAddressID;
    private int ImageResourceID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public word(int urlNameID, int urlAddressID, int imageResourceID) {
        UrlNameID = urlNameID;
        UrlAddressID = urlAddressID;
        ImageResourceID = imageResourceID;
    }

    public word(int urlNameID, int urlAddressID) {
        UrlNameID = urlNameID;
        UrlAddressID = urlAddressID;
    }

    public int getUrlNameID() {
        return UrlNameID;
    }

    public int getUrlAddressID() {
        return UrlAddressID;
    }

    public int getImageResourceID() {
        return ImageResourceID;
    }

    public boolean hasImage() {
        return ImageResourceID != NO_IMAGE_PROVIDED;
    }
}
