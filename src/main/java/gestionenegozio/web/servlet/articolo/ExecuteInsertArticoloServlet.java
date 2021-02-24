package gestionenegozio.web.servlet.articolo;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionenegozio.model.Articolo;
import gestionenegozio.service.MyServiceFactory;
import gestionenegozio.utility.UtilityForm;
import gestionenegozio.utility.UtilityParse;

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteInsertArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String prezzoParam = request.getParameter("prezzo");
		String dataInserimentoParam = request.getParameter("dataInserimento");

		Date dataInserimentoParsed = UtilityParse.parseDateArrivoFromString(dataInserimentoParam);
		
		int prezzoParsed = UtilityParse.parsePrezzoFromString(prezzoParam);
		
		if (!UtilityForm.validateArticoloFormInput(nomeParam, prezzoParam, dataInserimentoParam)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/articolo/insert.jsp").forward(request, response);
			return;
		}

		Articolo articoloInstance = new Articolo(nomeParam, prezzoParsed, dataInserimentoParsed);
		
		try {
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListArticoloServlet?operationResult=SUCCESS");
	}

}
