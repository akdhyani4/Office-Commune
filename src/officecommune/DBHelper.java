/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officecommune;
import java.sql.*;

/**
 *
 * @author dell
 */
public class DBHelper {
    Connection conn = null;
    public String username = null;
    public Connection connect() {
      
        try {
            // db parameters
            String url = "jdbc:sqlite:sample.db";            // create a connection to the database

            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void createTables() 
    {
       try
       {
            Statement stmt = conn.createStatement();
            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            ResultSet rs = stmt.executeQuery(sql);
            int count=0;
            while(rs.next())
            {
            //System.err.println(rs.getString(1));
            count++;            
            }
            if(count==0)
            {
            Statement stmt2 = conn.createStatement();
            String sql2 = "create table users (username varchar(25),password varchar(32))";
            stmt2.execute(sql2);
            }
       }
        catch(Exception e)
        {
        System.err.println(e);
        }
        
    }
    public String getData(String column) throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select "+column+" from users where username = '"+username+"'");
        if(rs.next()){
            return rs.getString(column);
        }
        return null;
    }
    
     public boolean registerUser(String username, String password){
        try{
            PreparedStatement pst = conn.prepareStatement("insert into users values (?,?)");
            pst.setString(1,username);
            pst.setString(2, password);
            pst.execute();
            return true;
        }catch(Exception ex){
            System.err.println(ex);
            return false;
        }        
    }
     
     public boolean loginUser(String username, String password){
        try
        {
            PreparedStatement pst = conn.prepareStatement("select username from users where username = ? and password = ?");
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();
            int count=0;
            while(rs.next())
            {
            //System.err.println(rs.getString(1));
            count++;            
            }
            if(count==1)
                return true;
            else 
                return false;
        }
        catch(Exception ex){
            System.err.println(ex);
            return false;
        }        
    }

    public boolean checkRegisteredUser(String username) {
        
        try
        {
            PreparedStatement pst=conn.prepareStatement("select * from users where username = ?");
            pst.setString(1, username);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
}