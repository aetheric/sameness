package nz.co.aetheric.sameness.matchers;

import nz.co.aetheric.sameness.Equatator;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Matcher to check against an arbitrary predicate or comparison.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class EquatableMatcher<T> extends CustomMatcher<T> {

	@NotNull
	public static <T> EquatableMatcher<T> equatableTo(T reference, Equatator<T> equatator) {
		return new EquatableMatcher<>(reference, equatator);
	}

	private final T reference;
	private final Equatator<T> equatator;

	private EquatableMatcher(final T reference, @NotNull final Equatator<T> equatator) {
		super(Objects.toString(reference));
		this.reference = reference;
		this.equatator = equatator;
	}

	@Override
	public boolean matches(Object item) {
		return ( item == null || equatator.getType().isAssignableFrom(item.getClass()) )
				&& equatator.isEquatable(reference, equatator.getType().cast(item));
	}

	@Override
	public void describeMismatch(Object item, Description mismatchDescription) {
		mismatchDescription
				.appendText("Provided ")
				.appendValue(item)
				.appendText(" should be equatable to ")
				.appendValue(reference);
	}

}
