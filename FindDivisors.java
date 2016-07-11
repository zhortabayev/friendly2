public class FindDivisors {

	int counter = 0;
	static int theNumber = 0;
	
	public static int checkDivisors(int n) {
		
		int sum = 0;
		theNumber = n;
		sum += n;
		
		if(isEven(n)){			
			sum += n/2;
			n = n / 2;			
		}
		
		for(int i = n - 1; i > 0; i--) {
			if(theNumber % i == 0){
				sum += i;
			}
		}
		return sum;
	}
	
	private static boolean isEven(int n) {
		if (n % 2 == 0)
			return true;
	return false;
	}
	
}
