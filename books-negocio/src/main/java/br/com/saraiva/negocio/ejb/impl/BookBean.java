package br.com.saraiva.negocio.ejb.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.saraiva.commons.exception.ProdutoJaExisteException;
import br.com.saraiva.commons.models.BookResponse;
import br.com.saraiva.dao.ejb.BookDAOService;
import br.com.saraiva.dao.models.Book;
import br.com.saraiva.negocio.ejb.BookService;

@Stateless
public class BookBean implements BookService {
	
	@EJB
	private BookDAOService serviceBookDAO;
	
	@Override
	public void gravaLivroNoBanco(BookResponse bookResponse) throws ProdutoJaExisteException {
		Book book = serviceBookDAO.pegaLivroPor(Integer.valueOf(bookResponse.getSku()));
		
		if (book == null) {
			book = new Book();
		
			String bookValue = bookResponse.getPrice().getBestPrice().getValue();
			bookValue = bookValue.replace(".", "");
			bookValue = bookValue.replace(",", ".");
			
			book.setSku(Integer.parseInt(bookResponse.getSku()));
			book.setName(bookResponse.getName());
			book.setBrand(bookResponse.getBrand());
			book.setPrice(new BigDecimal(bookValue).setScale(2));
		} else {
			throw new ProdutoJaExisteException("O livro solicitado já existe no banco de dados!");
		}
		
		serviceBookDAO.salva(book);
		
	}
	
	@Override
	public void removeLivroDoBanco(Integer sku) {
		serviceBookDAO.remove(sku);
	}

	@Override
	public Book procuraLivroPor(Integer sku) {
		return serviceBookDAO.pegaLivroPor(sku);
	}

	@Override
	public List<Book> procuraLivrosPor(Integer price, Integer limit) {
		return serviceBookDAO.procuraLivrosPor(price, limit);
	}

}