#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <sys/ioctl.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>
#include "I2Cpi.h"


#define I2C_ERROR_SUCCESS		 0
#define I2C_ERROR_OPEN			-1
#define I2C_ERROR_CLOSE			-2
#define I2C_ERROR_READ			-3
#define I2C_ERROR_WRITE			-4
#define I2C_ERROR_UNDEFINED		-9

int m_iFd;
int m_iError;

JNIEXPORT jint JNICALL Java_I2Cpi_I2C_1open(JNIEnv *env, jclass I2Cpi, jbyte iI2C_Bus)
{
	char szI2C_Bus[16];
	
	if(iI2C_Bus < 0 || iI2C_Bus > 1) return I2C_ERROR_UNDEFINED;
	
	strcpy(szI2C_Bus, "/dev/i2c-0");
	if(iI2C_Bus == 1) szI2C_Bus[9] = '1';

	if(iI2C_Bus < 0 || iI2C_Bus > 1) return I2C_ERROR_UNDEFINED;
	if(iI2C_Bus == 1) szI2C_Bus[9] = '1';

	// open device file
	if((m_iFd = open(szI2C_Bus, O_RDWR)) == -1)
	{
		// error opening 
		m_iError = errno;

		return I2C_ERROR_OPEN;
	}

	return I2C_ERROR_SUCCESS;
}


JNIEXPORT jint JNICALL Java_I2Cpi_I2C_1close(JNIEnv *env, jclass I2Cpi)
{
	// close device
	if(close(m_iFd) == -1)
	{
		// device could not be closed, save error code
		m_iError = errno;

		// return with negative result
		return I2C_ERROR_CLOSE;
	}
	
	return I2C_ERROR_SUCCESS;
}


JNIEXPORT jint JNICALL Java_I2Cpi_I2C_1read_1PCF8574(JNIEnv *env, jclass I2Cpi, jbyte byDeviceAddr, jbyte byRegisterAddr, jbyteArray byReceiveBuffer)
{
	jbyte *native_array;
	struct i2c_rdwr_ioctl_data ioctl_rw_data;
	struct i2c_msg message[2];
	
	native_array = (*env)->GetByteArrayElements(env, byReceiveBuffer, NULL);
	if(native_array == NULL) return I2C_ERROR_UNDEFINED;

	message[0].addr = byDeviceAddr;
	message[0].flags = I2C_M_RD;
	message[0].len = sizeof(byRegisterAddr);
	message[0].buf = &byRegisterAddr;

	message[1].addr = byDeviceAddr;
	message[1].flags = I2C_M_RD;
	message[1].len = sizeof(unsigned char);
	message[1].buf = native_array;

	ioctl_rw_data.msgs = message;
	ioctl_rw_data.nmsgs = 2;

	// call ioctl function
	if(ioctl(m_iFd, I2C_RDWR, &ioctl_rw_data) == -1)
	{
		// ioctl not successful
		m_iError = errno;

		(*env)->ReleaseByteArrayElements(env, byReceiveBuffer, native_array, 0);

		// return error code
		return I2C_ERROR_READ;
	}

	(*env)->ReleaseByteArrayElements(env, byReceiveBuffer, native_array, 0);

	return I2C_ERROR_SUCCESS;
}


JNIEXPORT jint JNICALL Java_I2Cpi_I2C_1write_1PCF8574(JNIEnv *env, jclass I2Cpi, jbyte byDeviceAddr, jbyte byRegisterAddr, jbyte byData)
{
	unsigned char byBuffer[2];
	struct i2c_rdwr_ioctl_data ioctl_rw_data;
	struct i2c_msg message[1];

	byBuffer[0] = byRegisterAddr;
	byBuffer[1] = byData;

	message[0].addr = byDeviceAddr;
	message[0].flags = 0;
	message[0].len = sizeof(byBuffer);
	message[0].buf = byBuffer;

	ioctl_rw_data.msgs = message;
	ioctl_rw_data.nmsgs = 1;

	// call ioctl function
	if(ioctl(m_iFd, I2C_RDWR, &ioctl_rw_data) == -1)
	{
		// ioctl not successful
		m_iError = errno;

		// return error code
		return I2C_ERROR_WRITE;
	}
	
	return I2C_ERROR_SUCCESS;
}
