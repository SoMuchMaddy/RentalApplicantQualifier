//An enumk that returns a score for the input utility usage compared to income

public enum Score{
	//The four possible scores
	GREAT, GOOD, FAIR, POOR;
	
	public static Score getScore(double usage) {
		
		if (usage <= 35) {
			
			return GREAT;
		} else if (usage <= 43) {
			
			return GOOD;
		} else if (usage <= 50) {
			
			return FAIR;
		} else {
		
			return POOR;
		}
	}
}
