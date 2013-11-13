package com.ociweb.jfast.stream;

import com.ociweb.jfast.BytesSequence;
import com.ociweb.jfast.FASTAccept;
import com.ociweb.jfast.field.FieldWriterInteger;

import static com.ociweb.jfast.field.TypeMask.*;
import static com.ociweb.jfast.field.OperatorMask.*;

//May drop interface if this causes a performance problem from virtual table 
public final class FASTStreamer implements FASTAccept {
	
	private final FieldWriterInteger writerInteger;
	//writerLong
	//writerText
	//writerByteArray
	//writerDecimal
	
	
	private final int[] tokenLookup; //array of tokens as field id locations
	
	private final int MASK = 0x3FF;
	private final int INST = 20;
	//32 bits total
	//two high bits set
	//  6 bit type (must match method)
	//  4 bit operation (must match method)
	// 20 bit instance (MUST be lowest for easy mask and frequent use)
	
	//TODO: replace FieldWriter with this class in FASTStreamable!!!
	//NOTE: then what does reader get? bring back provider?
	//      may be best because then read/write compression is kept in sync.
	//      plus this class will be the only one needing token lookup.
	
	public FASTStreamer(FieldWriterInteger writer, int[] tokenLookup) {
		this.writerInteger = writer;
		this.tokenLookup = tokenLookup;
	}
	
	//Can a Fix ID be in the same template with different compression operations
	//can two different fix ids share the same "previous" value.?
	
	@Override
	public void accept(int id, long value) {
		

		
		
		
		//the id better match the "next" field in the sate machine
		
		//use a static Id per field?
		//does the caller pass in the field id from spec our our generated id?
		//it depends on uniqueness
		//what if we always use our internal unique id but it can 
		//be used to easily lookup the field id etc.
		
		//3 bits for operator with one potential future value
		//4 bits for type with nullability (may want extra bit for new data format)
		//8 bits (256) unique values per type? really feels too small. need 80K or so 17 bits.		
		//16 bits for FIX id unless it is not used.
		
		//top bit set to 1 to ensure FIX id is not passed in as type check on int
		//4 bits operator
		//5 bits type with nullability
		//22 bits for unique last value id for this field 4M should be enough, can lookup field id if needed.
		
		//mask operator and type toghter for switch.
		//inside each block it can use the other 22 to index last as needed.
		
		//type and id will let us lookup the sequenceId from another 2d table.
		
	}

	@Override
	public void accept(int id, int value) {
		int token = id>=0 ? tokenLookup[id] : id;
		
		switch ((token>>INST)&MASK) {
			case (IntegerUnSigned<<4)|None:
				//writer.writeIntegerUnsigned
				break;
			case (IntegerSigned<<4)|None:
				writerInteger.writeIntegerSigned(value, token);
				break;
			case (IntegerUnSignedOptional<<4)|None:
				//writer.writer
				break;
			case (IntegerSignedOptional<<4)|None:
				writerInteger.writeIntegerSignedOptional(value, token);
				break;
			
			
			default:
				break;
		}
		//int type = token>>20
	}

	@Override
	public void accept(int id, int exponent, long manissa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(int id, BytesSequence value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(int id, CharSequence value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(int id) {
		int token = id>=0 ? tokenLookup[id] : id;
		switch ((token>>INST)&MASK) {
			case (IntegerUnSignedOptional<<4)|None:
				//writer.writer
				break;
			case (IntegerSignedOptional<<4)|None:
				writerInteger.writeIntegerSignedOptional(token);
				break;
			
			
			default:
				break;
		}
	}

}