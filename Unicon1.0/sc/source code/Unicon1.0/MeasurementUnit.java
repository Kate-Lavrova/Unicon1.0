
package sc.source code.Unicon1.0;

public class MeasurementUnit
{
	protected String name;
	protected double referenceFactor;
	
	public MeasurementUnit()
	{
		this("no unit", 1.0);
	}

	public MeasurementUnit(final String name)
	{
		this(name, 1.0);
	}


	public MeasurementUnit(String name, double referenceFactor) 
	{
		this.name = name;
		this.referenceFactor = referenceFactor;
	}

	public final String getName()
	{
		return name;
	}

	public final void setName(final String name)
	{
		this.name = name;
	}
	
	
	public double getReferenceFactor()
	{
		return referenceFactor;
	}

	public void setReferenceFactor(double referenceFactor)
	{
		this.referenceFactor = referenceFactor;
	}


	public double convertToReference(double refValue)
	{
		return refValue / referenceFactor;
	}

	
	public double convertFromReference(double refValue)
	{
		return refValue * referenceFactor;
	}
}
