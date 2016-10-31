package nz.co.aetheric.sameness.equatators

/**
 * Equates two exceptions.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class ExceptionEquatator<T : Throwable>(type: Class<T>) : NullsafeEquatator<T>(type) {

	companion object {

		val exceptionEquatator = ExceptionEquatator(Throwable::class.java)

	}

	override fun <L : T, R : T> isSafelyEquatable(left: L, right: R)
			= left.javaClass == right.javaClass
			&& left.message == right.message

}
