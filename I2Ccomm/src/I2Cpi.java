
public class I2Cpi {
	static { System.loadLibrary("I2Cpi"); }
	public static native int I2C_open(byte byDeviceAddr);
	public static native int I2C_close();
	public static native int I2C_read_PCF8574(byte byDeviceAddr, byte byRegisterAddr, byte[] byValue);
	public static native int I2C_write_PCF8574(byte byDeviceAddr, byte byRegisterAddr, byte byValue);
}
