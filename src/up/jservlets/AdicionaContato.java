package up.jservlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import up.jservlets.bean.Pessoa;
import up.jservlets.dao.PessoaDAO;

/**
 * Servlet implementation class AdicionaContato
 */
@WebServlet("/AdicionaContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection dbConnection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionaContato() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		System.out.println(config.getServletName() + " : Initializing...");

		ServletContext context = getServletContext();

		String driverClassName = context.getInitParameter("driverclassname");

		String dbURL = context.getInitParameter("dburl");

		String username = context.getInitParameter("username");

		String password = context.getInitParameter("password");

		// Load the driver class
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// get a database connection
		try {
			dbConnection = DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Initialized " + dbConnection.toString());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		Pessoa pessoa;
		PessoaDAO dao = new PessoaDAO();
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if (action.equals("Pesquisar")) {
			String mensagemErro = "";
			try {
				if (email != null) {
					File archpessoa = new File("C:\\temp\\" + email);

					if (archpessoa.exists()) {
						dao.setArchive(email);
						pessoa = (Pessoa) dao.obtemPessoa();
						System.out.println("Contato carregado com sucesso");
						request.setAttribute("nome", pessoa.getNome());
						request.setAttribute("endere�o", pessoa.getEndereco());
						request.setAttribute("email", pessoa.getEmail());
						request.setAttribute("dataNascimento", pessoa.getDataNascimento());
					} else
						mensagemErro = "Contato n�o encontrado";
				} else {
					mensagemErro = "Parametro inv�lido, execute <URL servlet>?email=<nome do email para carregar informa��es>";
				}
			} catch (Exception e) {
				mensagemErro = "Contato n�o encontrado";
				e.printStackTrace();
			}

			request.setAttribute("mensageErro", mensagemErro);
			request.getRequestDispatcher("ResultPesquisa.jsp").forward(request, response);
		}

		if (action.equals("Excluir")) {
			out.println("<html>");
			out.println("<body>");

			try {
				if (email != null) {
					File archpessoa = new File("C:\\temp\\" + email);

					if (archpessoa.exists()) {
						if (archpessoa.delete())
							out.println("<br>Contato " + email + " excluido com sucesso");
						else
							out.println("<br>Falha na Exclus�o do Contato " + email + " contate o administrador");
					}
				} else {
					out.println("Chamada Invi�lida ao mecanismo de exclus�o, contate o administrador");
				}
			} catch (Exception e) {
				out.println("Contato n�o encontrado");
				e.printStackTrace();
			}

			out.println("</body>");
			out.println("</html>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session.getAttribute("userid") == null) {
			response.sendRedirect("UsuarioNaoLogado.html");
			return;
		}

		String action = request.getParameter("action");

		if (action.equals("Pesquisar") || action.equals("Excluir")) {
			doGet(request, response);
			return;
		}

		if (action.equals("Gravar")) {
			// pegando os par�metros do request
			String nome = request.getParameter("nome");
			String endereco = request.getParameter("endereco");
			String email = request.getParameter("email");
			String dataNascimento = request.getParameter("dataNascimento");

			Pessoa pessoa = new Pessoa();
			pessoa.setNome(nome);
			pessoa.setEndereco(endereco);
			pessoa.setEmail(email);
			pessoa.setDataNascimento(dataNascimento);

			out.println("<html>");
			out.println("<body>");

			try {
				serializaObjeto("C:\\temp\\" + email, pessoa);
				out.println("Contato " + pessoa.getNome() + " adicionado com sucesso");
			} catch (Exception e) {
				out.println("Problemas no adicionamento do contato, contate o administrador");
				e.printStackTrace();
			}

			out.println("</body>");
			out.println("</html>");
		}

		if (action.equals("Listar")) {
			request.getRequestDispatcher("ListaContatos.jsp").forward(request, response);
		}
	}

	public void serializaObjeto(String path, Object obj) throws Exception {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}

	public Object deserializaObjeto(String path) throws Exception {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object result = ois.readObject();
		ois.close();

		return result;
	}
}