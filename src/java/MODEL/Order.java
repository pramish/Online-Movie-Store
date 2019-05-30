package MODEL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Order {

    private String ID;
//    private String CustomerID;   
    private String MovieID;
    private String UserID;
    private BigDecimal Price;
    private Integer Amount;
    private BigDecimal TotalPrice;
    private LocalDate Date;
    private String Status;
    private String title;
    private Integer stock=0;

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
    public Order(String ID, String MovieID, String UserID, BigDecimal Price, Integer Amount, BigDecimal TotalPrice, LocalDate Date, String Status, String title, Integer stock) {
        this.ID = ID;
//       this.CustomerID = CustomerID;
        this.MovieID = MovieID;
        this.UserID = UserID;
        this.Price = Price;
        this.Amount = Amount;
        this.TotalPrice = TotalPrice;
        this.Date = Date;
        this.Status = Status;
        this.title = title;
        this.stock = stock;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String MovieID) {
        this.MovieID = MovieID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer Amount) {
        this.Amount = Amount;
    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
