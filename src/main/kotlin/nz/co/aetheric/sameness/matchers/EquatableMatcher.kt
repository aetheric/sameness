package nz.co.aetheric.sameness.matchers

import nz.co.aetheric.sameness.Equatator
import org.hamcrest.CustomMatcher
import org.hamcrest.Description

import javax.validation.constraints.NotNull
import java.util.Objects

/**
 * Matcher to check against an arbitrary predicate or comparison.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class EquatableMatcher<T> : CustomMatcher<T> {

	companion object {

		fun <T> equatableTo(reference: T, equatator: Equatator<T>)
				= EquatableMatcher(reference, equatator)

	}

	private val reference: T
	private val equatator: Equatator<T>

	@Suppress("ConvertSecondaryConstructorToPrimary")
	private constructor(reference: T, equatator: Equatator<T>) : super(Objects.toString(reference)) {
		this.reference = reference
		this.equatator = equatator
	}

	override fun matches(item: Any?)
			= (item == null || equatator.type.isAssignableFrom(item.javaClass))
			&& equatator.isEquatable(reference, equatator.type.cast(item))

	override fun describeMismatch(item: Any, mismatchDescription: Description) {
		mismatchDescription
				.appendText("Provided ")
				.appendValue(item)
				.appendText(" should be equatable to ")
				.appendValue(reference)
	}

}
