package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

//½ºÇÁ¸µÄÁÅ×ÀÌ³Ê°¡ ÀÚµ¿À¸·Î °´Ã¼ »ý¼ºÇÔ
@Component
public class DBOpen { //µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á
  
	public DBOpen() {
		System.out.println("---- DBOpen() °´Ã¼ »ý¼ºµÊ ----");
	}
	
  public Connection getConnection() {
    //1) Oracle DB ï¿½ï¿½ï¿½ï¿½------------------------------
    String url      = "jdbc:oracle:thin:@localhost:1521:xe";
    String user     = "java1113";
    String password = "1234";
    String driver   = "oracle.jdbc.driver.OracleDriver";

    //2) MySQL DB ï¿½ï¿½ï¿½ï¿½-------------------------------
    /*
    String url      = "jdbc:mysql://localhost:3306/soldesk?useUnicode=true&characterEncoding=utf8";
    String user     = "root";
    String password = "1234";
    String driver   = "org.gjt.mm.mysql.Driver"; 
    */
    
    Connection con = null;
    
    try {

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);

    }catch (Exception e) {
        System.out.println("DBï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ : " + e);
    }//try end   
    
    return con;
    
  }//end

}//class end
