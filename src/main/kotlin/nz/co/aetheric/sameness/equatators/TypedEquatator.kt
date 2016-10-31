package nz.co.aetheric.sameness.equatators

import nz.co.aetheric.sameness.Equatator

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
abstract class TypedEquatator<T>(override val type: Class<T>) : Equatator<T> {

	abstract override fun <L : T, R : T> isEquatable(left: L?, right: R?): Boolean

	fun isApplicable(other: Any)
			= type.isAssignableFrom(other.javaClass)

	fun isApplicable(left: Any, right: Any)
			= isApplicable(left) && isApplicable(right)

	fun asApplicable(other: Any)
			= type.cast(other)

	fun attemptEquatable(left: Any, right: Any)
			= isEquatable(asApplicable(left), asApplicable(right))

}
