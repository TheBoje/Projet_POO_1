package Interpreteur;

public class Request
{
	private final Order order;
	private final String[] args;

	public Request(String order, String[] args) throws UnknownOrder
	{
		this.order = convertStringToOrder(order);
		if (args != null)
		{
			this.args = args;
		}
		else
		{
			this.args = new String[0];
		}
	}

	public Order convertStringToOrder(String input) throws UnknownOrder
	{
		Order[] orders = Order.values();
		Order result = null;
		input = input.toLowerCase();
		for (Order o : orders)
		{
			if (input.equals(o.getString()))
			{
				result = o;
				break;
			}
		}

		if (result != null)
		{
			return result;
		}
		else
		{
			throw new UnknownOrder();
		}
	}

	public Order getOrder()
	{
		return this.order;
	}

	public String[] getArgs()
	{
		return this.args;
	}

	public String getArg(int index)
	{
		if (index >= 0 && index < this.argCount())
		{
			return this.args[index];
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public int getIntArg(int index)
	{
		if (index >= 0 && index < this.argCount())
		{
			return Integer.parseInt(this.args[index]);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public int argCount()
	{
		if (this.args == null)
		{
			return 0;
		}
		else
		{
			return this.args.length;
		}
	}
}
