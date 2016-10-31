package nz.co.aetheric.sameness.equatators;

import javax.validation.constraints.NotNull;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
public class SimpleEquatator<T> extends NullsafeEquatator<T> {

	public static <T> SimpleEquatator<T> simpleEquatator(Class<T> type) {
		return new SimpleEquatator<>(type);
	}

	@SuppressWarnings("WeakerAccess")
	public SimpleEquatator(@NotNull Class<T> type) {
		super(type);
	}

	@Override
	public <L extends T, R extends T> boolean isEquatable(L left, R right) {
		return super.bothNull(left, right) || super.neitherNull(left, right)
				&& left.equals(right);
	}

}
