package org.test.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletController
 */
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mainCSS = request.getContextPath() + "/public/css/main.css";
		request.setAttribute("mainCSS", mainCSS);

		String validationJS = request.getContextPath() + "/public/javascript/validate.js";
		request.setAttribute("validationJS", validationJS);
		getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mainCSS = request.getContextPath() + "/public/css/main.css";
		request.setAttribute("mainCSS", mainCSS);
		
		Matrix matrix = new Matrix();
		 
		if (validateAndSafe(matrix, request)) {
			request.setAttribute("matrix", matrix);

			//DataBase db = new DataBase();
			//String result = db.getLog();
			//request.setAttribute("result", result);
			getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
		} else {
			System.err.println("Error: los parámetros no están completos!");
			//getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
		}
	}

	private boolean validateAndSafe(Matrix matrix, HttpServletRequest request) {
		String data = null;
		String size = request.getParameter("size");
		String selection = request.getParameter("selection");
		if (selection.equalsIgnoreCase("letras"))
			data = request.getParameter("matrix_b");
		else
			data = request.getParameter("matrix_a");
		String operation = request.getParameter("operation");

		if (size != null && data != null && operation != null) {
			matrix.setSize(size);
			matrix.setData(data);
			matrix.setOperation(operation);

			String result = preformOperation(operation, matrix);
			matrix.setResultado(result);

			//System.out.println("El resuldado en el controller es: " + matrix.getResultado());
			//DataBase db = new DataBase();
			//db.setLog(matrix);
			return true;
		} else return false;
	}
	
	private String preformOperation(String operation, Matrix matrix) {
		if(operation.equalsIgnoreCase("ocurrencias"))
			return matrix.countOcurrency();
		else if(operation.equalsIgnoreCase("repetidos"))
			return matrix.removeDuplicates();
		else
			return matrix.sortAsc();
	}

}
