
package MODEL;

import java.math.BigDecimal;


public class Order {
    private String ID;   
    private String CustomerID;   
    private String MovieID; 
    private String UserID; 
    private BigDecimal Price;    
    private BigDecimal Amount;    
    private BigDecimal TotalPrice;
    private String Date;
    private String Status;
    
    /**
     *
     * @param ID
     * @param CustomerID
     * @param MovieID
     * @param UserID
     * @param Price
     * @param Amount
     * @param TotalPrice
     * @param Date
     * @param Status
     */
    public Order (String ID, String CustomerID, String MovieID, String UserID,BigDecimal Price, BigDecimal Amount, BigDecimal TotalPrice, String Date, String Status) {
       this.ID = ID;
       this.CustomerID = CustomerID;
       this.MovieID = MovieID;
       this.UserID = UserID;
       this.Price = Price;
       this.Amount = Amount;
       this.TotalPrice = TotalPrice;
       this.Date = Date;
       this.Status = Status;
       
    }
}

