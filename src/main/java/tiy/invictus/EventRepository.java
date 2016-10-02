package tiy.invictus;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 9/29/16.
 */


public interface EventRepository extends CrudRepository<Event, Integer>{
//    Event findFirstByUser(User adminUser);
//    List<Event> findFirstByUser(User adminUser);
}
