package nz.co.aetheric.sameness

import javax.validation.constraints.NotNull

/**
 * Defines a function that determines if two things are isEquatable.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 * @param <T> The common class between both comparable objects.
 */
interface Equatator<T> {

	val type: Class<T>

	fun <L : T, R : T> isEquatable(left: L?, right: R?): Boolean

}
