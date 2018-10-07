package lifeGame;

import java.util.Random;

public class Logic {
	private int rows;	//行
	private int columns;//列
	private int steps;	//繁衍次数
	
	//初始化
	public Logic(int row,int col)
	{
		rows = row;
		columns = col;
		steps = 0;	
		boolean[][] currentGeneration = new boolean[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				currentGeneration[i][j] = false;	
			}
		}
		Data.setGeneration(currentGeneration);
	}
	
	//获取生存细胞状态
	public boolean[][] get()
	{
		boolean[][] currentGeneration = Data.getGeneration();		
		return currentGeneration;
	}

	//细胞繁衍
	public void reproduction()
	{
		boolean[][] currentGeneration = Data.getGeneration();
		boolean[][] nextGeneration = new boolean[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(findAlive(i,j) == 3)
					nextGeneration[i][j] = true;
				else if(findAlive(i,j) == 2)
					nextGeneration[i][j] = currentGeneration[i][j];
				else
					nextGeneration[i][j] = false;
			}
		}
		currentGeneration = nextGeneration;
		Data.setGeneration(nextGeneration);
		steps++;
	}
	
	//寻找（x,y）处细胞周边的生存细胞数
	public int findAlive(int x,int y)
	//private int findAlive(int x,int y)
	{
		boolean[][] currentGeneration = Data.getGeneration();	
		int num = 0;
		if(isVaild(x-1,y-1)&&(currentGeneration[x-1][y-1]==true))
			num++;
		if(isVaild(x,y-1)&&(currentGeneration[x][y-1]==true))
			num++;
		if(isVaild(x+1,y-1)&&(currentGeneration[x+1][y-1]==true))
			num++;
		if(isVaild(x+1,y)&&(currentGeneration[x+1][y]==true))
			num++;
		if(isVaild(x+1,y+1)&&(currentGeneration[x+1][y+1]==true))
			num++;
		if(isVaild(x,y+1)&&(currentGeneration[x][y+1]==true))
			num++;
		if(isVaild(x-1,y+1)&&(currentGeneration[x-1][y+1]==true))
			num++;
		if(isVaild(x-1,y)&&(currentGeneration[x-1][y]==true))
			num++;		
		return num;
	}
	
	//判断细胞位置是否在有效区域*/
	//private boolean isVaild(int x,int y)
	public boolean isVaild(int x,int y)
	{
		if((x>=0)&&(x<rows)&&(y>=0)&&(y<columns))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//获取当前生存的细胞数
	public int getAlive()
	{
		boolean[][] currentGeneration = Data.getGeneration();	
		int num = 0;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(currentGeneration[i][j] == true)
					num++;				
			}
		}		
		return num;		
	}
	
	//随机生成初始生存细胞
	public void randomInit()
	{
		int s;
		Random a=new Random();
		boolean[][] currentGeneration = new boolean[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				s=Math.abs(a.nextInt(2));	//生成0-1随机数
				if(s == 1)
					currentGeneration[i][j] = true;
				else
					currentGeneration[i][j] = false;				
			}
		}		
		Data.setGeneration(currentGeneration);		
	}
	
	//获取细胞繁衍次数
	public int getIter()
	{
		return steps;		
	}
	
	//将全部细胞置为死亡状态
	public void setFalse()
	{
		boolean[][] temp = new boolean[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				temp[i][j] = false;			
			}
		}
		Data.setGeneration(temp);	
		steps = 0;
	}
	
	//设置细胞生存状态
	public void set(boolean[][] temp)
	{
		Data.setGeneration(temp);
	}
}
