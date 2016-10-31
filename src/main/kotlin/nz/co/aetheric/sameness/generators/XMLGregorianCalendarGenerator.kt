package nz.co.aetheric.sameness.generators

import com.pholser.junit.quickcheck.generator.GenerationStatus
import com.pholser.junit.quickcheck.generator.Generator
import com.pholser.junit.quickcheck.generator.InRange
import com.pholser.junit.quickcheck.random.SourceOfRandomness

import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class XMLGregorianCalendarGenerator() : Generator<XMLGregorianCalendar>(XMLGregorianCalendar::class.java) {

	private val calendars = GregorianCalendarGenerator()
	private val datatypeFactory: DatatypeFactory = DatatypeFactory.newInstance()

	fun configure(range: InRange)
			= calendars.configure(range)

	override fun generate(random: SourceOfRandomness, status: GenerationStatus)
			= datatypeFactory.newXMLGregorianCalendar(calendars.generate(random, status))

}
