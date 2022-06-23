import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class TransactionHistory extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out=res.getWriter();

HttpSession s = req.getSession();
String name=(String)s.getAttribute("uname");
String password=(String)s.getAttribute("upassword");
String myAccount="";
 
String userName="system";
String userPassword="oracle";
String driverName="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:xe";
String sql="select ACCOUNT_NO from user_new_account where USERNAME=? AND PASSWORD=?";
try{
	Class.forName(driverName);
	Connection conn =DriverManager.getConnection(url,userName,userPassword);
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1,name);
	pst.setString(2,password);
	ResultSet rs = pst.executeQuery();
	if(rs.next()==true){
	myAccount=rs.getString("ACCOUNT_NO");	
	}
}catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}	

        String myTable="select*from H"+myAccount;
		try{
		Class.forName(driverName);
		Connection con =DriverManager.getConnection(url,userName,userPassword);
		PreparedStatement pt = con.prepareStatement(myTable);
		ResultSet rs=pt.executeQuery();

    out.println("<html>");
	out.println("<head><title>Afterlogin</title>");
	out.println("<link rel='stylesheet' href='style7.css'>");
	out.println("</head>");
	out.println("<body>");
	out.println("<header>");
	out.println("<img src='images/indian-bank-logo.png' class='logo'>");
	out.println("</header>");
	out.println("<nav><ul>");
	out.println("<li><a class='homeblack' href='afterlogin'>Home</a>");
	out.println("<li><a class='homeblack' href='transfer.html'>Transfer</a>");
	out.println("<li><a class='homeblack' href='debit.html'>Debit</a>");
	out.println("<li><a class='homeblack' href='credit.html'>Credit</a>");
	out.println("<li><a class='homeblack' href='checkbalance'>Check Balance</a>");
	out.println("<li><a class='homered' href='transactionhistory'>Transaction History</a>");
	out.println("<li><a class='homeblack' href='changepassword.html'>Change Password</a>");
	out.println("<li><a class='homeblack' href='logout'>LogOut</a>");
	out.println("</ul></nav>");
	out.println("<div class='divider'></div>");
	out.println("<div class='image'>");
	out.println("<br><br><br><br><br><br><br>");
	out.println("<center>");
	out.println("<table border=1px bgcolor='white'>");
	out.println("<tr bgcolor='yellow'>");
	out.println("<td>ACCOUNT</td>");
	out.println("<td>AMOUNT</td>");
	out.println("<td>TIME</td>");
	out.println("</tr>");
	while(rs.next()==true) {
        out.println("<tr>");
        out.println("<td>"+rs.getString("ACCOUNT")+"</td>");
        out.println("<td>"+rs.getString("AMOUNT")+"</td>");	
        out.println("<td>"+rs.getString("TIME")+"</td>");
        out.println("</tr>");		
		}
		out.print("</table>");
	out.println("</center>");
	out.println("</div>");
	out.println("</body>");
	out.println("</html>");
      
	  }catch(ClassNotFoundException e){System.out.println(e);}
	  catch(SQLException e){System.out.println(e);}	
	}
}	