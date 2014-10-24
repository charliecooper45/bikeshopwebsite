package beans;

public class Brand implements Comparable<Brand> {
	private String name;
	
	public Brand(String name) {
		this.name = name;
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
