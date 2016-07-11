import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FriendlyNumbers {
	
	public static volatile int theCounter = 0;

	public static void main(String [] args) {

		long startTime = System.currentTimeMillis();

		if(args.length != 1) {
			System.out.println("Please specify input file"); 
			return;
		}
		
		String input = args[0];		
		int [] numbers = new int [12];
		int counter = 0;		

		int start, end;
				
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			
			String str = "";
			
			while((str = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str, " ");
				
				if(st.hasMoreTokens()) {
					numbers[counter] = Integer.valueOf(st.nextToken());
					counter++;
					numbers[counter] = Integer.valueOf(st.nextToken());
					counter++;					
				}
			}			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		for(int outer = 0; outer < counter - 1; outer = outer + 2) {			
			
			start = numbers[outer];
			end = numbers[outer + 1];
			
			if((start == 0 && end == 0))
				break;
			
			System.out.println("Among numbers " + start + " and " + end);

			
			int counter2 = start;
			while(counter2 <= end / 8) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();	
			
			while(counter2 + 1 <= end / 4) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();
			
			while(counter2 + 1 <= end / 2) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			//System.gc();
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();
			
			while(counter2 + 1 <=  5 * end / 8) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			//System.gc();
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();
			
			while(counter2 + 1 <=  3 * end / 4) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();
			
			while(counter2 + 1 <= end) {
				new FriendlyThread(counter2).start();				
				counter2++; 		
			}
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			System.gc();

			
			ParallelOutput po0 = new ParallelOutput(numbers[outer], numbers[outer] + (end - start)/8, end);
			po0.start();	
				
			ParallelOutput po1 = new ParallelOutput(numbers[outer] + (end - start)/8 + 1, numbers[outer] + (end - start)/4, end);
			po1.start();		

			ParallelOutput po2 = new ParallelOutput(numbers[outer] + (end - start)/4 + 1, numbers[outer] + (end - start)/2, end);
			po2.start();			

			ParallelOutput po3 = new ParallelOutput(numbers[outer] + (end - start)/2 + 1, numbers[outer] + 3 * (end - start)/4, end);
			po3.start();

			ParallelOutput po4 = new ParallelOutput(numbers[outer] + 3 * (end - start)/4 + 1, end, end);
			po4.start();
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}			
			FriendlyThread.numbersAndRatios.clear();;
		}		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("The time spent is: "  + elapsedTime);
	}
}