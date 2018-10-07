package lifeGame;

import java.util.Random;

public class Logic {
	private int rows;	//��
	private int columns;//��
	private int steps;	//���ܴ���
	
	//��ʼ��
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
	
	//��ȡ����ϸ��״̬
	public boolean[][] get()
	{
		boolean[][] currentGeneration = Data.getGeneration();		
		return currentGeneration;
	}

	//ϸ������
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
	
	//Ѱ�ң�x,y����ϸ���ܱߵ�����ϸ����
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
	
	//�ж�ϸ��λ���Ƿ�����Ч����*/
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
	
	//��ȡ��ǰ�����ϸ����
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
	
	//������ɳ�ʼ����ϸ��
	public void randomInit()
	{
		int s;
		Random a=new Random();
		boolean[][] currentGeneration = new boolean[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				s=Math.abs(a.nextInt(2));	//����0-1�����
				if(s == 1)
					currentGeneration[i][j] = true;
				else
					currentGeneration[i][j] = false;				
			}
		}		
		Data.setGeneration(currentGeneration);		
	}
	
	//��ȡϸ�����ܴ���
	public int getIter()
	{
		return steps;		
	}
	
	//��ȫ��ϸ����Ϊ����״̬
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
	
	//����ϸ������״̬
	public void set(boolean[][] temp)
	{
		Data.setGeneration(temp);
	}
}
