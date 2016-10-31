package nz.co.aetheric.sameness.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class XMLGregorianCalendarGenerator extends Generator<XMLGregorianCalendar> {

	private final GregorianCalendarGenerator calendars = new GregorianCalendarGenerator();
	private final DatatypeFactory datatypeFactory;

	public XMLGregorianCalendarGenerator() throws DatatypeConfigurationException {
		super(XMLGregorianCalendar.class);
		datatypeFactory = DatatypeFactory.newInstance();
	}

	public void configure(InRange range) {
		calendars.configure(range);
	}

	@Override
	public XMLGregorianCalendar generate(SourceOfRandomness random, GenerationStatus status) {
		return datatypeFactory.newXMLGregorianCalendar(calendars.generate(random, status));
	}

}
