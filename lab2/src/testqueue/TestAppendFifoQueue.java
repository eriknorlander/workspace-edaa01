package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;
public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public void testEmpty(){
		q1.append(q2);
		assertTrue("Null?" , q1.size() == 0);
	}
	
	@Test 
	public void testAppendEmpty() {
		q1.offer(1);
		q1.offer(2);
		q1.append(q2);
		assertTrue("Same size", q1.size() == 2);
	}
	
	@Test
	public void testEmptyAppend() {
		q1.offer(1);
		q1.offer(2);
		q2.append(q1);
		assertTrue("Same size", q2.size() == 2);
	}
	
	@Test
	public void testAppendDifferent() {
		int[] order = {1,2,3,10,9,8};
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q2.offer(10);
		q2.offer(9);
		q2.offer(8);
		q1.append(q2);
		int i = 0;
		int temp = 0;
		Iterator<Integer> itr = q1.iterator();
		while(itr.hasNext()){
			temp = itr.next();
			assertEquals("Same as order if work", order[i], temp);
			i++;
		}
	}
	
	@Test
	public void testAppendSame() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		try {
			q1.append(q1);
			fail("IllegalArgumentExcepton");
		} catch (IllegalArgumentException e) {
		}
	}
}
