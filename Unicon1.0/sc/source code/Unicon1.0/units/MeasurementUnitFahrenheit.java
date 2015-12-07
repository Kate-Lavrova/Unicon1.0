package sc.source code.Unicon1.0.units;

import sc.source code.Unicon1.0.MeasurementUnit;

public class MeasurementUnitFahrenheit extends MeasurementUnit
{
	public MeasurementUnitFahrenheit()
	{
		super();
	}

	public MeasurementUnitFahrenheit(String name)
	{
		super(name);
	}

	
	public double convertToReference(double refValue)
	{
		return (refValue - 32.0) / 1.8;
	}


	public double convertFromReference(double refValue)
	{
		return (refValue * 1.8) + 32.0;
	}
}
