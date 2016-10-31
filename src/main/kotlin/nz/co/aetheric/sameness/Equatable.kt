package nz.co.aetheric.sameness

/**
 * Declares that something is isEquatable to another type of thing.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
interface Equatable<in T> {

	fun isEquatable(other: T?): Boolean

}
