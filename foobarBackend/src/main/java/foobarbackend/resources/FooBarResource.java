package foobarbackend.resources;

import com.codahale.metrics.annotation.Timed;

import foobarbackend.api.Foobar;
import foobarbackend.core.Template;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("api/foobar")
@Produces(MediaType.APPLICATION_JSON)

public class FooBarResource {
    private final Template template;
    
    public FooBarResource(Template template) {
        this.template = template;
    }

    // A Get function that returns a list of all foobar objects saved in the repo
	//=================================================================================================================
    @GET
    @Timed
    public List<Foobar> GetAllFoobar() {
        List<Foobar> foobar = foobarbackend.db.FooBarRepository.GetAllFoobar();
    	return foobar;
    }
	//=================================================================================================================

    // A Post function that recalculates the recieved foobar object, compares the two and saves it to local storage if they match else,
    // the foobar result field is updated with an error and returned.
	//=================================================================================================================
    @POST
    @Timed
    public Foobar createFoobar(Foobar foobar) {
    	try {
    		if (FoobarIsCorrect(foobar)) {
    			return foobarbackend.db.FooBarRepository.AddFoobar(foobar);
    		} else { 
    			foobar.result = "Results did not match, entry not saved.";
    			return foobar;
    		}
    	} catch(Exception e) {
    		foobar.result = "Invalid values, please input valid numbers.";
    		return foobar;
    	}
    }
	//=================================================================================================================

    // Returns a bool stating whether the foobar object that has been recalculated matches the given foobar object
	//=================================================================================================================
    private Boolean FoobarIsCorrect(Foobar foobar) {
    	Foobar foobarCheck = CalcFooBar(foobar);
    	
    	if (foobarCheck.fooCount == foobar.fooCount && foobarCheck.barCount == foobar.barCount && foobarCheck.foobarCount == foobar.foobarCount)
    		return true;
    	else
    		return false;
    }
	//=================================================================================================================
    
    // Recalculates the given foobar object (Counting amount of 'Foo's, 'Bar's and 'FooBar's)
	//=================================================================================================================
    private Foobar CalcFooBar(Foobar foobar) {
    	Foobar foobarCheck = new Foobar(foobar.foo, foobar.bar);
    	
    	for (int i = 1; i <= 42; i++) {
    		if (i % foobarCheck.foo == 0) {
    			foobarCheck.result += "Foo,";
    			foobarCheck.fooCount++;
    		}

    		if (i % foobarCheck.bar == 0) {
    			foobarCheck.result += "Bar,";
    			foobarCheck.barCount++;
    		}

    		if (i % (foobarCheck.foo * foobarCheck.bar) == 0) {
    			foobarCheck.result += "FooBar,";
    			foobarCheck.foobarCount++;
    		}

    		foobarCheck.result += i;
		
    		if (i != 42)
    			foobarCheck.result += ",";
    		}
    	
    	return foobarCheck;
    }
	//=================================================================================================================
}
