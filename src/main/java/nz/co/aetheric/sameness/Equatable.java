package nz.co.aetheric.sameness;

/**
 * Declares that something is isEquatable to another type of thing.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
public interface Equatable<T> {

	boolean isEquatable(T right);

}
