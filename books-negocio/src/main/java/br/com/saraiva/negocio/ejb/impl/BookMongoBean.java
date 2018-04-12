package br.com.saraiva.negocio.ejb.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.saraiva.dao.models.Book;
import br.com.saraiva.dao.mongodb.ejb.BookDAOMongoService;
import br.com.saraiva.negocio.ejb.BookMongoService;

@Stateless
public class BookMongoBean implements BookMongoService {
	
	@EJB
	private BookDAOMongoService serviceBookDAOMongo;

	@Override
	public List<Book> pegaLivros() {
		return serviceBookDAOMongo.pegaLivros();
	}

	@Override
	public void adicionaLivro(Book book) {
		serviceBookDAOMongo.adicionaLivro(book);
	}

	@Override
	public Book pegaLivroPor(Integer sku) {
		return serviceBookDAOMongo.pegaLivroPor(sku);
	}

	@Override
	public List<Book> pegaLivrosMenorQue(Double preco) {
		return serviceBookDAOMongo.pegaLivrosMenorQue(preco);
	}

	@Override
	public void removeLivroPor(Integer sku) {
		serviceBookDAOMongo.removeLivroPor(sku);
	}

	@Override
	public long atualizaLivro(Book book) {
		return serviceBookDAOMongo.atualizaLivro(book);
	}

}
