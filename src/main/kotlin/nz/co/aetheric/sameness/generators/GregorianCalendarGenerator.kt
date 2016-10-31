package nz.co.aetheric.sameness.generators

import com.pholser.junit.quickcheck.generator.GenerationStatus
import com.pholser.junit.quickcheck.generator.Generator
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.generator.java.util.DateGenerator
import com.pholser.junit.quickcheck.generator.java.util.TimeZoneGenerator
import com.pholser.junit.quickcheck.internal.generator.GeneratorRepository
import com.pholser.junit.quickcheck.internal.generator.ServiceLoaderGeneratorSource
import com.pholser.junit.quickcheck.random.SourceOfRandomness

import java.util.GregorianCalendar

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class GregorianCalendarGenerator : Generator<GregorianCalendar>(GregorianCalendar::class.java) {

	private val zones = TimeZoneGenerator()
	private val dates = DateGenerator()

	fun configure(range: InRange)
			= dates.configure(range)

	override fun generate(random: SourceOfRandomness, status: GenerationStatus): GregorianCalendar {
		val calendar = GregorianCalendar()
		calendar.timeZone = zones.generate(random, status)
		calendar.time = dates.generate(random, status)
		return calendar
	}

}
