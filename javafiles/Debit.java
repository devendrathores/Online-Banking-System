import java.sql.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Debit extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();
int balance=0; 
String myAccount="";
/*FETCHING USER INPUT DETAILS*/
int debit=Integer.parseInt(req.getParameter("withdrawn"));

String a=String.valueOf(debit);
String am="-"+a;
String pm="+"+a;
Date date=Calendar.getInstance().getTime();
DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy hh:mm EEE");
String strDate=dateFormat.format(date);

HttpSession s = req.getSession();
String name=(String)s.getAttribute("uname");
String password=(String)s.getAttribute("upassword"); 
String userName="system";
String userPassword="oracle";
String driverName="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:xe";
String sql="select BALANCE from user_new_account where USERNAME=? AND PASSWORD=?";
try{
	Class.forName(driverName);
	Connection conn =DriverManager.getConnection(url,userName,userPassword);
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1,name);
	pst.setString(2,password);
	ResultSet rs = pst.executeQuery();
	if(rs.next()==true){
	balance=rs.getInt("BALANCE");	
	}
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}	

if(balance>=debit){
String SQL="update user_new_account set BALANCE=? where USERNAME=? AND PASSWORD=?";
balance=balance-debit;
try{
	Class.forName(driverName);
	Connection conn =DriverManager.getConnection(url,userName,userPassword);
	PreparedStatement pst = conn.prepareStatement(SQL);
	pst.setInt(1,balance);
	pst.setString(2,name);
	pst.setString(3,password);
	pst.executeUpdate();
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}
RequestDispatcher dispp = req.getRequestDispatcher("debit.html");
	  dispp.include(req,res);
	  out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('Amount Debited Successfuly !!');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");	
}
else{
	  RequestDispatcher disp = req.getRequestDispatcher("debit.html");
	  disp.include(req,res);
	  out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('Insufficient Balance In Account !!');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");
	  }

//TRANSACTION HISTORY
String Sql="select ACCOUNT_NO from user_new_account where USERNAME=? AND PASSWORD=?";
try{
Class.forName(driverName);
Connection conn1 =DriverManager.getConnection(url,userName,userPassword);
PreparedStatement Pst = conn1.prepareStatement(Sql);
Pst.setString(1,name);
Pst.setString(2,password);
ResultSet rs1 = Pst.executeQuery();
if(rs1.next()==true){myAccount=rs1.getString("ACCOUNT_NO");}
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}

String myTable="insert into H"+myAccount+" values(?,?,?)";
try{
Class.forName(driverName);
Connection con =DriverManager.getConnection(url,userName,userPassword);
PreparedStatement pt = con.prepareStatement(myTable);
pt.setString(1,"SELF");
pt.setString(2,am);
pt.setString(3,strDate);
pt.executeUpdate();
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}


   }
}		