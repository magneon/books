package br.com.saraiva.ws.client.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import br.com.saraiva.commons.exception.ErroGenericoException;
import br.com.saraiva.commons.exception.ProdutoJaExisteException;
import br.com.saraiva.commons.models.BookResponse;
import br.com.saraiva.dao.models.Book;
import br.com.saraiva.negocio.ejb.BookService;

@Stateless
@Path("/saraiva")
public class SaraivaClientBean {
	
	@EJB
	private BookService serviceBook;

	@POST
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Response pegaLivroDaSaraivaEAdicionaAoBanco(Book book) throws ProdutoJaExisteException, ErroGenericoException {
		BookResponse bookResponse = null;

		ResteasyClient build = new ResteasyClientBuilder().build();

		Response response = build.target(UriBuilder.fromPath("https://api.saraiva.com.br/sc/produto/pdp/"+ book.getSku() +"/0/0/1/")).request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() != 200) {
			System.out.println("Erro!");
		} else {
			bookResponse = response.readEntity(BookResponse.class);
			
			if (bookResponse.getSku() != null) {
			
				try {
					serviceBook.gravaLivroNoBanco(bookResponse);
				} catch (ProdutoJaExisteException produtoJaExiste) {
					throw new ProdutoJaExisteException(produtoJaExiste.getMessage());
				} catch (Exception exception) {
					throw new ErroGenericoException(exception.getMessage());
				}
			
			}
			
		}
		
		return Response.status(201).build();

	}
	
	@DELETE
	@Path("/{sku}")
	public Response removeLivroDoBanco(@PathParam("sku") Integer sku) {
		
		if (sku != null && sku != 0) {
			
			serviceBook.removeLivroDoBanco(sku);
			
		} else {
			
			System.out.println("Erro!");
			
		}
		
		return Response.ok().build();
		
	}
	
	@GET
	@Path("/{sku}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Book consultaLivroNoBanco(@PathParam("sku") Integer sku) {
		Book book = null;
		
		if (sku != null && sku != 0) {
			
			book = serviceBook.procuraLivroPor(sku);
			
		}
		
		return book;
			
	}
	
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Book> consultaLivrosPor(@QueryParam("price") Integer price, @QueryParam("limit") Integer limit) {
		return serviceBook.procuraLivrosPor(price, limit);
	}
	
}