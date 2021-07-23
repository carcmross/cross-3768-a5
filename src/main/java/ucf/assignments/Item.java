package ucf.assignments;

public class Item {
    private String name;
    private String serial;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

   public Item() {
       this.name = "";
       this.serial = "XXXXXXXXXX";
       this.price = "0.00";
   }

   public Item(String name, String serial, String price) {
       this.name = name;
       this.serial = serial;
       this.price = price;
   }
}
