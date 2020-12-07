package TestsInterpreteur;

import Interpreteur.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTests
{
	String order;
	String[] args = new String[1];
	Request r;

	@Test
	public void testRequestConstructor()
	{
		order = "go";
		args[0] = "N";
		Throwable exception = assertThrows(NullPointerException.class, () -> new Request(null, null));
		assertEquals(exception.getClass(), NullPointerException.class);
		exception = assertThrows(NullPointerException.class, () -> new Request(null, args));
		assertEquals(exception.getClass(), NullPointerException.class);
		exception = assertThrows(IllegalArgumentException.class, () -> new Request("go", null));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
	}

	@Test
	public void testConvertStringToOrder() throws UnknownOrder
	{
		order = "go";
		args[0] = "N";
		r = new Request(order, args );
		assertEquals(r.convertStringToOrder("debug"), Order.DEBUG);
		assertEquals(r.convertStringToOrder("take"), Order.TAKE);
		assertEquals(r.convertStringToOrder("open"), Order.OPEN);
		assertEquals(r.convertStringToOrder("list"), Order.LIST);
		Throwable exception = assertThrows(UnknownOrder.class, () -> r.convertStringToOrder("azes"));
		assertEquals(exception.getClass(), UnknownOrder.class);
	}

	@Test
	public void testConvertStringToOrderNull()
	{
		Throwable exception = assertThrows(NullPointerException.class, () -> r.convertStringToOrder(null));
		assertEquals(exception.getClass(), NullPointerException.class);
		exception = assertThrows(NullPointerException.class, () -> r.convertStringToOrder(""));
		assertEquals(exception.getClass(), NullPointerException.class);
	}

	@Test
	public void testGetArgNull()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> r.getArg(-1));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
		exception = assertThrows(IllegalArgumentException.class, () -> r.getArg(998));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
		exception = assertThrows(IllegalArgumentException.class, () -> r.getIntArg(-1));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
		exception = assertThrows(IllegalArgumentException.class, () -> r.getIntArg(998));
		assertEquals(exception.getClass(), IllegalArgumentException.class);
	}

	@Test
	public void testArgCount() throws UnknownOrder
	{
		String[] args = new String[2];
		args[0] = "aze";
		args[1] = "eza";
		r = new Request("go", args);
		assertEquals(r.argCount(), args.length);
	}
}
