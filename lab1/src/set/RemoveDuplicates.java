package set;

import java.util.Iterator;

public class RemoveDuplicates {
	
	public static int[] uniqueElements(int[] ints){
		MaxSet<Integer> ms = new MaxSet<Integer>();
		for (int i = 0; i < ints.length; i++){
			ms.add(ints[i]);
		}
		
		int[] output = new int[ms.size()];
		
		for (int i = output.length-1; i >= 0 ; i--){
			int max = ms.getMax();
			output[i] = max;
			ms.remove(max);
		}
		return output;
	}
	
	public static void main(String[] args){
		int[] i = {7,5,3,5,2,2,7};
		int[] b = uniqueElements(i);
		for (int c = 0; c < b.length; c++){
			System.out.print(b[c] + " ");
		}
	}
}
