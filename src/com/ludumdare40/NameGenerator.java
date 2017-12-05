package com.ludumdare40;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class NameGenerator implements Iterable<String>, Iterator<String> {
    public ArrayList<String> names;
    public NameGenerator() {
        names = new ArrayList<>();
       File file = new File("names.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                names.add(scan.nextLine());
            }
        } catch(Exception e) {
        	 InputStream iStream = NameGenerator.class.getResourceAsStream("/names.txt"); // this supposes the csv is in the root of the jar file
             try  {
             	Scanner scan = new Scanner(iStream);
             	 while(scan.hasNextLine()) {
                      names.add(scan.nextLine());
             	 }
             } catch (Exception e1) {
             	 System.out.println("Unable to load names from file.");
                  System.out.println(e1.fillInStackTrace());
             }
        }
        
       
    }
    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
    	//return "Bob";
        return names.get((int)(Math.random() * names.size() ));
    }
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
