package nz.co.aetheric.sameness.equatators;

import nz.co.aetheric.sameness.Equatator;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public abstract class TypedEquatator<T> implements Equatator<T> {

	private final Class<T> type;

	public TypedEquatator(Class<T> type) {
		this.type = type;
	}

	@Override
	public abstract <L extends T, R extends T> boolean isEquatable(L left, R right);

	public boolean isApplicable(Object other) {
		return type.isAssignableFrom(other.getClass());
	}

	public boolean isApplicable(Object left, Object right) {
		return isApplicable(left) && isApplicable(right);
	}

	public T asApplicable(Object other) {
		return type.cast(other);
	}

	public boolean attemptEquatable(Object left, Object right) {
		return isEquatable(
			asApplicable(left),
			asApplicable(right)
		);
	}

	public Class<T> getType() {
		return this.type;
	}

}
