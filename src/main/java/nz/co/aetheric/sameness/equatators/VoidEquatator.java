package nz.co.aetheric.sameness.equatators;

import nz.co.aetheric.sameness.Equatator;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
public class VoidEquatator implements Equatator<Void> {

	public static VoidEquatator voidEquatator = new VoidEquatator();

	@Override
	public Class<Void> getType() {
		return Void.class;
	}

	@Override
	public <L extends Void, R extends Void> boolean isEquatable(L left, R right) {
		return true;
	}

}
