package lifeGame;


public class Data {

	private static boolean[][] current;		//´æ´¢µ±Ç°Ï¸°ûÉú´æ×´Ì¬
	
	//»ñÈ¡Ï¸°ûÉú´æ×´Ì¬
	public static boolean[][] getGeneration()
	{
		return current;	
	}
	
	//ÉèÖÃÏ¸°ûÉú´æ×´Ì¬
	public static void setGeneration(boolean[][] temp)
	{
		int rows =temp.length;
		int columns = temp[0].length;
		current = new boolean[rows][columns];
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<columns; j++)
			{
				current[i][j]=temp[i][j];
			}
		}
	}	
}
