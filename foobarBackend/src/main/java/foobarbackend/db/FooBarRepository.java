package foobarbackend.db;

import javax.persistence.*;

import java.util.*;

import foobarbackend.api.Foobar;

public class FooBarRepository {
	static EntityManager em = null;
	static EntityManagerFactory emf = null;
	
	public FooBarRepository() {
		// Check if repo has been created yet, if not, create it.
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("$objectdb/database/foobar.odb");
            //emf = Persistence.createEntityManagerFactory("objectdb:$objectdb/db/foobar.tmp;drop"); //Test repo (Clears all data on startup)
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
	}

	// Adds given foobar object to the database.
	// Returns added foobar object.
	//=================================================================================================================
    public static Foobar AddFoobar(Foobar foobar) {  
        em.getTransaction().begin();     
    	em.persist(foobar);
        em.getTransaction().commit();
        return foobar;
    }  
	//=================================================================================================================
    
    // Returns a list all foobar objects in the database.
	//=================================================================================================================
    public static List<Foobar> GetAllFoobar(){
        TypedQuery<Foobar> query = em.createQuery("SELECT f FROM Foobar f", Foobar.class);
    	return query.getResultList(); 
    }
	//=================================================================================================================
}