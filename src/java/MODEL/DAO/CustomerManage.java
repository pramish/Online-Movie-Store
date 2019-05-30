package MODEL.DAO;

import MODEL.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerManage {

    private Statement st;

    public CustomerManage(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public boolean checkId(String id) throws SQLException {
        String fetch = "select id from CUSTOMER where id='" + id + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public void addCustomer(String ID, String NAME, String EMAIL, String TYPE, String ADDRESS, String STATUS) throws SQLException {
        st.executeUpdate("INSERT INTO CUSTOMER (ID, NAME, EMAIL, TYPE, ADDRESS, STATUS) VALUES ('" + ID + "','" + NAME + "','" + EMAIL + "','" + TYPE + "','" + ADDRESS + "','" + STATUS + "')");
    }

    public void updateCustomer(String ID, String NAME, String EMAIL, String TYPE, String ADDRESS, String STATUS) throws SQLException {
        st.executeUpdate("UPDATE CUSTOMER SET NAME='" + NAME + "', EMAIL='" + EMAIL + "', TYPE='" + TYPE + "', ADDRESS='" + ADDRESS + "', STATUS='" + STATUS + "' WHERE ID='" + ID + "'");
    }

    public void deleteCustomer(String id) throws SQLException {
        st.executeUpdate("DELETE FROM CUSTOMER WHERE ID = '" + id + "'");
    }

    public List<Customer> listCustomer() throws SQLException {
        List<Customer> list = new ArrayList<>();
        String fetch = "SELECT * FROM CUSTOMER";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setEmail(rs.getString(3));
            customer.setType(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setStatus(rs.getString(6));
            list.add(customer);
        }
        return list;
    }

    public Customer getCustomer(String id) throws SQLException {
        Customer customer = new Customer();
        String fetch = "SELECT * FROM CUSTOMER WHERE ID='" + id + "'";
        ResultSet rs = st.executeQuery(fetch);
        if (rs.next()) {
            customer.setId(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setEmail(rs.getString(3));
            customer.setType(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setStatus(rs.getString(6));
        }
        return customer;
    }

    public List<Customer> searchCustomer(String name, String type) throws SQLException {
        List<Customer> list = new ArrayList<>();
        String fetch = "SELECT * FROM CUSTOMER WHERE NAME LIKE '%" + name + "%' AND TYPE LIKE '%" + type + "%'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setEmail(rs.getString(3));
            customer.setType(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setStatus(rs.getString(6));
            list.add(customer);
        }
        return list;
    }
}
