package eric;

public class DIYArrayList<Item> {
	private Item[] elementData;
	private int size;

	
	public DIYArrayList() {
		elementData = (Item[]) new Object[5];
		size = 0;
	}
	
	public DIYArrayList(int i) {
		elementData = (Item[]) new Object[size];
		this.size = i;
	}

	/**
	 * improve remove(int id) to O(1), cause there's no need to keep ordered
	 */
	public Item remove(int id) {
		if(size == 0)
			return null;
		if(size == elementData.length >> 2){
			resize(elementData.length >> 1);
		}
		Item tmp = elementData[id];
		elementData[id] = elementData[size - 1];
		elementData[size--] = null;
		return tmp;
	}

	public boolean add(Item item) {
		if(size == elementData.length){
			resize(size * 2);
		}
		elementData[size++] = item;
		return true;
	}

	private void resize(int newSize) {
		Item[] tmp = (Item[]) new Object[newSize];
		for(int i = 0; i < size; i++){
			tmp[i] = elementData[i];
		}
		elementData = tmp;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Item get(int i) {
		return elementData[i];
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder("");
		for(int i = 0; i < size; i++){
			str.append(elementData[i].toString());
		}
		return str.toString();
	}
}
