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
    
    List<FoundPrime> primes = new ArrayList<>();
    
    @Override
    public void addFoundPrime( FoundPrime foundPrime )
    {
        primes.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime )
    {
        FoundPrime p = null;
        for (FoundPrime i:primes){
            if (i.equals(prime)){
                p = i;
            }
        }
        return p;
    }
}
