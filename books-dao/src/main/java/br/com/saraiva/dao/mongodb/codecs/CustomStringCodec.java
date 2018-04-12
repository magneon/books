package br.com.saraiva.dao.mongodb.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class CustomStringCodec implements Codec<String> {

	@Override
	public void encode(BsonWriter writer, String value, EncoderContext context) {
		writer.writeString(value);
	}

	@Override
	public Class<String> getEncoderClass() {
		return String.class;
	}

	@Override
	public String decode(BsonReader reader, DecoderContext context) {
		return reader.readString();
	}

}
