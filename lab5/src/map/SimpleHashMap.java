package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K,V>[] table;
	private int capacity;
	private double load;
	private int vectorSize;
	private int totalSize;
	private StringBuilder sb;
	
	public SimpleHashMap(){
		capacity = 16;
		load = 0.75;
		table = (Entry<K,V>[]) new Entry[capacity];
		vectorSize = 0;
	}
	
	public SimpleHashMap(int capacity){
		this.capacity = capacity;
		load = 0.75;
		table = (Entry<K,V>[]) new Entry[capacity];
		vectorSize = 0;
	}
	
	public String show(){
		sb = new StringBuilder();
		int i = 0;
		for(Entry e:table){
			sb.append(i + "    ");
			while(e!=null){
				sb.append(e.toString());
				sb.append(e.getValue() + " ");
				e = e.next;
			}
			sb.append(System.lineSeparator());
			i++;
		}
		return sb.toString();
	}
	@Override
	public V get(Object obj) {
		if(totalSize == 0){
			return null;
		}
		int i = index((K) obj);
		if(i<0){
			i = i + capacity;
		}
		Entry<K,V> e = table[i];
		if(e==null){
			return null;
		}
		while(!e.getKey().equals(obj)){
			e = e.next;
		}
		return e.getValue();
	}

	@Override
	public boolean isEmpty() {
		if(vectorSize==0){
			return true;
		}
		return false;
	}
	
	private int index(K key){
		//hashCode() översätter key till ett heltal
		return key.hashCode() % table.length;
	}
	
	private Entry<K,V> find(int index, K key){
		if(index < table.length && vectorSize > 0 && table[index]!=null){
			Entry<K,V> n = table[index];
			while(n!=null && !n.getKey().equals(key)){
				n = n.next;
			}
			return n;
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		int i = index(key);
		if(i < 0){
			i = capacity + i;
		}
		Entry<K,V> found = find(i,key);
		if(found !=null){ 
			V oldVal = found.getValue();
			found.setValue(value);
			return oldVal;
		} 
		Entry<K,V> fresh = new Entry<K,V>(key,value);
		if(table[i]==null){
			table[i] = fresh;
			vectorSize++;
			totalSize++;
			if((int) capacity*load < vectorSize){
				this.rehash();
			}
			return null;
		}
		
		fresh.next = table[i];
		table[i] = fresh;
		totalSize++;
		return null;
	}
	
	private void rehash(){
		Entry<K,V> [] oldTable = table;
		capacity = 2*capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		totalSize = 0;
		vectorSize = 0;
		for(Entry<K,V> e : oldTable){
			while(e!=null){
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}
	@Override
	public V remove(Object key) {
		int index = index((K) key);
		if(totalSize == 0 || find(index,(K) key)==null){
			return null;
		}
		Entry<K,V> e = table[index];	
		if(e.getKey().equals(key)){
			V removedValue = e.getValue(); 
			e = e.next;
			if(e==null){
				vectorSize--;
			}
			totalSize--;
			return removedValue;
		}
		while(e!=null && !e.next.getKey().equals(key)){
			e = e.next;
		}
		V removedValue = e.next.getValue();
		e.next=e.next.next;
		totalSize--;
		return removedValue;
		
	}

	@Override
	public int size() {
		return totalSize;
	}
	
	private static class Entry<K,V> implements Map.Entry<K,V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(key + "=");
			return sb.toString();
		}	
	}

}
