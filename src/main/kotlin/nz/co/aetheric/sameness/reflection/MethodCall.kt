package nz.co.aetheric.sameness.reflection

import nz.co.aetheric.sameness.reflection.MethodArgument.Companion.getValues
import javax.validation.constraints.NotNull
import java.lang.reflect.InvocationTargetException

/**
 * This class performs and holds the context for a reflection-based method invocation.
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
internal class MethodCall<T> {

	companion object {

		fun <T> pair(
				left: Class<*>,
				right: Class<*>,
				returns: Class<T>,
				name: String,
				args: List<MethodArgument<*>>

		): MethodCallResult<T> {
			val methodTypes = MethodArgument.getTypes(args)
			return MethodCallResult(
					MethodCall(left, returns, name, getValues(args), methodTypes),
					MethodCall(right, returns, name, getValues(args), methodTypes)
			)
		}

	}

	val args: List<*>

	@Suppress("ConvertSecondaryConstructorToPrimary")
	constructor(source: Class<*>, results: Class<T>, name: String, args: List<*>, argTypes: List<Class<*>>) {
		this.args = args

		val argArray = args.toTypedArray()
		val typeArray = argTypes.toTypedArray()

		try {
			val method = source.getDeclaredMethod(name, *typeArray)

			val accessible = method.isAccessible
			try {
				if (!accessible) {
					method.isAccessible = true
				}

				val result = method.invoke(null, *argArray)

				this.result = results.cast(result)

			} finally {
				if (!accessible) {
					method.isAccessible = false
				}
			}

		} catch (error: InvocationTargetException) {
			this.error = error.cause

		} catch (error: Exception) {
			throw RuntimeException(error)
		}
	}

	var result: T? = null
		private set

	var error: Throwable? = null
		private set

}
