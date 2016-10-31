package nz.co.aetheric.sameness;

import javax.validation.constraints.NotNull;

/**
 * Defines a function that determines if two things are isEquatable.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 * @param <T> The common class between both comparable objects.
 */
@SuppressWarnings("WeakerAccess")
public interface Equatator<T> {

	@NotNull
	Class<T> getType();

	<L extends T, R extends T> boolean isEquatable(L left, R right);

}
