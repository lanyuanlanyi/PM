package compare;

import JDBC.Person;

import java.util.Comparator;

/**
 * Created by 85168 on 2017/3/24.
 */
public class CompareId implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o2.getId()-o1.getId();
    }
}


