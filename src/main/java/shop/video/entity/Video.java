package shop.video.entity;

import shop.user.entity.User;

/**
 * Created by zapp on 24/02/15.
 */
public class Video {

    private String title;
    private User currentUser;
    private double paidAmount;
    private double price;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User loggedInUser) {
        this.currentUser = loggedInUser;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
