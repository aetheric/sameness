package nz.co.aetheric.sameness.generators;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.java.util.DateGenerator;
import com.pholser.junit.quickcheck.generator.java.util.TimeZoneGenerator;
import com.pholser.junit.quickcheck.internal.generator.GeneratorRepository;
import com.pholser.junit.quickcheck.internal.generator.ServiceLoaderGeneratorSource;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.GregorianCalendar;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class GregorianCalendarGenerator extends Generator<GregorianCalendar> {

	private final TimeZoneGenerator zones = new TimeZoneGenerator();
	private final DateGenerator dates = new DateGenerator();

	public GregorianCalendarGenerator() {
		super(GregorianCalendar.class);
	}

	public void configure(InRange range) {
		dates.configure(range);
	}

	@Override
	public GregorianCalendar generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeZone(zones.generate(sourceOfRandomness, generationStatus));
		calendar.setTime(dates.generate(sourceOfRandomness, generationStatus));
		return calendar;
	}

}
