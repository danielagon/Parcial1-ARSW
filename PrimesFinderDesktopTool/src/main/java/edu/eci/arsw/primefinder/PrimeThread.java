/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class PrimeThread extends Thread{
    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private volatile boolean isPaused = false;
    private final Object lock = new Object();
    private volatile boolean isRunning = true;

    public PrimeThread(BigInteger a, BigInteger b, PrimesResultSet prs) {
        this.a = a;
        this.b = b;
        this.prs = prs;
    }
    
    @Override
    public void run(){
        MathUtilities mt=new MathUtilities();

        int itCount=0;

        BigInteger i=a;
        while (i.compareTo(b)<=0){
            synchronized(lock){
                if (isPaused){
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PrimeThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
        
    }
    
    public void pause(){
        isPaused = true;
        isRunning = false;
    }
    
    public boolean isRunning(){
        return isRunning;
    }
    
    public void started(){
        synchronized(lock){
            isPaused = false;
            lock.notifyAll();
        }
    }
}
