package br.com.saraiva.negocio.ejb;

import java.util.List;

import br.com.saraiva.commons.exception.ProdutoJaExisteException;
import br.com.saraiva.commons.models.BookResponse;
import br.com.saraiva.dao.models.Book;

public interface BookService {

	public void gravaLivroNoBanco(BookResponse bookResponse) throws ProdutoJaExisteException;
	
	public void removeLivroDoBanco(Integer sku);

	public Book procuraLivroPor(Integer sku);

	public List<Book> procuraLivrosPor(Integer price, Integer limit);

}
