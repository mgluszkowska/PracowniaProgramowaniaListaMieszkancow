package classes;

import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;

public class PeopleList {

    HashBagMultimap<String, Person> citiesToPeople;

    public void PeopleList() {
        this.citiesToPeople = HashBagMultimap.newMultimap();
    }

    public  HashBagMultimap<String, Person> getCitiesToPeople() {
        return citiesToPeople;
    }
}
