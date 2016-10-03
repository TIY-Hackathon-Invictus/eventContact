package tiy.invictus;

import javax.persistence.*;

/**
 * Created by Brice on 9/29/16.
 */

public class ReqEvent {

    @Id
    @GeneratedValue
    int eventId;

    @Column (nullable = false)
    String title;

    @Column (nullable = false)
    String address;

    @Column (nullable = false)
    String description;

    @ManyToOne
    int adminUser;

    @Column (nullable = false)
    String date;

    @Column (nullable = false)
    String time;

    public ReqEvent(String title, String address, String description, int adminUserID, String date, String time) {

        this.title = title;
        this.address = address;
        this.description = description;
        this.adminUser = adminUserID;
        this.date = date;
        this.time = time;
    }

    public ReqEvent() {
    }

    public int getEventid() {
        return eventId;
    }

    public void setEventid(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(int adminUser) {
        this.adminUser = adminUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
