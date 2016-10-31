package nz.co.aetheric.sameness.reflection;

import nz.co.aetheric.sameness.Equatator;

import javax.validation.constraints.NotNull;
import java.util.List;

import static nz.co.aetheric.sameness.equatators.ExceptionEquatator.exceptionEquatator;
import static nz.co.aetheric.sameness.equatators.SimpleEquatator.simpleEquatator;
import static nz.co.aetheric.sameness.matchers.EquatableMatcher.equatableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class provides a function for checking if two
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@SuppressWarnings("unused")
public class MethodChecker {

	private Class<?> leftType;
	private Class<?> rightType;

	@SuppressWarnings("unused")
	public MethodChecker(@NotNull Class<?> leftType, @NotNull Class<?> rightType) {
		this.leftType = leftType;
		this.rightType = rightType;
	}

	@SuppressWarnings("WeakerAccess")
	public <T> void check(
			@NotNull String name,
			@NotNull List<MethodArgument> args,
			@NotNull Equatator<T> returnTypeEquatator

	) {

		final Class<T> returnType = returnTypeEquatator.getType();
		final MethodCallResult<T> testers = MethodCall.pair(leftType, rightType, returnType, name, args);

		final List leftArgs = testers.getLeft().getArgs();
		final List rightArgs = testers.getRight().getArgs();
		for (int idx = 0; idx < args.size(); idx++) {
			final Equatator equatator = args.get(idx).getEquatator();
			assertThat("Input arguments should be identical",
					leftArgs.get(idx), is(equatableTo(rightArgs.get(idx), equatator)));
		}

		final T leftResult = testers.getLeft().getResult();
		final T rightResult = testers.getRight().getResult();
		assertThat("The results should be the same.",
				leftResult, is(equatableTo(rightResult, returnTypeEquatator)));

		final Throwable leftError = testers.getLeft().getError();
		final Throwable rightError = testers.getRight().getError();
		assertThat("The errors should be the same.",
				leftError, is(equatableTo(rightError, exceptionEquatator)));

	}

	@SuppressWarnings("unused")
	public <T> void check(
			@NotNull String name,
			@NotNull List<MethodArgument> args,
			@NotNull Class<T> returnType

	) {
		check(name, args, simpleEquatator(returnType));
	}

}
