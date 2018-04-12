package br.com.saraiva.ws.client.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saraiva.dao.models.Book;
import br.com.saraiva.negocio.ejb.BookMongoService;

@Stateless
@Path("/saraiva/mongo")
public class SaraivaMongoClientBean {
	
	@EJB
	private BookMongoService serviceMongoBook;
	
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Book> pegaLivros() {
		return serviceMongoBook.pegaLivros();
	}
	
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public void adicionaLivro(Book book) {
		serviceMongoBook.adicionaLivro(book);
	}
	
	@GET
	@Path("/porSku/{sku}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Book pegaLivroPor(@PathParam("sku") Integer sku) {
		return serviceMongoBook.pegaLivroPor(sku);
	}
	
	@GET
	@Path("/porPreco/{preco}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Book> pegaLivrosMenorQue(@PathParam("preco") Double preco) {
		return serviceMongoBook.pegaLivrosMenorQue(preco);
	}
	
	@DELETE
	@Path("/{sku}")
	public void removeLivroPor(@PathParam("sku") Integer sku) {
		serviceMongoBook.removeLivroPor(sku);
	}
	
	@PUT
	public Response atualizaLivro(Book book) {
		long updatedLines = serviceMongoBook.atualizaLivro(book);
		
		if (updatedLines > 0) {
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}

}