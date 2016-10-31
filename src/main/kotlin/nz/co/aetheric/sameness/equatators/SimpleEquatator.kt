package nz.co.aetheric.sameness.equatators

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class SimpleEquatator<T>(type: Class<T>) : NullsafeEquatator<T>(type) {

	companion object {

		fun <T> simpleEquatator(type: Class<T>)
				= SimpleEquatator(type)

	}

	override fun <L : T, R : T> isSafelyEquatable(left: L, right: R)
			= left == right

}
