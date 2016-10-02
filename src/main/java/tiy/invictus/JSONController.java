package tiy.invictus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Brice on 9/29/16.
 */

@RestController
public class JSONController {

    @Autowired
    EventRepository events;

    @Autowired
    UserRepository users;

    @Autowired
    ContactRequestRepository contacts;

    @Autowired
    CheckedInRepository checkedInRepos;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody ReqLoginRequest loginRequest) throws Exception {
        Response myUser = users.findFirstByEmail(loginRequest.getEmail());
        User userAsUser = (User) myUser;

        if (userAsUser == null) {
            ResponseError myResponse = new ResponseError();
            myResponse.setMessage("User does not exist.");
            return myResponse;
//            return new ResponseError("User does not exist.");
        } else if (!loginRequest.getPassword().equals(userAsUser.getPassword())) {
            return new ResponseError("Incorrect Username/Password.");
        }

        return userAsUser;
    }



    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody User user) throws Exception {
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



    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        Iterable<Event> allEvents = events.findAll();
        for (Event event : allEvents) {
            eventList.add(event);
        }
        return eventList;
    }



    @RequestMapping(path = "/eventInfo", method = RequestMethod.POST)
    public Event eventInfo(@RequestBody ReqEventInfo eventInfoRequest) {
        Event event = events.findOne(eventInfoRequest.getEventId());
        return event;
    }



    @RequestMapping(path = "/addEvent", method = RequestMethod.POST)
    public ArrayList<Event> addEvent(@RequestBody ReqAddEvent addEventRequest) throws Exception {
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

    @RequestMapping(path = "/contactInfo", method = RequestMethod.POST)
    public SecureUser contactInfo(int userID) {
        User myUser = users.findOne(userID);
        SecureUser mySecureUser = new SecureUser(myUser.getUserId(), myUser.getFirstName(), myUser.getLastName(), myUser.getEmail(), myUser.isAdmin());
        return mySecureUser;
    }



    @RequestMapping(path = "/requests", method = RequestMethod.POST)
    public ArrayList<ContactRequest> requests() {
        ArrayList<ContactRequest> contactRequestsList = new ArrayList<ContactRequest>();
        Iterable<ContactRequest> allContacts = contacts.findAll();
        for (ContactRequest contactRequest : allContacts) {
            contactRequestsList.add(contactRequest);
        }
        return contactRequestsList;
    }
}
