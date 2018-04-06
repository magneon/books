package br.com.saraiva.negocio.ejb;

import java.util.List;

import br.com.saraiva.commons.exception.ProdutoJaExisteException;
import br.com.saraiva.commons.models.BookResponse;
import br.com.saraiva.dao.models.Book;

public interface BookService {

	void gravaLivroNoBanco(BookResponse bookResponse) throws ProdutoJaExisteException;

	void removeLivroDoBanco(Integer sku);

	Book procuraLivroPor(Integer sku);

	List<Book> procuraLivrosPor(Integer price, Integer limit);

}
