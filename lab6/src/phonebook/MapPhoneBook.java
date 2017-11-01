package phonebook;

import java.io.Serializable;
import java.util.*;

public class MapPhoneBook implements PhoneBook, Serializable {
	private Map<String, Set<String>> book;
	
	public MapPhoneBook() {
		book = new TreeMap<String, Set<String>>();
	}

	@Override
	public boolean put(String name, String number) {
		if(!book.containsKey(name)){
			Set<String> numberSet = new TreeSet<String>();
			numberSet.add(number);
			book.put(name, numberSet);
			return true;
		}
		if(book.containsKey(name) && !book.get(name).contains(number)){
			book.get(name).add(number);	
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(String name) {
		if(book.containsKey(name)){
			book.remove(name);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeNumber(String name, String number) {
		if(book.containsKey(name)){
			return book.get(name).remove(number);
		}
		return false;
	}

	@Override
	public Set<String> findNumbers(String name) {
		Set<String> returnSet = new TreeSet<String>();
		Set<String> nameSet = book.get(name);
		if(book.containsKey(name)){
			returnSet.addAll(nameSet);
			return returnSet;
		}
		return returnSet;
		 
	}

	@Override
	public Set<String> findNames(String number) {
		Set<String> returnSet = new TreeSet<String>();
		Set<String> names = names();
		Iterator itr = names.iterator();
		while(itr.hasNext()){
			String testName = (String) itr.next();
			if(book.get(testName).contains(number)){
				returnSet.add(testName);
			}
		}
		return returnSet;
	}

	@Override
	public Set<String> names() {
		Set<String> names = new TreeSet<String>(book.keySet());
		return names;
	}
	
	@Override
	public int size() {
		return book.size();
	}

	@Override
	public Set<String> findPersons(String letters) {
		Set<String> returnSet = new TreeSet<String>();
		Set<String> names = names();
		Iterator itr = names.iterator();
		while(itr.hasNext()){
			String testName = (String) itr.next();
			if(testName.contains(letters)){
				returnSet.add(testName);
			}
		}
		return returnSet;
	}
}