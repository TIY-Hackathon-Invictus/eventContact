package tiy.invictus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SC on 9/30/2016.
 */
public class Database {

    @Autowired
    EventRepository events;

    @Autowired
    UserRepository users;

    @Autowired
    ContactRequestRepository contacts;

    @Autowired
    CheckedInRepository checkedInRepos;

//    public Database(EventRepository events, UserRepository users, ContactRequestRepository contacts, CheckedInRepository checkedInRepos) {
//        this.events = events;
//        this.users = users;
//        this.contacts = contacts;
//        this.checkedInRepos = checkedInRepos;
//    }


    public Database() {
    }

    //    public Response login(@RequestBody ReqLoginRequest loginRequest) throws Exception {
    public Response login(ReqLoginRequest loginRequest) {
        Response myUser = users.findFirstByEmail(loginRequest.getEmail());
        User userAsUser = (User) myUser;
        System.out.println("This is the email and password " + userAsUser.email + " " + userAsUser.password);

        if (userAsUser == null) {
            return new ResponseError("User does not exist.");
        }
        if (!loginRequest.getPassword().equals(userAsUser.getPassword())) {
            return new ResponseError("Incorrect Username/Password.");
        }
        return userAsUser;
    }

//    public Response register(@RequestBody User user) throws Exception {
    public Response register(User user){
        User compareUser = users.findFirstByEmail(user.getEmail());
        SecureUser secureUser = new SecureUser();
        if (compareUser != null) {
            return new ResponseError("User already exists.");
        }
        else  {
            users.save(user);
            secureUser = new SecureUser(compareUser.getUserId(), compareUser.getFirstName(), compareUser.getLastName(), compareUser.getEmail(), compareUser.isAdmin());
        }
        return secureUser;
    }



//    public ArrayList<Event> getAllEvents() {
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        Iterable<Event> allEvents = events.findAll();
        for (Event event : allEvents) {
            eventList.add(event);
        }
        return eventList;
    }



//    public Event eventInfo(@RequestBody ReqEventInfo eventInfoRequest) {
    public Event eventInfo(ReqEventInfo eventInfoRequest) {
        Event event = events.findOne(eventInfoRequest.getEventId());
        return event;
    }



    public ArrayList<Event> addEvent(ReqAddEvent addEventRequest) throws Exception {
        User newUser = users.findOne(addEventRequest.getUserId());
        Event newEvent = addEventRequest.getEvent();
        newEvent.setAdminUser(newUser);
        events.save(newEvent);
        return getAllEvents();
    }



//    @RequestMapping(path = "/contacts", method = RequestMethod.POST)
//    public ArrayList<ContactRequest> contacts(@RequestBody ReqContacts rc) {
//        ArrayList<ContactRequest> requestListThatAreTrue = new ArrayList<ContactRequest>();
//        Iterable<ContactRequest> allRequests = contacts.findAll();
//        User myUser = users.findOne(Integer.valueOf(rc.getContactID()));
//        for (ContactRequest cr : allRequests) {
//            if (cr.isFriend) {
//                requestListThatAreTrue.add(cr);
//            }
//        }
//        return requestListThatAreTrue;
//    }

    public SecureUser contactInfo(int userID) {
        User myUser = users.findOne(userID);
        SecureUser mySecureUser = new SecureUser(myUser.getUserId(), myUser.getFirstName(), myUser.getLastName(), myUser.getEmail(), myUser.isAdmin());
        return mySecureUser;
    }



    public ArrayList<ContactRequest> requests() {
        ArrayList<ContactRequest> contactRequestsList = new ArrayList<ContactRequest>();
        Iterable<ContactRequest> allContacts = contacts.findAll();
        for (ContactRequest contactRequest : allContacts) {
            contactRequestsList.add(contactRequest);
        }
        return contactRequestsList;
    }



}
