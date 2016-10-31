package nz.co.aetheric.sameness.equatators;

import javax.validation.constraints.NotNull;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class NullsafeEquatator<T> extends TypedEquatator<T> {

	public static <T> NullsafeEquatator<T> nullsafeEquatator(@NotNull Class<T> type) {
		return new NullsafeEquatator<>(type);
	}

	public NullsafeEquatator(@NotNull Class<T> type) {
		super(type);
	}

	@Override
	public <L extends T, R extends T> boolean isEquatable(L left, R right) {
		return bothNull(left, right)
				|| neitherNull(left, right);
	}

	public <L extends T, R extends T> boolean bothNull(L left, R right) {
		return left == null && right == null;
	}

	public <L extends T, R extends T> boolean neitherNull(L left, R right) {
		return left != null && right != null;
	}

}
