package no.hvl.dat108;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class PersonDAO {

	@PersistenceContext(name = "personPU")
    private EntityManager em;
	
	public PersonDAO() {
		
	}

	public void leggtilDeltaker(Person person) {
		em.persist(person);
		
	}

    public List<Person> hentDeltakere() {
    	TypedQuery<Person> q = em.createQuery("select p from Person p order by p.fornavn", Person.class);
		return q.getResultList();
	}
    
    public boolean finnes(String mobilnr) {
    	if(mobilnr == null) {
    		return false;
    	}
    	Person p = em.find(Person.class, mobilnr);
    	if (p == null) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }
    
    public String finnPassord(String mobilnr) {
    	if (finnes(mobilnr) == true) {
    	Person p = null;
    	p = em.find(Person.class, mobilnr);
    	return p.getPassord();
    	}
    	return null;
    }

}
