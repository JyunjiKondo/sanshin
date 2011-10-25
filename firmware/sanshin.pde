#include <Wire.h>

#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

#define  SOFTPOT1       A0
#define  SOFTPOT2       A1
#define  SOFTPOT3       A2

#define  PIEZO1         A3
#define  PIEZO2         A4
#define  PIEZO3         A5

#define SoftpotMinThreshold 100
#define SoftpotMaxThreshold 1000
#define PiezoMinThreshold 20
#define PiezoMaxThreshold 0x500
#define BaseVelocity 40
#define MinVelocityDelta 10
#define MaxVelocityDelta 87 // BaseVelocity + MaxVelocityDelta = 127 (Max Velocity)
#define MaleStringId 1
#define MiddleStringId 2
#define FemaleStringId 3

AndroidAccessory acc("Google, Inc.",
		     "DemoKit",
		     "DemoKit Arduino Board",
		     "1.0",
		     "http://www.android.com",
		     "0000000012345678");


int maleStringLastVelocity = -1, middleStringLastVelocity = -1, femaleStringLastVelocity = -1;
boolean maleStringPicked = false, middleStringPicked = false, femaleStringPicked = false;

void setup();
void loop();
void sendMessage(int, int, int);

void setup()
{
	acc.powerOn();
}

void loop()
{
        byte message[3];
        int len;
        int maleStringPosition, middleStringPosition, femaleStringPosition;
        int value;
        int velocity;

	if (acc.isConnected()) {
		len = acc.read(message, sizeof(message), 1);
                if (len > 2) { // write 3 bytes data if something is read. This is for releasing blocking read to terminate thread.
			if (message[0] == 0x1 &&
                            message[1] == 0x2 &&
                            message[2] == 0x3) {
                                acc.write(message, 3);
                        }
                }

                maleStringPosition = map(analogRead(SOFTPOT1), SoftpotMinThreshold, SoftpotMaxThreshold, 0, 50);
                middleStringPosition = map(analogRead(SOFTPOT2), SoftpotMinThreshold, SoftpotMaxThreshold, 0, 50);
                femaleStringPosition = map(analogRead(SOFTPOT3), SoftpotMinThreshold, SoftpotMaxThreshold, 0, 50);

                value = analogRead(PIEZO1);
                if (value >= PiezoMinThreshold) {
                        if (value > PiezoMaxThreshold) {
                                value = PiezoMaxThreshold;
                        }
                        velocity = map(value, PiezoMinThreshold, PiezoMaxThreshold, MinVelocityDelta, MaxVelocityDelta) + BaseVelocity;
                        if (maleStringPicked == false) {
                                if (velocity < maleStringLastVelocity) {
                                        sendMessage(MaleStringId, maleStringPosition, velocity);
                                        maleStringPicked = true;
                                }
                        } else {
                                if (velocity >= maleStringLastVelocity) {
                                        maleStringPicked = false;
                                }
                        }
                        maleStringLastVelocity = velocity;
                }

                value = analogRead(PIEZO2);
                if (value >= PiezoMinThreshold) {
                        if (value > PiezoMaxThreshold) {
                                value = PiezoMaxThreshold;
                        }
                        velocity = map(value, PiezoMinThreshold, PiezoMaxThreshold, MinVelocityDelta, MaxVelocityDelta) + BaseVelocity;
                        if (middleStringPicked == false) {
                                if (velocity < middleStringLastVelocity) {
                                        sendMessage(MiddleStringId, middleStringPosition, velocity);
                                        middleStringPicked = true;
                                }
                        } else {
                                if (velocity >= middleStringLastVelocity) {
                                        middleStringPicked = false;
                                }
                        }
                        middleStringLastVelocity = velocity;
                }

                value = analogRead(PIEZO3);
                if (value >= PiezoMinThreshold) {
                        if (value > PiezoMaxThreshold) {
                                value = PiezoMaxThreshold;
                        }
                        velocity = map(value, PiezoMinThreshold, PiezoMaxThreshold, MinVelocityDelta, MaxVelocityDelta) + BaseVelocity;
                        if (femaleStringPicked == false) {
                                if (velocity < femaleStringLastVelocity) {
                                        sendMessage(FemaleStringId, femaleStringPosition, velocity);
                                        femaleStringPicked = true;
                                }
                        } else {
                                if (velocity >= femaleStringLastVelocity) {
                                        femaleStringPicked = false;
                                }
                        }
                        femaleStringLastVelocity = velocity;
                }
	}

	delay(10);
}

#define OpenStringNoteThreshold 3
#define FirstPositionNoteThreshold 17
#define SecondPositionNoteThreshold 32

byte noteTable[] = { 60, 62, 64, 65, 65, 67, 69, 71, 72, 74, 76, 77 };
void
sendMessage(int stringId, int position, int velocity)
{
        int positionIndex;
        int tableOffset;
        byte message[3];
        int note;

        if (position < OpenStringNoteThreshold) {
                positionIndex = 0;
        } else if (position < FirstPositionNoteThreshold) {
                positionIndex = 1;
        } else if (position < SecondPositionNoteThreshold) {
                positionIndex = 2;
        } else {
                positionIndex = 3;
        }
        switch (stringId) {
        case MaleStringId:
                tableOffset = 0;
                break;
        case MiddleStringId:
                tableOffset = 4;
                break;
        case FemaleStringId:
                tableOffset = 8;
                break;
        }
        note = noteTable[positionIndex + tableOffset];
        message[0] = stringId;
        message[1] = note;
        message[2] = velocity;
        acc.write(message, 3);
}
