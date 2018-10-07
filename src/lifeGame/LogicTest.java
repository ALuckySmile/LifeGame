package lifeGame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class LogicTest {
	private static Logic logic = new Logic(4,4);
	
    @Before
    public void setUp() throws Exception{
    	logic.setFalse();//将全部细胞置为死亡，迭代次数清0，回到初始状态
    }
    @After
    public void tearDown() throws Exception{
    	
    }
	
    @Test
    public void testIsValid() {
    	assertEquals(false,logic.isVaild(-1, 0));
    	assertEquals(false,logic.isVaild(4, 4));
    	assertEquals(true,logic.isVaild(0, 1));    	
    }
    
    @Test
    public void testFindAlive() {
		boolean[][] input = new boolean[4][];
		input[0] = new boolean[] {false,true,false,true};
		input[1] = new boolean[] {true,false,true,false};
		input[2] = new boolean[] {true,true,false,false};
		input[3] = new boolean[] {false,true,true,false};
		logic.set(input);		
    	assertEquals(2,logic.findAlive(0,0));
    	assertEquals(3,logic.findAlive(3, 1));  	
    }
    
	@Test
	public void testReproduction() {
		boolean[][] input = new boolean[4][];
		input[0] = new boolean[] {false,true,false,true};
		input[1] = new boolean[] {true,false,true,false};
		input[2] = new boolean[] {true,true,false,false};
		input[3] = new boolean[] {false,true,true,false};
		boolean[][] output = new boolean[4][];
		output[0] = new boolean[] {false,true,true,false};
		output[1] = new boolean[] {true,false,true,false};
		output[2] = new boolean[] {true,false,false,false};
		output[3] = new boolean[] {true,true,true,false};
		logic.set(input);
		logic.reproduction();
		assertEquals(output,logic.get());		
	}

	@Test
	public void testGetAlive() {
		boolean[][] input = new boolean[4][];
		input[0] = new boolean[] {false,true,false,true};
		input[1] = new boolean[] {true,false,true,false};
		input[2] = new boolean[] {true,true,false,false};
		input[3] = new boolean[] {false,true,true,false};
		logic.set(input);		
    	assertEquals(8,logic.getAlive());
	}

	@Test
	public void testSetFalse() {
		boolean[][] input = new boolean[4][];
		input[0] = new boolean[] {false,true,false,true};
		input[1] = new boolean[] {true,false,true,false};
		input[2] = new boolean[] {true,true,false,false};
		input[3] = new boolean[] {false,true,true,false};
		boolean[][] output = new boolean[4][];
		output[0] = new boolean[] {false,false,false,false};
		output[1] = new boolean[] {false,false,false,false};
		output[2] = new boolean[] {false,false,false,false};
		output[3] = new boolean[] {false,false,false,false};
		logic.set(input);
		logic.setFalse();
		assertEquals(output,logic.get());	
	}
	
	@Test
	public void testIter(){
		boolean[][] input = new boolean[4][];
		input[0] = new boolean[] {false,true,false,true};
		input[1] = new boolean[] {true,false,true,false};
		input[2] = new boolean[] {true,true,false,false};
		input[3] = new boolean[] {false,true,true,false};
		logic.set(input);
		logic.reproduction();
		logic.reproduction();
		logic.reproduction();
		assertEquals(3,logic.getIter());
		logic.setFalse();
		assertEquals(0,logic.getIter());
	}
	
	@Test
	public void testRandomInit(){
		//判断两次随机生成的布尔数组是否一致
		logic.randomInit();
		boolean[][] test1 = logic.get();
		logic.randomInit();
		boolean[][] test2 = logic.get();
		assertEquals(false,isSame(test1,test2));		
	}
	
	private boolean isSame(boolean[][] a, boolean[][] b)
	{
		int rows = a.length;
		int cols = a[0].length;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
			{
				if(a[i][j] != b[i][j])
					return false;				
			}
		return true;
	}
}
