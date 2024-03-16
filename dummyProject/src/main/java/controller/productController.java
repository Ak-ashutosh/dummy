package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.productDTO;

@WebServlet("/addProductLink")
public class productController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productName = req.getParameter("productName");
		double productprice = Double.parseDouble(req.getParameter("productPrice"));
		String productCategory = req.getParameter("productCategory");
		int productQty = Integer.parseInt(req.getParameter("productQty"));
		
		productDTO dto = new productDTO();
		dto.setProductName(productName);
		dto.setProductPrice(productprice);
		dto.setProductCategory(productCategory);
		dto.setProductQty(productQty);
	
		Session session =  new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(productDTO.class).buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();
	}
}
