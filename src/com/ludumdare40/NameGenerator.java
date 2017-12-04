package com.ludumdare40;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class NameGenerator implements Iterable<String>, Iterator<String> {
    public ArrayList<String> names;
    public NameGenerator() {
        names = new ArrayList<>();
        File file = new File("res/names.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                names.add(scan.nextLine());
            }
        } catch(Exception e) {
            System.out.println("Unable to load names from file.");
            System.out.println(e.fillInStackTrace());
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
        return names.get((int)(Math.random() * names.size() ));
    }
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
