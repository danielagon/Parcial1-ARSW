package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
@Service
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = RequestMethod.GET )
    public ResponseEntity<?> getPrimes()
    {
        return new ResponseEntity<>(primeService.getFoundPrimes(),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/primes", method = RequestMethod.POST)
    public ResponseEntity<?> postPrimes(@RequestBody FoundPrime foundPrime){
        if (primeService.getFoundPrimes().contains(foundPrime)){
            return new ResponseEntity<>("El numero ya fue registrado por otro usuario", HttpStatus.NOT_ACCEPTABLE);
        }else{
            primeService.addFoundPrime(foundPrime);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/primes/{primenumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimeNumber(@PathVariable String primeNumber){
        if (primeService.getPrime(primeNumber) == null){
            return new ResponseEntity<>("El numero no esta registrado",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(primeService.getPrime(primeNumber),HttpStatus.ACCEPTED);
        }
    }



}
