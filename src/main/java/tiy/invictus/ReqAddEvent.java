package tiy.invictus;

/**
 * Created by SC on 10/1/2016.
 */
public class ReqAddEvent {

    public int userId;
    public Event event;

    public ReqAddEvent() {
    }

    public ReqAddEvent(int userId, Event event) {
        this.userId = userId;
        this.event = event;
    }

    public ReqAddEvent(Event event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
