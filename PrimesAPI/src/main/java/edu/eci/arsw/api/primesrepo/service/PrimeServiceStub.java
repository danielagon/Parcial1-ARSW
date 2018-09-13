package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import java.util.ArrayList;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService{
    
    private static final List<FoundPrime> primes = new ArrayList<>();
    
    @Override
    public void addFoundPrime( FoundPrime foundPrime ){
        boolean found = false;
        for (FoundPrime f: primes){
            if (f.getPrime().equals(foundPrime.getPrime())){
                found = true;
            }
        }
        if (!found){
            synchronized (primes){
                primes.add(foundPrime);
            }
        }
    }

    @Override
    public List<FoundPrime> getFoundPrimes(){
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime ){
        FoundPrime p = null;
        for (FoundPrime i:primes){
            if (i.getPrime().equals(prime)){
                p = i;
            }
        }
        return p;
    }
    
    static {
        FoundPrime f = new FoundPrime();
        f.setUser("John");
        f.setPrime("32416190071");
        primes.add(f);
    }
}
