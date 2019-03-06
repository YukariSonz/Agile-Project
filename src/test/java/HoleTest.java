import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HoleTest {

	@Test
	public void holeConstructorTest() {
		Hole hole = new Hole();
		assertEquals(9, hole.getNumberOfKorgools());
		assertEquals(false, hole.getTuz());
	}
	
	@Test
	public void setNumberOfKorgoolsTest() {
		Hole hole = new Hole();
		assertEquals(10, hole.setNumberOfKorgools(10));
		assertEquals(0, hole.setNumberOfKorgools(-1));
		assertEquals(0, hole.setNumberOfKorgools(-100));
		assertEquals(5, hole.setNumberOfKorgools(5));
	}
	
	@Test
	public void addKorgoolsTest() {
		Hole hole = new Hole();
		assertEquals(10, hole.addKorgools(1));
		assertEquals(13, hole.addKorgools(3));
		assertEquals(12, hole.addKorgools(-1));
		assertEquals(11, hole.addKorgools(-1));
		assertEquals(0, hole.addKorgools(-100));
	}
	
	@Test
	public void getNumberOfKorgoolsTest() {
		Hole hole = new Hole();
		assertEquals(9, hole.getNumberOfKorgools());
		hole.addKorgools(5);
		assertEquals(14, hole.getNumberOfKorgools());
	}
	
	@Test
	public void setTuzTest() {
		Hole hole = new Hole();
		assertEquals(true, hole.setTuz());
		assertEquals(false, hole.setTuz());
		assertEquals(true, hole.setTuz());
		assertEquals(false, hole.setTuz());
		assertEquals(true, hole.setTuz());
	}
	
	@Test
	public void getTuzTest() {
		Hole hole = new Hole();
		assertEquals(false, hole.getTuz());
		assertEquals(false, hole.getTuz());
		hole.setTuz();
		assertEquals(true, hole.getTuz());
	}
}
