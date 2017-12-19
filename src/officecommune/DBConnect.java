/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officecommune;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author dell
 */
public class DBConnect 
{
    
    int s=0;
    JOptionPane J = new JOptionPane();
    
    static boolean checkDB()
        {
            try
            {
                Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/dell/Documents/NetBeansProjects/DB-Folder");
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
                if(rs.first())
                {
                    if(rs.getString(1)=="admin")
                    return true;
                }
                else
                    return false;
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"SQLite Not Connected");
            }
            return false;
        }
    void connectDB()
    {
        Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/dell/Documents/NetBeansProjects/DB-Folder/Sample.db");
    }
    

/*try
            {
                J.showMessageDialog(null,"2");
                Class.forName("com.mysql.jdbc.driver");
                
                /*Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/dell/Documents/NetBeansProjects/DB-Folder/Sample.db");
                PreparedStatement pst = con.prepareStatement("Select username from loginform where username=? and password=?");
                pst.setString(1,name.getText());
                pst.setInt(2,Integer.parseInt(pass.getText()));
                J.showMessageDialog(null,"3");
                ResultSet rs = pst.executeQuery();
                if(rs.first())
                {
                   Welcome W;
                   W= new Welcome();
                   W.setSession(name.getText());
                   W.start();
                   
                   this.setVisible(false);
                }
            
            catch(Exception e)
            {
                
                J.showMessageDialog(null,"Server not connected");
            }*/


void setSession(int n)
{
    s=n;
}

}