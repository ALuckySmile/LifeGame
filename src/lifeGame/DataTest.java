package lifeGame;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.internal.runners.JUnit4ClassRunner;

@SuppressWarnings("deprecation")
@RunWith(JUnit4ClassRunner.class)
public class DataTest {

	@Test
	public void testSetGeneration() {
		boolean[][] temp = new boolean[40][50];
		for(int i = 0; i < 40; i++)
			for(int j = 0; j < 50; j++)
				temp[i][j] = false;
		Data.setGeneration(temp);
		assertEquals(temp,Data.getGeneration());				
	}
}
