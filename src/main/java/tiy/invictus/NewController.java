package tiy.invictus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SC on 9/30/2016.
 */
@Controller
public class NewController {

    @Autowired
    EventRepository events;
    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.email = "brice@blanch.com";
            user.password = "hi";
            user.setAdmin(true);
            user.setFirstName("brice");
            user.setLastName("blanch");
            users.save(user);
        }

        int counter = 0;
        while (counter < 10) {
            User myUser = users.findFirstByEmail("brice@blanch.com");
            Event myEvent = new Event("title" + counter, "address" + counter, "description" + counter, myUser, "date" + counter, "time" + counter);
            events.save(myEvent);
            counter++;
        }

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        System.out.println("hanging out in home");

        return "home";
    }
}
