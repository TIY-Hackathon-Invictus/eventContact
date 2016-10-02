package tiy.invictus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        users.delete(tester);
    }


}
