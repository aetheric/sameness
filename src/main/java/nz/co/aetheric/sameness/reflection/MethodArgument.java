package nz.co.aetheric.sameness.reflection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import nz.co.aetheric.sameness.Equatable;
import nz.co.aetheric.sameness.Equatator;
import nz.co.aetheric.sameness.equatators.TypedEquatator;

import javax.validation.constraints.NotNull;
import java.util.List;

import static nz.co.aetheric.sameness.equatators.SimpleEquatator.simpleEquatator;

/**
 * TODO: What is the purpose of this class?
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("WeakerAccess")
public class MethodArgument<T> implements Equatable<T> {

	@NotNull
	@SuppressWarnings("unchecked")
	public static <T> MethodArgument arg(T value, @NotNull TypedEquatator<T> equatator) {
		return new MethodArgument(value, equatator);
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public static <T> MethodArgument arg(T value, @NotNull Class<T> type) {
		return arg(value, simpleEquatator(type));
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public static <T> MethodArgument arg(@NotNull T value) {
		return arg(value, (Class<T>) value.getClass());
	}

	@NotNull
	public static List getValues(@NotNull List<MethodArgument> args) {
		return Lists.transform(args, new Function<MethodArgument, Object>() {
			@Override
			public Object apply(MethodArgument input) {
				return input.value;
			}
		});
	}

	@NotNull
	public static List<Class> getTypes(@NotNull List<MethodArgument> args) {
		return Lists.transform(args, new Function<MethodArgument, Class>() {
			@Override
			public Class apply(MethodArgument input) {
				return input.equatator.getType();
			}
		});
	}

	private final T value;
	private final TypedEquatator<T> equatator;

	private MethodArgument(T value, @NotNull TypedEquatator<T> equatator) {
		this.value = value;
		this.equatator = equatator;
	}

	@Override
	public boolean isEquatable(T other) {
		return equatator.isEquatable(value, other);
	}

	@NotNull
	public Equatator<T> getEquatator() {
		return equatator;
	}

}
