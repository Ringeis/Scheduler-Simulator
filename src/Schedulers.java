import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Jonathan
 *
 */
public class Schedulers {
	
	int totalTime[] = new int[4];
	double fifoATC = 0;
	double shortestATC = 0;
	double roundR3ATC = 0;
	double roundR5ATC = 0;
	Queue jobTime = new LinkedList();
	
	public Schedulers(Queue<String> q, Queue<Integer> ints) {
		populateQueue(ints.size(), ints);
		fifo(jobTime);
		//populateQueue(ints.size(), ints);
		//shortest(jobTime);
		populateQueue(ints.size(), ints);
		roundR3(jobTime);
		populateQueue(ints.size(), ints);
		roundR5(jobTime);
		
	}

	private void fifo(Queue ints){
		int tempTime = 0;
		while(!ints.isEmpty()){
			tempTime+= (int)ints.remove();
			totalTime[0]+=tempTime;
		}
	}
	
	private void shortest(Queue ints){
		
	}
	
	private void roundR3(Queue ints){
		int tempTime = 0;
		while(!ints.isEmpty()){
			if((int)ints.element() > 3){
				tempTime+= 3;
				ints.add((int)ints.remove() - 3);
			}else{
				tempTime+= (int)ints.remove();
				totalTime[2]+=tempTime;
			}
		}
	}
	
	private void roundR5(Queue ints){
		int tempTime = 0;
		while(!ints.isEmpty()){
			if((int)ints.element() > 5){
				tempTime+= 5;
				ints.add((int)ints.remove() - 5);
			}else{
				tempTime+= (int)ints.remove();
				totalTime[3]+=tempTime;
			}
		}
	}
	
	public void analyze(int i){
		fifoATC = (double)totalTime[0]/i;
		shortestATC = (double)totalTime[1]/i;
		roundR3ATC = (double)totalTime[2]/i;
		roundR5ATC = (double)totalTime[3]/i;
	}
	
	private void populateQueue(int size, Queue ints){
		for(int i = 0; i < size; i++){
			jobTime.add(ints.element());
			ints.add(ints.remove());
		}
	}
	
	public void radixSort(Queue ints){
		
	}
}
