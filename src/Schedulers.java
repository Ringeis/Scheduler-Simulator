import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jonathan
 *
 */
public class Schedulers {
	
	int totalTime[] = new int[4];
	double fifoACT = 0;
	double shortestACT = 0;
	double roundR3ACT = 0;
	double roundR5ACT = 0;
	Queue jobTime = new LinkedList();
	Queue<String> jobName = new LinkedList();
	String[] jobArray; //for shortest
	
	public Schedulers(Queue<String> q, Queue<Integer> ints) {
		jobArray = new String[q.size()];
		for(int i = 0; i < q.size(); i++){
			jobName.add(q.element());
			jobArray[i] = q.element();
			q.add(q.remove());
		}
		populateQueue(ints.size(), ints);
		fifo(jobTime);
		populateQueue(ints.size(), ints);
		shortest(jobTime);
		populateQueue(ints.size(), ints);
		roundR3(jobTime);
		populateQueue(ints.size(), ints);
		roundR5(jobTime, q);
		
	}

	private void fifo(Queue ints){
		int tempTime = 0;
		System.out.println("First In First Out");
		System.out.print("|");
		while(!ints.isEmpty()){
			tempTime+= (int)ints.element();
			for(int i = 0; i < (int)ints.element()-1;i++){
				System.out.print("-");
			}
			System.out.print(" " + jobName.element() + ":" + tempTime + " ");
			jobName.add(jobName.remove());
			ints.remove();
			totalTime[0]+=tempTime;
		}
		System.out.print("|");
		System.out.print("\n");
	}
	
	private void shortest(Queue ints){
		Object[] elements = ints.toArray();
		elements = quickSort(elements, 0, elements.length-1);
		int tempTime = 0;
		System.out.println("Shortest First");
		System.out.print("|");
		for(int i = 0; i < elements.length; i++){
			tempTime+= (int) elements[i];
			for(int j = 0; j < (int)elements[i]-1;j++){
				System.out.print("-");
			}
			System.out.print(" " + jobArray[i] + ":" + tempTime + " ");
			totalTime[1]+=tempTime;
		}
		System.out.print("|");
		System.out.print("\n");
		ints.clear(); //empty queue for next task.
	}
	
	private void roundR3(Queue ints){
		int tempTime = 0;
		System.out.println("Round Robin (3)");
		System.out.print("|");
		while(!ints.isEmpty()){
			if((int)ints.element() > 3){
				tempTime+= 3;
				for(int i = 0; i < 3;i++){
					System.out.print("-");
				}
				System.out.print(" " + jobName.element() + "(" + ((int)ints.element()-3) + ")" + " ");
				jobName.add(jobName.remove());
				ints.add((int)ints.remove() - 3);
			}else{
				tempTime+= (int)ints.element();
				for(int j = 0; j < (int)ints.element()-1;j++){
					System.out.print("-");
				}
				System.out.print(" " + jobName.element() + ":" + tempTime + " ");
				jobName.add(jobName.remove());
				ints.remove();
				totalTime[2]+=tempTime;
			}
		}
		System.out.print("|");
		System.out.print("\n");
	}
	
	private void roundR5(Queue ints, Queue<String> q){
		int tempTime = 0;
		System.out.println("Round Robin (5)");
		System.out.print("|");
		while(!ints.isEmpty()){
			if((int)ints.element() > 5){
				tempTime+= 5;
				for(int i = 0; i < 5;i++){
					System.out.print("-");
				}
				System.out.print(" " + q.element() + "(" + ((int)ints.element()-5) + ")" + " ");
				q.add(q.remove());
				ints.add((int)ints.remove() - 5);
			}else{
				tempTime+= (int)ints.element();
				for(int j = 0; j < (int)ints.element()-1;j++){
					System.out.print("-");
				}
				System.out.print(" " + q.element() + ":" + tempTime + " ");
				q.add(q.remove());
				ints.remove();
				totalTime[3]+=tempTime;
			}
		}
		System.out.print("|");
		System.out.print("\n");
	}
	
	public void analyze(int i){
		fifoACT = Math.floor(((double)totalTime[0]/i)*100)/100;
		shortestACT = Math.floor(((double)totalTime[1]/i)*100)/100;
		roundR3ACT = Math.floor(((double)totalTime[2]/i)*100)/100;
		roundR5ACT = Math.floor(((double)totalTime[3]/i)*100)/100;
	}
	
	private void populateQueue(int size, Queue ints){
		for(int i = 0; i < size; i++){
			jobTime.add(ints.element());
			ints.add(ints.remove());
		}
	}
	
	int partition(Object[] elements, int left, int right){
		int i = left, j = right;
	    int tmp;
	    String job;
	    int pivot = (int) elements[(left + right) / 2];
	    while (i <= j) {
	    	while ((int)elements[i] < pivot)
	    		i++;
	    	while ((int)elements[j] > pivot)
	    		j--;
	    	if (i <= j) {
	    		tmp = (int)elements[i];
	    		elements[i] = elements[j];
	    		elements[j] = tmp;
	    		job = jobArray[i];
	    		jobArray[i] = jobArray[j];
	    		jobArray[j] = job;
	    		i++;
	    		j--;
	    	}
	    };
	    return i;
	}
	 
	Object[] quickSort(Object[] elements, int left, int right) {
	      int i = partition(elements, left, right);
	      if (left < i - 1)
	            quickSort(elements, left, i - 1);
	      if (i < right)
	            quickSort(elements, i, right);
	      return elements;
	}
}
