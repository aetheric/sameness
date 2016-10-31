package nz.co.aetheric.sameness.reflection;

import org.apache.commons.lang3.tuple.Pair;

import javax.validation.constraints.NotNull;

/**
 * This just holds the results of a pairwise MethodCall creation process.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
class MethodCallResult<T> extends Pair<MethodCall<T>, MethodCall<T>> {

	private final MethodCall<T> left;
	private final MethodCall<T> right;

	MethodCallResult(@NotNull MethodCall<T> left, @NotNull MethodCall<T> right) {
		this.left = left;
		this.right = right;
	}

	@NotNull
	@Override
	public MethodCall<T> getLeft() {
		return left;
	}

	@NotNull
	@Override
	public MethodCall<T> getRight() {
		return right;
	}

	/** @deprecated */
	@Override
	@Deprecated
	public MethodCall<T> setValue(MethodCall<T> value) {
		throw new RuntimeException("Not allowed to edit immutable results.");
	}

}
