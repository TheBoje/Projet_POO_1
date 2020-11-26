package Items;

public class Item {

	private String name;

	public Item(String name){
		this.name = name;
	}
	public void use() {
		// TODO - implement Object.use
		throw new UnsupportedOperationException();
	}

	public String getName(){
		return this.name;
	}
}