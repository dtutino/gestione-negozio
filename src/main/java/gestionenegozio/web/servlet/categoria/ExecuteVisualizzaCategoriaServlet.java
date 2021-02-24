package gestionenegozio.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import gestionenegozio.model.Categoria;
import gestionenegozio.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaCategoriaServlet")
public class ExecuteVisualizzaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteVisualizzaCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoriaParam = request.getParameter("idCategoria");

		if (!NumberUtils.isCreatable(idCategoriaParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Categoria registaInstance = MyServiceFactory.getCategoriaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idCategoriaParam));

			if (registaInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListCategoriaServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("show_categoria_attr", registaInstance);
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/categoria/show.jsp").forward(request, response);
	}

}
