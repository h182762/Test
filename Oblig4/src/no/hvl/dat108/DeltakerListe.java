package no.hvl.dat108;

import java.util.ArrayList;
import java.util.List;

public class DeltakerListe {
	List<Person> liste = new ArrayList<Person>();
	
	public DeltakerListe() {
		
	}
	
	public void leggTil(Person person) {
		liste.add(person);
	}
	
	public void fjern(Person person) {
		liste.remove(person);
	}
	
	public List<Person> getItems() {
        return liste;
    }

	@Override
	public String toString() {
		return "DeltakerListe [liste=" + liste + "]";
	}
	
	

}
