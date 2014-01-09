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
		//File file = new File("C:/Users/Jonathan/workspace/CS431Project/src/testdata1.txt"); //adjusted for each test
		//File file = new File("C:/Users/Jonathan/workspace/CS431Project/src/testdata2.txt"); 
		File file = new File("C:/Users/Jonathan/workspace/CS431Project/src/testdata3.txt");
        try {
        	Scanner scanner = new Scanner(file);
        	while(scanner.hasNextLine()){
        		q.add(scanner.nextLine());
        		ints.add(Integer.parseInt(scanner.nextLine()));
        	}
        	scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		Schedulers schedulers = new Schedulers(q, ints);
		schedulers.analyze(q.size());
		System.out.println("FIFO Average Completion Time: " + schedulers.totalTime[0] + "/"
							+ q.size() + "=" + schedulers.fifoACT + "\n" 
							+ "Shortest First Average Completion Time: " 
							+ schedulers.totalTime[1] + "/" + q.size() + "=" 
							+ schedulers.shortestACT + "\n" + "RR3 Average Completion Time: " 
							+ schedulers.totalTime[2] + "/" + q.size() + "="
							+ schedulers.roundR3ACT + "\n" + "RR5 Average Completion Time: " 
							+ schedulers.totalTime[3] + "/" + q.size() + "="
							+ schedulers.roundR5ACT);
	}

}
