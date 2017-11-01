package test;
	import static org.junit.Assert.*;

	import java.util.NoSuchElementException;

	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;

	import set.ArraySet;
	import set.MaxSet;
	import set.RemoveDuplicates;

	public class RemoveDuplicateTest {
		private RemoveDuplicates rd;

		@Before
		public void setUp() throws Exception {
			rd = new RemoveDuplicates();
		}

		@After
		public void tearDown() throws Exception {
			rd = null;
		}

		@Test
		public final void testNullPointer() {
			try {
				int[] i = rd.uniqueElements(null);
				fail("uniqueElements should catch NullPointException");
			} catch (NullPointerException e) {
			}
		}
		
		@Test
		public final void testEmptySet() {
			int[] ints = new int[10];
			int[] i  = rd.uniqueElements(ints);
			assertEquals("uniqueElements should return length 1", 1, i.length);
			// ints have 10 elements where everyone is null
		}
		
		@Test
		public final void testSingleElements() {
			int[] ints = new int[1];
			ints[0] = 7;
			assertEquals("uniqueElements should return int 7", 7, rd.uniqueElements(ints)[0]);
		}
		
		@Test
		public final void testManyInOrder() {
			int[] ints = new int[1000];
			for (int i = 0; i < 1000; i++) {
				ints[i] = i;
			}
			assertArrayEquals("uniqueElements should return 999", ints, rd.uniqueElements(ints));
		}

		@Test
		public final void testManyInDisorder() {
			int[] ints = new int[1000];
			int[] queue = new int[1000];
			for (int i = 0; i < 1000; i++) {
				queue[i] = i;
			}
			for (int i = 999; i >= 0; i--) {
				ints[i] = i;
			}
			assertArrayEquals("uniqueElements should return 1-999", queue, rd.uniqueElements(ints));	
		}
		
		@Test
		public final void testNegatives() {
			int[] ints = new int[5];
			ints[0] = 1;
			ints[1] = -2;
			ints[2] = -5;
			ints[3] = -5;
			ints[4] = -5;
			assertEquals("",-5, rd.uniqueElements(ints)[0]);
			assertEquals("",-2, rd.uniqueElements(ints)[1]);
			assertEquals("", 1, rd.uniqueElements(ints)[2]);
			assertEquals("", 3, rd.uniqueElements(ints).length);
		}
}