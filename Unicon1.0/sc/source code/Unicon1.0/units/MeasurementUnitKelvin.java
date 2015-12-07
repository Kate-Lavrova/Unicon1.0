package sc.source code.Unicon1.0.units;

import sc.source code.Unicon1.0.MeasurementUnit;

public class MeasurementUnitKelvin extends MeasurementUnit
{
	public MeasurementUnitKelvin()
	{
		super();
	}

	public MeasurementUnitKelvin(String name)
	{
		super(name);
	}

	
	public double convertToReference(double refValue)
	{
		return (refValue - 273.15);
	}


	public double convertFromReference(double refValue)
	{
		return (refValue + 273.15);
	}
}
