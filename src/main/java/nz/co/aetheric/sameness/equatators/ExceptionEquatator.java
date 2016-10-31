package nz.co.aetheric.sameness.equatators;

import java.util.Objects;

/**
 * Equates two exceptions.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class ExceptionEquatator<T extends Throwable> extends NullsafeEquatator<T> {

	public static final ExceptionEquatator<Throwable> exceptionEquatator = new ExceptionEquatator<>(Throwable.class);

	public ExceptionEquatator(Class<T> type) {
		super(type);
	}

	@Override
	public <L extends T, R extends T> boolean isEquatable(L left, R right) {
		return super.bothNull(left, right) || super.neitherNull(left, right)
			&& Objects.equals(left.getClass(), right.getClass())
			&& Objects.equals(left.getMessage(), right.getMessage());
	}

}
