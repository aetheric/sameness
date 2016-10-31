package nz.co.aetheric.sameness.equatators

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
open class NullsafeEquatator<T>(type: Class<T>) : TypedEquatator<T>(type) {

	companion object {

		fun <T> nullsafeEquatator(type: Class<T>)
				= NullsafeEquatator(type)

	}

	final override fun <L : T, R : T> isEquatable(left: L?, right: R?)
			= ( left == null && right == null )
			|| ( left != null && right != null )
			&& isSafelyEquatable(left, right)

	open fun <L:T,R:T> isSafelyEquatable(left: L, right: R) = true

}
