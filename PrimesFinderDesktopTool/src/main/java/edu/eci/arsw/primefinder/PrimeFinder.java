package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder{
        
	private static List<PrimeThread> threadList = new LinkedList<>();
        private static boolean run = false;
        
	public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) throws InterruptedException{
            
                BigInteger a=_a;
                BigInteger b=_b;
                
                BigInteger numThread = new BigInteger("4");
                BigInteger div = (b.andNot(a)).divide(numThread);
                BigInteger res = (b.andNot(a)).mod(numThread);
                
                for (int i=0;i<numThread.intValue();i++){
                    if (i == numThread.intValue()-1){
                        threadList.add(new PrimeThread((new BigInteger(String.valueOf(i)).multiply(div)).add(a),(new BigInteger(String.valueOf(i)).multiply(div)).add(div).add(res), prs));
                    }else{
                        threadList.add(new PrimeThread((new BigInteger(String.valueOf(i)).multiply(div)).add(a), (new BigInteger(String.valueOf(i)).multiply(div)).add(div), prs));
                    }
                }
                
                for (PrimeThread i:threadList){
                    i.start();
                }
	}
        
        public static void pause(){
            for (PrimeThread i:threadList){
                i.pause();
            }
        }
        
        public static void launch(){
            int cont = 0;
            for (PrimeThread i: threadList){
                if (i.isRunning()){
                    cont++;
                }
                i.started();
            }
            if (cont == threadList.size()){
                run = true;
            }
        }
        
        public static boolean running(){
            for (PrimeThread i:threadList){
                if (i.isRunning()){
                    run = true;
                }
            }
            return run;
        }	
	
}
