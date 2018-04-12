package br.com.saraiva.dao.mongodb.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.Decimal128;

import br.com.saraiva.dao.models.Book;

public class BookCodec implements Codec<Book>{

	private CodecRegistry codecRegistry;

	public BookCodec(CodecRegistry codecRegistry) {
		this.codecRegistry = codecRegistry;
	}

	@Override
	public void encode(BsonWriter writer, Book book, EncoderContext context) {
		writer.writeStartDocument();
		
		writer.writeInt32("sku", book.getSku());
		writer.writeString("name", book.getName());
		writer.writeString("brand", book.getBrand());
		writer.writeDecimal128("price", Decimal128.parse(book.getPrice().toString()));
		
		writer.writeEndDocument();
	}

	@Override
	public Class<Book> getEncoderClass() {
		return Book.class;
	}

	@Override
	public Book decode(BsonReader reader, DecoderContext context) {		
		reader.readStartDocument();
		reader.readObjectId();
		
		Book book = new Book();
		book.setSku(reader.readInt32("sku"));
		book.setName(reader.readString("name"));
		book.setBrand(reader.readString("brand"));
		book.setPrice(reader.readDecimal128("price").bigDecimalValue());
		
		reader.readEndDocument();
		
		return book;
	}
	
	public CodecRegistry getCodecRegistry() {
		return codecRegistry;
	}
	
}
