package nz.co.aetheric.sameness.equatators

import nz.co.aetheric.sameness.Equatator

/**
 * TODO: What is the purpose of this class?
 *
 * Author: [Peter Cummuskey](http://gplus.to/tzrlk)
 */
class VoidEquatator : Equatator<Void> {

	companion object {
		val voidEquatator = VoidEquatator()
	}

	override val type: Class<Void>
		get() = Void::class.java

	override fun <L : Void, R : Void> isEquatable(left: L?, right: R?)
			= true

}
