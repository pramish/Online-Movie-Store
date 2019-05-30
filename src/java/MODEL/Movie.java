package MODEL;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Lucky
 */

public class Movie implements Serializable {

    
    private String ID;   
    private String title;   
    private String genre;    
    private BigDecimal price;    
    private int stock;    
    

    public Movie (String ID, String title, String genre, BigDecimal price, int stock  ) {
       this.ID = ID;
       this.title = title;
       this.genre = genre;
       this.price = price;
       this.stock = stock;
    }

    public Movie() { }
    
    public void updateDetails(String title, String genre, BigDecimal price, int stock ){
       
       this.title = title;
       this.genre = genre;
       this.price = price;
       this.stock = stock;
    }

    public boolean matchID(String ID){
        return this.ID.equals(ID.trim());
    }
    
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
}//end class
