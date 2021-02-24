package gestionenegozio.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionenegozio.model.Categoria;
import gestionenegozio.service.MyServiceFactory;
import gestionenegozio.utility.UtilityForm;

@WebServlet("/ExecuteInsertCategoriaServlet")
public class ExecuteInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertCategoriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("descrizione");
		String cognomeParam = request.getParameter("codice");

		if (!UtilityForm.validateCategoriaFormInput(nomeParam, cognomeParam)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/categoria/insert.jsp").forward(request, response);
			return;
		}

		Categoria categoriaInstance = new Categoria(nomeParam, cognomeParam);
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getCategoriaServiceInstance().inserisciNuovo(categoriaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/categoria/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListCategoriaServlet?operationResult=SUCCESS");
	}

}
