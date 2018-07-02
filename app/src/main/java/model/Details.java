package model;

/**
 * Created by HP on 17-10-2017.
 */

public class Details
{
    private int detailsIcon;
    private String detailsTitle;

    public Details(String detailsTitle, int detailsIcon) {
        this.detailsTitle = detailsTitle;
        this.detailsIcon = detailsIcon;
    }

    public int getDetailsIcon() {
        return detailsIcon;
    }

    public void setDetailsIcon(int detailsIcon) {
        this.detailsIcon = detailsIcon;
    }

    public String getDetailsTitle() {
        return detailsTitle;
    }

    public void setDetailsTitle(String detailsTitle) {
        this.detailsTitle = detailsTitle;
    }
}
