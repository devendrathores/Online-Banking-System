import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AfterLogin extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();

HttpSession s = req.getSession();
String name=(String)s.getAttribute("uname");
    out.println("<html>");
	out.println("<head><title>Afterlogin</title>");
	out.println("<link rel='stylesheet' href='style7.css'>");
	out.println("</head>");
	out.println("<body>");
	out.println("<header>");
	out.println("<img src='images/indian-bank-logo.png' class='logo'>");
	out.println("</header>");
	out.println("<nav><ul>");
	out.println("<li><a class='homered' href='afterlogin'>Home</a>");
	out.println("<li><a class='homeblack' href='transfer.html'>Transfer</a>");
	out.println("<li><a class='homeblack' href='debit.html'>Debit</a>");
	out.println("<li><a class='homeblack' href='credit.html'>Credit</a>");
	out.println("<li><a class='homeblack' href='checkbalance'>Check Balance</a>");
	out.println("<li><a class='homeblack' href='transactionhistory'>Transaction History</a>");
	out.println("<li><a class='homeblack' href='changepassword.html'>Change Password</a>");
	out.println("<li><a class='homeblack' href='logout'>LogOut</a>");
	out.println("</ul></nav>");
	out.println("<div class='divider'></div>");
	out.println("<div class='image'>");
	out.println("<br><br><br><br><br><br><br>");
	out.println("<div class='welcome'>Hi "+name+" !!</div><br><br>");
	out.println("<center>");
	out.println("<h3>Indian Bank Welcomes You in the World Of Banking.</h3>");
	out.println("</center>");
	out.println("</div>");
	out.println("</body>");
	out.println("</html>");
	
	}
}	