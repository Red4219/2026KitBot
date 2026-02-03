// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveConstants {
    public static final int kLeftDriveMotorID = 1;
    public static final int kLeftFollowerDriveMotorID = 2;
    public static final int kRightDriveMotorID = 3;
    public static final int kRightFollowerDriveMotorID = 4;

    public static final double kDriveSpeedPercent = 0.8;
    public static final double kRotationSpeedPercent = 0.4;

  }

  public static class ShooterConstants {
    public static final int kIntakeMotorID = 7;
    public static final int kFlywheelMotorID = 5;

    public static final boolean kInvertIntake = true;
    public static final boolean kInvertFlywheel = true;

    public static final double kFlywheelShootPercent = 1;
    public static final double kIntakeIntakingPercent = 1;
    public static final double kIntakeOuttakingPercent = -1;
    public static final double kFlywheelReversePercent = -0.8;
    public static final double kFlywheelIntakingPercent = 1;
    public static final double kFlywheelOuttakingPercent = -1;

    public static enum ShooterState{
      SHOOT,
      INTAKE,
      OUTTAKE,
      IDLE
    }

  }

}
