import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Project {
	
	
	int averageCompletionTime = 0;
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Queue<String> q = new LinkedList();
		Queue<Integer> ints = new LinkedList();
		q.add("Job1");
		ints.add(6);
		q.add("Job2");
		ints.add(4);
		q.add("Job3");
		ints.add(10);
		q.add("Job4");
		ints.add(7);
		q.add("Job4");
		ints.add(24);
		q.add("Job4");
		ints.add(11);
		q.add("Job4");
		ints.add(32);
		Schedulers schedulers = new Schedulers(q, ints);
		schedulers.analyze(q.size());
		System.out.println(schedulers.fifoATC + "\n" + schedulers.roundR3ATC
							+ "\n" + schedulers.roundR5ATC);
		
		
		int count = 1;
		int sum = 0;
		for(int i = 0; i < 99; i++){
			sum+= (4*count);
			count++;
		}
		System.out.println(sum);
	}

}
