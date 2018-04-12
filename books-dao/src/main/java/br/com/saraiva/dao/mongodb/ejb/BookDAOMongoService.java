package br.com.saraiva.dao.mongodb.ejb;

import java.util.List;

import br.com.saraiva.dao.models.Book;

public interface BookDAOMongoService {

	public List<Book> pegaLivros();

	public void adicionaLivro(Book book);

	public Book pegaLivroPor(Integer sku);

	public List<Book> pegaLivrosMenorQue(Double preco);

	public void removeLivroPor(Integer sku);

	public long atualizaLivro(Book book);

}
