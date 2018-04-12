package br.com.saraiva.dao.mongodb.codecs;

import java.math.BigDecimal;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.Decimal128;

public class CustomBigDecimalCodec implements Codec<BigDecimal> {

	@Override
	public void encode(BsonWriter writer, BigDecimal value, EncoderContext context) {
		writer.writeDecimal128(Decimal128.parse(value.toString()));
	}

	@Override
	public Class<BigDecimal> getEncoderClass() {
		return BigDecimal.class;
	}

	@Override
	public BigDecimal decode(BsonReader reader, DecoderContext context) {
		return reader.readDecimal128().bigDecimalValue();
	}

}
