package beans;

public class Brand implements Comparable<Brand> {
	private int id;
	private String name;
	
	public Brand(String name) {
		this.name = name;
	}
	
	public Brand(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Brand brand) {
		return name.compareTo(brand.name);
	}
}
