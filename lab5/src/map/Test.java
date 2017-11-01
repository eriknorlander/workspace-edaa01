package map;

public class Test {

	public static void main(String[] args) {
		SimpleHashMap<String, Integer> map = new SimpleHashMap<String,Integer>();
		map.put("abc", 0);
		map.put("def", 1);
		map.put("ghi", 2);
		map.put("jkl", 3);
		map.put("abc", 4);
		
		System.out.print(map.show());
	}
}
