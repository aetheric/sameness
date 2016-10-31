package nz.co.aetheric.sameness.reflection;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static nz.co.aetheric.sameness.reflection.MethodArgument.getValues;

/**
 * This class performs and holds the context for a reflection-based method invocation.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
class MethodCall<T> {

	static <T> MethodCallResult<T> pair(
			@NotNull Class<?> left,
			@NotNull Class<?> right,
			@NotNull Class<T> returns,
			@NotNull String name,
			@NotNull List<MethodArgument> args

	) {
		final List<Class> methodTypes = MethodArgument.getTypes(args);
		return new MethodCallResult<> (
				new MethodCall<>(left, returns, name, getValues(args), methodTypes),
				new MethodCall<>(right, returns, name, getValues(args), methodTypes)
		);
	}

	private final List args;
	private T result = null;
	private Throwable error = null;

	private MethodCall(
			@NotNull Class<?> source,
			@NotNull Class<T> results,
			@NotNull String name,
			@NotNull List args,
			@NotNull List<Class> argTypes

	) {
		this.args = args;

		final Object[] argArray = args.toArray();
		final Class[] typeArray = argTypes.toArray(new Class[argTypes.size()]);

		try {
			final Method method = source.getDeclaredMethod(name, typeArray);

			final boolean accessible = method.isAccessible();
			try {
				if (!accessible) {
					method.setAccessible(true);
				}

				final Object result = method.invoke(null, argArray);

				this.result = results.cast(result);

			} finally {
				if (!accessible) {
					method.setAccessible(false);
				}
			}

		} catch (InvocationTargetException error) {
			this.error = error.getCause();

		} catch (Exception error) {
			throw new RuntimeException(error);
		}

	}

	List getArgs() {
		return args;
	}

	T getResult() {
		return result;
	}

	Throwable getError() {
		return error;
	}

}
