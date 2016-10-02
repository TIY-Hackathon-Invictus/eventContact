package tiy.invictus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventContactApplicationTests {
	@Autowired
	EventRepository events;

	@Autowired
	UserRepository users;

	@Autowired
	ContactRequestRepository contacts;

	@Autowired
	CheckedInRepository checkedInRepos;

	@Test
	public void login() throws Exception {
	    ReqLoginRequest myRequest = new ReqLoginRequest("brice@blanch.com", "hi");

        User user = new User();
        user.email = "brice@blanch.com";
        user.password = "hi";
        user.setAdmin(true);
        user.setFirstName("brice");
        user.setLastName("blanch");
//        users.save(user);

		User tester = new User();
        User compareUsers = users.findFirstByEmail(myRequest.getEmail());


		System.out.println("tester: " + compareUsers.firstName);
		System.out.println("myUser: " + compareUsers.lastName);
	}

	@Test
	public void register() throws Exception {
		User user = new User("princess", "sampson", "princess@sampson.com", "hello");
		User tester = new User();
		users.save(user);
        tester = users.findFirstByEmail("princess@sampson.com");
        System.out.println(tester.getFirstName());
        System.out.println(tester.getLastName());
        System.out.println(tester.getEmail());
        System.out.println(tester.getPassword());
        System.out.println(tester.getUserId());
        System.out.println(tester.isAdmin());
        users.delete(tester);
    }

    @Test
    public void events() throws Exception {
        User myUser = users.findFirstByEmail("brice@blanch.com");
        Event myEvent = new Event("title", "address", "description", myUser, "date", "time");
        Event myEvent1 = new Event("title1", "address", "description", myUser, "date", "time");
        Event myEvent2 = new Event("title2", "address", "description", myUser, "date", "time");

        events.save(myEvent);
//        myEvent = events.findOne();
        myEvent1 = events.save(myEvent1);
        myEvent2 = events.save(myEvent2);

//        events.findOne(myEvent.getEventId());
//        events.findOne(myEvent1.getEventId());
//        events.findOne(myEvent2.getEventId());

        Iterable<Event> allEvents = events.findAll();
        ArrayList<Event> eventArrayList = new ArrayList<>();

        for (Event currentEvent : allEvents) {
            eventArrayList.add(currentEvent);
        }
        for (Event thisEvent : eventArrayList) {
            System.out.println(thisEvent.eventId);
            System.out.println(thisEvent.adminUser);
            System.out.println(thisEvent.title);
            System.out.println(thisEvent.address);
            System.out.println(thisEvent.date);
            System.out.println(thisEvent.time);
            System.out.println();
            events.delete(thisEvent.getEventId());
        }
    }


}
