### 1. Accelerometer

The accelerometer (TYPE_ACCELEROMETER) is related to gravitational acceleration.
You can see this by turning on the accelerometer and rolling your phone back and forth.



**1.1 Mobile phone tilt according to coordinates**

A simple test will give you the values shown in the figure below.
![image](https://user-images.githubusercontent.com/43742747/82753410-52872a80-9e00-11ea-8bc4-b5a842d94787.png)


**1.2 Mobile phone tilt in any direction**

Up to now, it was about the right angle direction, what kind of value will be displayed if the mobile phone is placed at an angle?

Since the gravitational acceleration acts constantly at 9.8, the value of 9.8 that the z axis had for the tilted angle was

The x-axis and y-axis are separated.
![image](https://user-images.githubusercontent.com/43742747/82753430-8cf0c780-9e00-11ea-9420-c0b26542e1d5.png)


Referring to the formula for finding the length of the hypotenuse of a right triangle by the Pythagoras theorem,

Right-angled square sizes are also available.

After that, the total acceleration size of the tilted mobile phone is the square root of the value obtained by adding the square of each side.

As shown in the mobile phone's coordinate diagram, the gravitational acceleration causes the center of the earth to point, so the positive coordinate values ​​are reversed.





### 2. Removed the influence of acceleration sensor and gravity


Because the accelerometer is affected by gravity, you can

It is only when the values ​​are offset.

It means that we have to have no gravity to live 
```
private float accX, accY, accZ, tempX, tempY, tempZ;
private float alpha = 0.8f;

tempX = alpha * tempX + (1 - alpha) * event.values[0];
tempY = alpha * tempY + (1 - alpha) * event.values[1];
tempZ = alpha * tempZ + (1 - alpha) * event.values[2];

accX = event.values[0] - tempX;
accY = event.values[1] - tempY;
accZ = event.values[2] - tempZ;


```
### 3. Linear acceleration sensor

However, Google offers an acceleration sensor that eliminates the acceleration of gravity.
That sensor is **_TYPE_LINEAR_ACCELERATION_.**
There is the **_TYPE_ACCELEROMETER_** sensor value as it is displayed on the top row
If the second value removes gravity from the **_TYPE_ACCELEROMETER_** sensor value
Finally, the **_TYPE_LINEAR_ACCELERATION_** sensor value.
![image](https://user-images.githubusercontent.com/43742747/82753533-4fd90500-9e01-11ea-89ab-8a179f44bf90.png)


