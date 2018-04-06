package br.com.saraiva.dao.ejb.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.saraiva.dao.ejb.BookDAOService;
import br.com.saraiva.dao.models.Book;

@Stateless
public class BookDAOBean implements BookDAOService {
	
	@PersistenceContext(unitName = "saraivaPU")
	private EntityManager entityManager;

	@Override
	public void salva(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void remove(Integer sku) {
		Book book = entityManager.find(Book.class, sku);
		
		if (book != null) {
			entityManager.remove(book);
		}
	}

	@Override
	public Book pegaLivroPor(Integer sku) {
		return entityManager.find(Book.class, sku);
	}

	@Override
	public List<Book> procuraLivrosPor(Integer price, Integer limit) {
		TypedQuery<Book> query = null;
		
		if (price != null && limit != null) {
			query = entityManager.createNamedQuery(Book.CONSULTA_LIVROS_ATE_PRECO, Book.class);
			query.setParameter("price", new BigDecimal(price));
			query.setMaxResults(limit);
		} else if (price != null) {
			query = entityManager.createNamedQuery(Book.CONSULTA_LIVROS_ATE_PRECO, Book.class);
			query.setParameter("price", new BigDecimal(price));
		} else if (limit != null) {
			query = entityManager.createNamedQuery(Book.CONSULTA_LIVROS, Book.class);
			query.setMaxResults(limit);
		} else {
			query = entityManager.createNamedQuery(Book.CONSULTA_LIVROS, Book.class);
		}
		
		return query.getResultList();
	}

}
