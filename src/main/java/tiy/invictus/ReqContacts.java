package tiy.invictus;

/**
 * Created by SC on 10/1/2016.
 */
public class ReqContacts {

    public int contactId;

    public ReqContacts() {
    }

    public ReqContacts(int contactID) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
