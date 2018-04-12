package br.com.saraiva.dao.mongodb.providers;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import br.com.saraiva.dao.models.Book;
import br.com.saraiva.dao.mongodb.codecs.BookCodec;

public class BookCodecProvider implements CodecProvider {

	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry codecRegistry) {
		if (clazz == Book.class) {
			return (Codec<T>) new BookCodec(codecRegistry);
		}
		
		return null;
	}

}