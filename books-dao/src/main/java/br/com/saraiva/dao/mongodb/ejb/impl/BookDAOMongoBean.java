package br.com.saraiva.dao.mongodb.ejb.impl;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.saraiva.dao.models.Book;
import br.com.saraiva.dao.mongodb.ejb.BookDAOMongoService;
import br.com.saraiva.dao.mongodb.providers.BookCodecProvider;

@Stateless
public class BookDAOMongoBean implements BookDAOMongoService {
	
	private CodecRegistry registry;
	
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Book> bookCollection;

	public BookDAOMongoBean() {
		registry = CodecRegistries.fromRegistries(CodecRegistries.fromProviders(new BookCodecProvider()), MongoClient.getDefaultCodecRegistry());
		
		client = new MongoClient("localhost", 27017);
		database = client.getDatabase("saraiva");
		
		bookCollection = database.getCollection("book", Book.class).withCodecRegistry(registry);
	}
	
	@Override
	public List<Book> pegaLivros() {
		List<Book> livros = new ArrayList<Book>();
		Block<Book> bookBlock = new Block<Book>() {

			@Override
			public void apply(final Book book) {
				livros.add(book);
			}
		};
		
		bookCollection.find(Book.class).forEach(bookBlock);
		
		return livros;
	}

	@Override
	public void adicionaLivro(Book book) {
		bookCollection.insertOne(book);
	}

	@Override
	public Book pegaLivroPor(Integer sku) {
		return bookCollection.find(eq("sku", sku)).first();
	}

	@Override
	public List<Book> pegaLivrosMenorQue(Double preco) {
		List<Book> books = new ArrayList<Book>();
		
		Block<Book> block = new Block<Book>() {

			@Override
			public void apply(Book book) {
				books.add(book);
			}
		};
		
		bookCollection.find(lt("price", preco)).forEach(block);
		
		return books;
	}

	@Override
	public void removeLivroPor(Integer sku) {
		bookCollection.deleteOne(eq("sku", sku));
	}

	@Override
	public long atualizaLivro(Book book) {
		return bookCollection.updateOne(eq("sku", book.getSku()), combine(set("name", book.getName()), set("brand", book.getBrand()), set("price", book.getPrice().toString()))).getModifiedCount();
	}
	
}