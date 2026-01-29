// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.VictorSPXSimCollection;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final VictorSPX m_leftMotor;
  private final VictorSPX m_rightMotor;
  private final VictorSPX m_leftFolMotor;
  private final VictorSPX m_rightFolMotor;

  private VictorSPXSimCollection m_leftSimMotor;
  private VictorSPXSimCollection m_leftFolSimMotor;
  private VictorSPXSimCollection m_rightSimMotor;
  private VictorSPXSimCollection m_rightFolSimMotor;

  private double leftSpeed = 0;
  private double rightSpeed = 0;

  private boolean isSim = Robot.isSimulation();

  public DriveSubsystem() {
    m_leftMotor = new VictorSPX(DriveConstants.kLeftDriveMotorID);
    m_leftFolMotor = new VictorSPX(DriveConstants.kLeftFollowerDriveMotorID);
    m_rightMotor = new VictorSPX(DriveConstants.kRightDriveMotorID);
    m_rightFolMotor = new VictorSPX(DriveConstants.kRightFollowerDriveMotorID);

    m_leftFolMotor.follow(m_leftMotor);
    m_rightFolMotor.follow(m_rightMotor);

    m_leftMotor.setInverted(true);
    m_rightMotor.setInverted(false);

    if (isSim){
      m_leftSimMotor = m_leftMotor.getSimCollection();  
      m_leftFolSimMotor = m_leftFolMotor.getSimCollection();  
      m_rightSimMotor = m_rightMotor.getSimCollection(); 
      m_rightFolSimMotor = m_rightFolMotor.getSimCollection(); 
    }
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public Command exampleMethodCommand() {
  //   // Inline construction of command goes here.
  //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  //   return runOnce(
  //       () -> {
  //         /* one-time action goes here */
  //       });
  // }

  // /**
  //  * An example method querying a boolean state of the subsystem (for example, a digital sensor).
  //  *
  //  * @return value of some boolean subsystem state, such as a digital sensor.
  //  */
  // public boolean exampleCondition() {
  //   // Query some boolean state, such as a digital sensor.
  //   return false;
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_leftMotor.set(ControlMode.PercentOutput, leftSpeed);
    m_rightMotor.set(ControlMode.PercentOutput, rightSpeed);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  /**
   * This method will tell the drive subsystem to translate and rotate the robot.
   * 
   * @param y pass the y value of the left joystick- to be used for lateral driving.
   * @param x pass the x value of the Right joystick- for rotational driving.
   */
  public void drive(double y, double x){
    leftSpeed = y * DriveConstants.kDriveSpeedPercent + x * DriveConstants.kRotationSpeedPercent;
    leftSpeed = y * DriveConstants.kDriveSpeedPercent - x * DriveConstants.kRotationSpeedPercent;
  }
}
