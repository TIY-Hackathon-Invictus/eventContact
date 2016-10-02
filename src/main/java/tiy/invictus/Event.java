package tiy.invictus;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Brice on 9/29/16.
 */

@Entity
@Table(name = "events")
public class Event {

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
    User adminUser;

    @Column (nullable = false)
    String date;

    @Column (nullable = false)
    String time;

    public Event(String title, String address, String description, User adminUserID, String date, String time) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.adminUser = adminUserID;
        this.date = date;
        this.time = time;
    }

    public Event() {
    }

    public int getEventId() {
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

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
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
