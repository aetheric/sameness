package nz.co.aetheric.sameness.reflection

import org.apache.commons.lang3.tuple.Pair

import javax.validation.constraints.NotNull

/**
 * This just holds the results of a pairwise MethodCall creation process.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
internal class MethodCallResult<T> : Pair<MethodCall<T>, MethodCall<T>> {

	private val left: MethodCall<T>
	private val right: MethodCall<T>

	@Suppress("ConvertSecondaryConstructorToPrimary")
	constructor(left: MethodCall<T>, right: MethodCall<T>) : super() {
		this.left = left
		this.right = right
	}

	override fun getLeft()
			= left

	override fun getRight()
			= right

	override fun setValue(newValue: MethodCall<T>)
			= throw RuntimeException("Not allowed to edit immutable results.")

}
