package Interpreteur;

public class Request
{
	private final Order order;
	private final String[] args;

	public Request(String order, String[] args) throws UnknownOrder
	{
		this.order = convertStringToOrder(order);
		this.args = args;
	}

	public Order convertStringToOrder(String input) throws UnknownOrder
	{
		Order[] orders = Order.values();
		Order result = null;

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
		return this.args[index];
	}

	public int getIntArg(int index)
	{
		return Integer.parseInt(this.args[index]);
	}

	public int argCount()
	{
		return this.args.length;
	}
}
