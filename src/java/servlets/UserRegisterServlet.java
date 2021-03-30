package servlets;

import constants.IOnlineBookStoreConstants;
import java.sql.*;
import javax.servlet.*;

import sql.IUserContants;

import java.io.*;

public class UserRegisterServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);

		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		String fName = req.getParameter(IUserContants.COLUMN_FIRSTNAME);
		String lName = req.getParameter(IUserContants.COLUMN_LASTNAME);
		String addr = req.getParameter(IUserContants.COLUMN_ADDRESS);
		String phNo = req.getParameter(IUserContants.COLUMN_PHONE);
		String mailId = req.getParameter(IUserContants.COLUMN_MAILID);
                System.out.print(uName+" "+mailId+" "+pWord+" "+fName+" "+lName+" "+phNo);
		try {
			Connection con = DBConnection.getCon();
                        System.out.println("dfghjgfd");
			PreparedStatement ps = con
					.prepareStatement("insert into registeration values(?,?,?,?,?,?,?)");
                        System.out.println("dfghjgfd");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ps.setString(3, fName);
			ps.setString(4, lName);
			ps.setString(5, addr);
			ps.setString(6, phNo);
			ps.setString(7, mailId);
			//ps.setInt(8, 2);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully</h3>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Sorry for interruption! Register again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}