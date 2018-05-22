package foobarbackend.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Foobar {
    @Id 
    @GeneratedValue
    private long id;
    
    public int foo = 0;
    public int fooCount = 0;
    public int bar = 0;
    public int barCount = 0;
    public int foobarCount = 0;
    public String result = "";
    public Date date;

    public Foobar() {
		// Auto-generated constructor stub
	}

    public Foobar(int foo, int bar) {
    	this.foo = foo;
    	this.bar = bar;
    }

	@JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public int getFoo() {
        return foo;
    }

    @JsonProperty
    public int getFooCount() {
        return fooCount;
    }

    @JsonProperty
    public int getBar() {
        return bar;
    }

    @JsonProperty
    public int getBarCount() {
        return barCount;
    }

    @JsonProperty
    public int getFoobarCount() {
        return foobarCount;
    }
    
    @JsonProperty
    public String getResult() {
        return result;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }
}
