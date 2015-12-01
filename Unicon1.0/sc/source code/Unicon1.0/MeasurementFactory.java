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
	public final static String MEASURE_STORAGE      = "Digital Storage";
	public final static String MEASURE_CURRENCY     = "Currency"; // м б добавить (по времени)


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
	public final static String UNIT_WEIGHT_TON      = "metric ton";

	// объем
	public final static String UNIT_VOLUME_LITER     = "liter";
	public final static String UNIT_VOLUME_MILILITER = "mililiter";
	public final static String UNIT_VOLUME_GALLON    = "gallon";
	public final static String UNIT_VOLUME_CUP       = "cup";


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


	// память
	public final static String UNIT_STORAGE_BYTES       = "bytes (B)";
	public final static String UNIT_STORAGE_KILOBYTES   = "kilobytes (KB)";
	public final static String UNIT_STORAGE_MEGABYTES   = "megabytes (MB)";
	public final static String UNIT_STORAGE_GIGABYTES   = "gigabytes (GB)";
	public final static String UNIT_STORAGE_TERABYTES   = "terabytes (TB)";


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
				//Give an Error if user has no Internet connection.
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


	public void load()
	{
		//
		// длина
		// см см.:  1 дюйм = 2,54 см
		// 
		Measurement lengthMeasurements = new Measurement(MEASURE_LENGTH);
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_CM));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_METER,  0.01));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_KM,     0.00001));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_INCH,   1.0/2.54));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_FOOT,   1.0/(12.0*2.54)));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_MILE,   1.0/(2.54*12*5280.0)));
		lengthMeasurements.addUnit(new MeasurementUnit(UNIT_LENGTH_MILLIMETER,   1.0/0.01));


		//
		// вес
		// см. г : 1 кг = 2,2 фунта
		//
		Measurement weightMeasurements = new Measurement(MEASURE_WEIGHT);
		weightMeasurements.addUnit(new MeasurementUnit(UNIT_WEIGHT_GRAM));
		weightMeasurements.addUnit(new MeasurementUnit(UNIT_WEIGHT_KG,     0.001));
		weightMeasurements.addUnit(new MeasurementUnit(UNIT_WEIGHT_TON,    1.0/1000000));


		//
		// температура
		// см. Цельсий : F = (С * 1,8 ) + 32
		//
		Measurement temperatureMeasurements = new Measurement(MEASURE_TEMPERATURE);
		temperatureMeasurements.setDecimalPlaces(1);
		temperatureMeasurements.addUnit(new MeasurementUnit(UNIT_TEMP_CELSIUS));
		temperatureMeasurements.addUnit(new MeasurementUnitFahrenheit(UNIT_TEMP_FAHRENHEIT));
		temperatureMeasurements.addUnit(new MeasurementUnitKelvin(UNIT_TEMP_KELVIN));
		
		//
		// объем
		// см. л : 1 галлон = 3.7854 литра
		//
		Measurement volumeMeasurements = new Measurement(MEASURE_VOLUME);
		volumeMeasurements.addUnit(new MeasurementUnit(UNIT_VOLUME_LITER));
		volumeMeasurements.addUnit(new MeasurementUnit(UNIT_VOLUME_MILILITER, 1000.0));
		volumeMeasurements.addUnit(new MeasurementUnit(UNIT_VOLUME_GALLON,    1.0/3.7854));
		volumeMeasurements.addUnit(new MeasurementUnit(UNIT_VOLUME_CUP,       16.0/3.7854));


		//
		// площадь
		// см. метры квадратные
		//
		Measurement areaMeasurements = new Measurement(MEASURE_AREA);
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_METER));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_HECTARE,   Math.pow(0.01, 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_KM,     Math.pow(0.001, 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_INCH,   Math.pow((100.0/(2.54)), 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_FOOT,   Math.pow((100.0/(12.0*2.54)), 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_YARD,   Math.pow((100.0/(12.0*3.0*2.54)), 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_SQ_MILE,   Math.pow((100.0/(12.0*5280.0*2.54)), 2)));
		areaMeasurements.addUnit(new MeasurementUnit(UNIT_AREA_ACRE,      Math.pow((100.0/(12.0*5280.0*2.54)), 2)*640.0));


		
		//
		// память
		// см. байт
		//
		Measurement storageMeasurements = new Measurement(MEASURE_STORAGE);
		storageMeasurements.addUnit(new MeasurementUnit(UNIT_STORAGE_BYTES));
		storageMeasurements.addUnit(new MeasurementUnit(UNIT_STORAGE_KILOBYTES,   1.0/1024.0));
		storageMeasurements.addUnit(new MeasurementUnit(UNIT_STORAGE_MEGABYTES,   1.0/1024000.0));
		storageMeasurements.addUnit(new MeasurementUnit(UNIT_STORAGE_GIGABYTES,   1.0/1073741824.0));
		storageMeasurements.addUnit(new MeasurementUnit(UNIT_STORAGE_TERABYTES,   1.0/1099511627776.0));
		

		this.measurements.put(MEASURE_LENGTH,       lengthMeasurements);
		this.measurements.put(MEASURE_WEIGHT,       weightMeasurements);
		this.measurements.put(MEASURE_TEMPERATURE,  temperatureMeasurements);
		this.measurements.put(MEASURE_VOLUME,       volumeMeasurements);
		this.measurements.put(MEASURE_AREA,         areaMeasurements);
		this.measurements.put(MEASURE_STORAGE,      storageMeasurements);
	}
}
