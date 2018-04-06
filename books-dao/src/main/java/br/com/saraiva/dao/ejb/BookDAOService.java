package br.com.saraiva.dao.ejb;

import java.util.List;

import br.com.saraiva.dao.models.Book;

public interface BookDAOService {

	void salva(Book book);

	void remove(Integer sku);

	Book pegaLivroPor(Integer sku);

	List<Book> procuraLivrosPor(Integer price, Integer limit);

}
