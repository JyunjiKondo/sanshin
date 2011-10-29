#include <Wire.h>
#include <Servo.h>

#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

#include <CapSense.h>

#define  BUTTON1        A6
#define  BUTTON2        A7
#define  BUTTON3        A8

AndroidAccessory acc("Google, Inc.",
		     "DemoKit",
		     "DemoKit Arduino Board",
		     "1.0",
		     "http://www.android.com",
		     "0000000012345678");

void setup();
void loop();

void init_buttons()
{
	pinMode(BUTTON1, INPUT);
	pinMode(BUTTON2, INPUT);
	pinMode(BUTTON3, INPUT);

	// enable the internal pullups
	digitalWrite(BUTTON1, HIGH);
	digitalWrite(BUTTON2, HIGH);
	digitalWrite(BUTTON3, HIGH);
}

byte b1, b2, b3;
void setup()
{
	Serial.begin(115200);
	Serial.print("\r\nStart");

	init_buttons();

	b1 = digitalRead(BUTTON1);
	b2 = digitalRead(BUTTON2);
	b3 = digitalRead(BUTTON3);

	acc.powerOn();
}

void loop()
{
	byte msg[3];

	if (acc.isConnected()) {
		int len = acc.read(msg, sizeof(msg), 1);
                if (len > 2) { // write 3 bytes data if something is read. This is for releasing blocking read to terminate thread.
			if (msg[0] == 0x1 &&
                            msg[1] == 0x2 &&
                            msg[2] == 0x3) {
                                acc.write(msg, 3);
                        }
                }

		byte b;

		msg[0] = 0x1;

		b = digitalRead(BUTTON1);
		if (b != b1) {
			msg[1] = 0;
			msg[2] = b ? 0 : 1;
			if (msg[2] == 1) {
				acc.write(msg, 3);
			}
			b1 = b;
		}

		b = digitalRead(BUTTON2);
		if (b != b2) {
			msg[1] = 1;
			msg[2] = b ? 0 : 1;
			if (msg[2] == 1) {
				acc.write(msg, 3);
			}
			b2 = b;
		}

		b = digitalRead(BUTTON3);
		if (b != b3) {
			msg[1] = 2;
			msg[2] = b ? 0 : 1;
			if (msg[2] == 1) {
				acc.write(msg, 3);
			}
			b3 = b;
		}

	}

	delay(10);
}
