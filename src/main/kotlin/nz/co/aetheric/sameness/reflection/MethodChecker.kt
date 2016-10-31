package nz.co.aetheric.sameness.reflection

import nz.co.aetheric.sameness.Equatator

import nz.co.aetheric.sameness.equatators.ExceptionEquatator.Companion.exceptionEquatator
import nz.co.aetheric.sameness.equatators.SimpleEquatator.Companion.simpleEquatator
import nz.co.aetheric.sameness.matchers.EquatableMatcher.Companion.equatableTo
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

/**
 * This class provides a function for checking if two
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class MethodChecker {

	private val leftType: Class<*>
	private val rightType: Class<*>

	@Suppress("ConvertSecondaryConstructorToPrimary")
	constructor(leftType: Class<*>, rightType: Class<*>) {
		this.leftType = leftType
		this.rightType = rightType
	}

	fun <T> check(
			name: String,
			args: List<MethodArgument<*>>,
			returnTypeEquatator: Equatator<T>

	) {

		val returnType = returnTypeEquatator.type
		val testers = MethodCall.pair(leftType, rightType, returnType, name, args)

		val leftArgs = testers.left.args
		val rightArgs = testers.right.args
		for (idx in args.indices) {
			val equatator = args[idx].equatator
			assertThat("Input arguments should be identical",
					leftArgs[idx], `is`(equatableTo(rightArgs[idx], equatator)))
		}

		val leftResult = testers.left.result
		val rightResult = testers.right.result
		assertThat("The results should be the same.",
				leftResult, `is`(equatableTo(rightResult, returnTypeEquatator)))

		val leftError = testers.left.error
		val rightError = testers.right.error
		assertThat("The errors should be the same.",
				leftError, `is`(equatableTo(rightError, exceptionEquatator)))

	}

	fun <T> check(
			name: String,
			args: List<MethodArgument<*>>,
			returnType: Class<T>

	) = check(name, args, simpleEquatator(returnType))

}
