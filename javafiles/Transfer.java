import java.sql.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Transfer extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();
/*FETCHING USER INPUT DETAILS*/
int balance=0;
int partyBalance=0;

String myAccount="";
HttpSession s = req.getSession();
String name=(String)s.getAttribute("uname");
String password=(String)s.getAttribute("upassword");

String accountNo=req.getParameter("Accountnum");
String ReaccountNo=req.getParameter("ReAccountnum");
int amount=Integer.parseInt(req.getParameter("amount"));

String a=String.valueOf(amount);
String am="-"+a;
String pm="+"+a;
Date date=Calendar.getInstance().getTime();
DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy hh:mm EEE");
String strDate=dateFormat.format(date);
//IF BOTH ACCOUNT NUMBER ARE EQUAL(1)
if(accountNo.equals(ReaccountNo)){

	
      String userName="system";
	  String userPassword="oracle";
	  String driverName="oracle.jdbc.driver.OracleDriver";
	  String url="jdbc:oracle:thin:@localhost:1521:xe";
	  String sql="select ACCOUNT_NO from USER_NEW_ACCOUNT where ACCOUNT_NO=?";
      try{
	  Class.forName(driverName);
	  Connection conn = DriverManager.getConnection(url,userName,userPassword);
	  PreparedStatement pst = conn.prepareStatement(sql);
	  pst.setString(1,accountNo);
	  ResultSet rs = pst.executeQuery();	  
//IF ACCOUNT NUMBER EXIST(2)	  
	  if(rs.next()==true){
	   String Sql="select*from user_new_account where USERNAME=? AND PASSWORD=?";
       try{
	   Class.forName(driverName);
	   Connection conn1 =DriverManager.getConnection(url,userName,userPassword);
	   PreparedStatement Pst = conn1.prepareStatement(Sql);
	   Pst.setString(1,name);
	   Pst.setString(2,password);
	   ResultSet rs1 = Pst.executeQuery();
	   if(rs1.next()==true){balance=rs1.getInt("BALANCE");
	   myAccount=rs1.getString("ACCOUNT_NO");}
       }catch(ClassNotFoundException e){out.println(e);}
       catch(SQLException e){out.println(e);}		  
	   String sQl="select BALANCE from user_new_account where ACCOUNT_NO=?";
       try{
	   Class.forName(driverName);
	   Connection conn2 =DriverManager.getConnection(url,userName,userPassword);
	   PreparedStatement pSt = conn2.prepareStatement(sQl);
	   pSt.setString(1,accountNo);
	   ResultSet rs2 = pSt.executeQuery();
	   if(rs2.next()==true){partyBalance=rs2.getInt("BALANCE");}
       }catch(ClassNotFoundException e){out.println(e);}
       catch(SQLException e){out.println(e);}
//IF BALANCE IS THEIR FOR TRANSACTION(3)	   
	   if(balance>=amount){
	    balance=balance-amount;
        String sqL="update user_new_account set BALANCE=? where USERNAME=? AND PASSWORD=?";
        try{
	    Class.forName(driverName);
	    Connection conn3 =DriverManager.getConnection(url,userName,userPassword);
	    PreparedStatement psT = conn3.prepareStatement(sqL);
	    psT.setInt(1,balance);
	    psT.setString(2,name);
	    psT.setString(3,password);
	    psT.executeUpdate();
        }catch(ClassNotFoundException e){out.println(e);}
        catch(SQLException e){out.println(e);}
        
		partyBalance=partyBalance+amount; 
		String SQL="update user_new_account set BALANCE=? where ACCOUNT_NO=?"; 
        try{
	    Class.forName(driverName);
	    Connection conn4 =DriverManager.getConnection(url,userName,userPassword);
	    PreparedStatement PST = conn4.prepareStatement(SQL);
	    PST.setInt(1,partyBalance);
	    PST.setString(2,accountNo);
	    PST.executeUpdate();
        }catch(ClassNotFoundException e){out.println(e);}
        catch(SQLException e){out.println(e);}
//TRANSACTION HISTORY		
String myTable="insert into H"+myAccount+" values(?,?,?)";
try{
Class.forName(driverName);
Connection con =DriverManager.getConnection(url,userName,userPassword);
PreparedStatement pt = con.prepareStatement(myTable);
pt.setString(1,"TRANSFER");
pt.setString(2,am);
pt.setString(3,strDate);
pt.executeUpdate();
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}


String partyTable="insert into H"+accountNo+" values(?,?,?)";
try{
Class.forName(driverName);
Connection CON =DriverManager.getConnection(url,userName,userPassword);
PreparedStatement PT = CON.prepareStatement(partyTable);
PT.setString(1,myAccount);
PT.setString(2,pm);
PT.setString(3,strDate);
PT.executeUpdate();
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}
//TRANSACTION HISTORY		
      }//(3)
//IF BALANCE IS NOT THERE FOR TRANSACTION(3)	   
	  else if(balance<amount){
	    RequestDispatcher disp = req.getRequestDispatcher("transfer.html");
	    disp.include(req,res);
	    out.println("<html>");
	    out.println("<body onload='myFunction()'>");
	    out.println("<script>");
	    out.println("function myFunction(){alert('Account Balance is Insufficient to complete Transaction !!');}");
	    out.println("</script>");
	    out.println("</body>");
	    out.println("</html>");  
	   }
	   
	   
     }//(2) 
//IF ACCOUNT NUMBER DOESN'T EXIST	  
	  else if(rs.next()!=true){
	   RequestDispatcher dispp = req.getRequestDispatcher("transfer.html");
	   dispp.include(req,res);
	   out.println("<html>");
	   out.println("<body onload='myFunction()'>");
	   out.println("<script>");
	   out.println("function myFunction(){alert('Account Number does not exist !!');}");
	   out.println("</script>");
	   out.println("</body>");
	   out.println("</html>");	
	   }
	   
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}  
	   RequestDispatcher disppp = req.getRequestDispatcher("transfer.html");
	   disppp.include(req,res);
	   out.println("<html>");
	   out.println("<body onload='myFunction()'>");
	   out.println("<script>");
	   out.println("function myFunction(){alert('Amount Transfer Successfully !!');}");
	   out.println("</script>");
	   out.println("</body>");
	   out.println("</html>");
	  
 }//(1)
//IF BOTH ACCOUNT NUMBER ARE NOT EQUAL (1)
else{
	RequestDispatcher dispppp = req.getRequestDispatcher("transfer.html");
	  dispppp.include(req,res);
	  out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('Account Number does not matched with the Second One !!');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");	
}


	}
}	