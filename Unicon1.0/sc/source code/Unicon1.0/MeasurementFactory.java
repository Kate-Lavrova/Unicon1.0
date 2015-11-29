package sc.source code.Unicon1.0;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

import sc.source code.Unicon1.0.units.MeasurementUnitFahrenheit;
import sc.source code.Unicon1.0.units.MeasurementUnitKelvin;


public class MeasurementFactory
{
	// категории
	public final static String MEASURE_LENGTH       = "Length";
	public final static String MEASURE_WEIGHT       = "Weight";
	public final static String MEASURE_TEMPERATURE  = "Temperature";
	public final static String MEASURE_VOLUME       = "Volume";
	public final static String MEASURE_AREA         = "Area";
	public final static String MEASURE_SPEED	    = "Speed";
	public final static String MEASURE_RADIATION    = "Radiation";
	public final static String MEASURE_ANGLE        = "Angle Measurments";
	public final static String MEASURE_STORAGE      = "Digital Storage";
	public final static String MEASURE_CURRENCY     = "Currency"; 
	// м б добавить (по времени) денежную категорию 
	
		// длина
	public final static String UNIT_LENGTH_CM         = "centimeter";
	public final static String UNIT_LENGTH_METER      = "meter";
	public final static String UNIT_LENGTH_KM         = "kilometer";
	public final static String UNIT_LENGTH_INCH       = "inch";
	public final static String UNIT_LENGTH_FOOT       = "foot";
	public final static String UNIT_LENGTH_MILE       = "mile";
	public final static String UNIT_LENGTH_MILLIMETER = "millimeter";

	// вес.
	public final static String UNIT_WEIGHT_GRAM     = "gram";
	public final static String UNIT_WEIGHT_KG       = "kilogram";
	public final static String UNIT_WEIGHT_POUND    = "pound";
	public final static String UNIT_WEIGHT_OUNCE    = "ounce";
	public final static String UNIT_WEIGHT_TON      = "metric ton";

	// объем.
	public final static String UNIT_VOLUME_LITER     = "liter";
	public final static String UNIT_VOLUME_MILILITER = "mililiter";
	public final static String UNIT_VOLUME_GALLON    = "gallon";
	public final static String UNIT_VOLUME_CUP       = "cup";
	public final static String UNIT_VOLUME_PINT      = "pint";
	public final static String UNIT_VOLUME_QUART     = "quart";
	public final static String UNIT_VOLUME_OUNCE     = "ounce";

	// температура.
	public final static String UNIT_TEMP_CELSIUS    = "celsius";
	public final static String UNIT_TEMP_FAHRENHEIT = "fahrenheit";
	public final static String UNIT_TEMP_KELVIN     = "kelvin";

	// площадь
	public final static String UNIT_AREA_SQ_METER   = "square meter";
	public final static String UNIT_AREA_SQ_KM      = "square km";
	public final static String UNIT_AREA_SQ_INCH    = "square inch";
	public final static String UNIT_AREA_SQ_FOOT    = "square foot";
	public final static String UNIT_AREA_SQ_YARD    = "square yard";
	public final static String UNIT_AREA_SQ_MILE    = "square mile";
	public final static String UNIT_AREA_ACRE       = "acre";
	public final static String UNIT_AREA_HECTARE    = "hectare";

	// скорость
	public final static String UNIT_SPEED_KM_PER_HOUR        = "kilometers per hour";
	public final static String UNIT_SPEED_MILES_PER_HOUR     = "miles per hour";
	public final static String UNIT_SPEED_METERS_PER_SECOND  = "meters per second";
	public final static String UNIT_SPEED_KNOTS              = "knots";

	// радиация
	public final static String UNIT_RADIATION_REM     = "rem";
	public final static String UNIT_RADIATION_RAD     = "rad";

	// угловые измерения
	public final static String UNIT_ANGLE_DEGREES   = "degrees";
	public final static String UNIT_ANGLE_RADIANS   = "radians";
	public final static String UNIT_ANGLE_GRADIANS  = "gradians";

	// память
	public final static String UNIT_STORAGE_BYTES       = "bytes (B)";
	public final static String UNIT_STORAGE_KILOBYTES   = "kilobytes (KB)";
	public final static String UNIT_STORAGE_MEGABYTES   = "megabytes (MB)";
	public final static String UNIT_STORAGE_GIGABYTES   = "gigabytes (GB)";
	public final static String UNIT_STORAGE_TERABYTES   = "terabytes (TB)";
	public final static String UNIT_STORAGE_KILOBANANAS = "kilobananas (KBA)";
	public final static String UNIT_STORAGE_MEGABANANAS = "megabananas (MBA)";
	public final static String UNIT_STORAGE_GIGABANANAS = "gigabananas (GBA)";
	public final static String UNIT_STORAGE_TERABANANAS = "terabananas (TBA)";

	// валюта !!! (дописать и сделать апдейт курса как-то оо)
	public final static String UNIT_CURRENCY_USD = "U.S. Dollars";
	public final static String UNIT_CURRENCY_EUR = "Euros";
	public final static String UNIT_CURRENCY_GBP = "British Pounds";
	public final static String UNIT_CURRENCY_INR = "Indian Rupee";
	public final static String UNIT_CURRENCY_AUD = "Australian Dollars";
	public final static String UNIT_CURRENCY_CAD = "Canadian Dollars";
	public final static String UNIT_CURRENCY_JPY = "Japanese Yen";
	public final static String UNIT_CURRENCY_CNY = "Chinese Yuan";
	public final static String UNIT_CURRENCY_RUB = "Russian Ruble";
	
	private Map<String, Measurement> measurements;
	private static MeasurementFactory instance = null;

	private MeasurementFactory()
	{
		this.measurements = new TreeMap<String, Measurement>();
	}

	public final static MeasurementFactory instance()
	{
		if(instance == null)
		{
			instance = new MeasurementFactory();
		}

		return instance;
	}

	public Set<String> getEntries()
	{
		return this.measurements.keySet();
	}

	public Measurement getMeasurement(final String measurementType)
			// Для обновления (валюта)!!!!!!!!!!!!
	{
		if(measurementType.equals(MEASURE_CURRENCY))
			if(!isInternetReachable())
				
				UnitConverterGui.ErrorMessage("Currency conversion requires an active Internet connection.");
		return this.measurements.get(measurementType);
	}

	public static boolean isInternetReachable()
    {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
            @SuppressWarnings("unused")
			Object objData = urlConnect.getContent();

        } catch (Exception e) {              
            e.printStackTrace();
            return false;
        }

        return true;
    }
	}
