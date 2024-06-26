// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // TODO: Verify ports and button mappings
  public static class DriverConstants {
    public static final int controllerPort = 0;
    public static final int leftX = 0;
    public static final int leftY = 1;
    public static final int lT = 2;
    public static final int rT = 3;
    public static final int rightX = 4;
    public static final int rightY = 5;

    public static final int a = 1;
    public static final int b = 2;
    public static final int x = 3;
    public static final int y = 4;
    public static final int lB = 5;
    public static final int rB = 6;

    public static final int start = 8;
    public static final int back = 7;

    public static final double driveMult = .85;
  }

  public static class OperatorConstants {
    public static final int controllerPort = 1;

    public static final int a = 1;
    public static final int b = 2;
    public static final int x = 3;
    public static final int y = 4;
    public static final int lB = 5;
    public static final int rB = 6;
    public static final int start = 8;
    public static final int back = 7;

  }

  public static class MotorControllerConstants {
    public static final int frontLeft = 1;
    public static final int frontRight = 2;
    public static final int backLeft = 3;
    public static final int backRight = 4;

    public static final int intakeMovement = 5;
    public static final int intakeMotor = 6;

    public static final int lClimber = 10;
    public static final int rClimber = 9;

    public static final int topShooter = 16;
    public static final int middleShooter = 17;
    public static final int bottomShooter = 18;

    public static final int LEDs = 0;
  }

  public static class SensorConstants {
    public static final int rangeFinderPort = 0;
  }
  //Inches and seconds
  public static class EncoderConstants {
    public static final double wheelRadius = 4;
    public static final double distancePerRevolution = 4 * 2 * Math.PI;
    public static final double velocityConversionFactor = distancePerRevolution * 60;
  }
}
