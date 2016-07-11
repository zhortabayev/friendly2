
public class ParallelOutput extends Thread {

	int start, smallEnd, bigEnd;
	
	public ParallelOutput(int start, int smallEnd, int bigEnd) {
		this.start = start;
		this.smallEnd = smallEnd;
		this.bigEnd = bigEnd;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = start; i <= smallEnd; i++)
			for(int j = i + 1; j <= bigEnd; j++)
				if(FriendlyThread.numbersAndRatios.get(i).equals(FriendlyThread.numbersAndRatios.get(j)))
					System.out.println(i + " and " + j + " are FRIENDLY");
	}
	
	

}
