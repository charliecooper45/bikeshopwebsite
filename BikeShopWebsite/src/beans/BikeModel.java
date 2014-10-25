package beans;

public class BikeModel {
	private String name;
	private String price;
	private Brand brand;
	private String image;
	
	public BikeModel(String name, String price, Brand brand, String image) {
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.image = image;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
