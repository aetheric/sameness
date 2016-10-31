package nz.co.aetheric.sameness.reflection

import com.google.common.collect.Lists
import nz.co.aetheric.sameness.Equatable
import nz.co.aetheric.sameness.equatators.SimpleEquatator.Companion.simpleEquatator
import nz.co.aetheric.sameness.equatators.TypedEquatator

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class MethodArgument<T> : Equatable<T> {

	companion object {

		fun <T> arg(value: T?, equatator: TypedEquatator<T>)
				= MethodArgument(value, equatator)

		fun <T> arg(value: T?, type: Class<T>)
				= arg(value, simpleEquatator(type))

		fun <T> arg(value: T)
				= arg(value, value.javaClass)

		fun getValues(args: List<MethodArgument<*>>)
				= Lists.transform(args) { input -> input!!.value }

		fun getTypes(args: List<MethodArgument<*>>)
				= Lists.transform(args) { input -> input!!.equatator.type }

	}

	val value: T?
	val equatator: TypedEquatator<T>

	@Suppress("ConvertSecondaryConstructorToPrimary")
	constructor(value: T?, equatator: TypedEquatator<T>) {
		this.value = value
		this.equatator = equatator
	}

	override fun isEquatable(other: T?)
			= equatator.isEquatable(value, other)

}
