// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkBase.PersistMode;
// import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.ShooterState;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private ShooterState state = ShooterState.IDLE;
  
  private double intakeSpeed = 0;
  private double flywheelSpeed = 0;

  private final SparkMax m_intakeMotor;
  private final SparkMax m_flywheelMotor;

  private SparkMaxSim m_flywheelMotorSim;
  private SparkMaxSim m_intakeMotorSim;

  private final SparkMaxConfig intakeConfig;
  private final SparkMaxConfig flywheelConfig;

  private final boolean isSim = RobotBase.isSimulation();

  public ShooterSubsystem() {
    m_intakeMotor = new SparkMax(ShooterConstants.kIntakeMotorID, MotorType.kBrushed);
    m_flywheelMotor = new SparkMax(ShooterConstants.kFlywheelMotorID, MotorType.kBrushed);

    intakeConfig = new SparkMaxConfig();
    flywheelConfig = new SparkMaxConfig();

    intakeConfig
    .inverted(ShooterConstants.kInvertIntake);

    flywheelConfig
    .inverted(ShooterConstants.kInvertFlywheel);

    m_flywheelMotor.configure(flywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_intakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    if (isSim){
      m_flywheelMotorSim = new SparkMaxSim(m_flywheelMotor, DCMotor.getCIM(1));
      m_intakeMotorSim = new SparkMaxSim(m_intakeMotor, DCMotor.getCIM(1));
    }

  }

  // /**
  //  * Example command factory method.
  //  *
  //  * @return a command
  //  */
  // // public Command exampleMethodCommand() {
  // //   // Inline construction of command goes here.
  // //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  // //   return runOnce(
  // //       () -> {
  // //         /* one-time action goes here */
  // //       });
  // // }

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

    
    m_flywheelMotor.set(flywheelSpeed);
    m_intakeMotor.set(intakeSpeed);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void setDesiredState(ShooterState state){
    this.state = state;

    switch (this.state) {
      case SHOOT:
        flywheelSpeed = ShooterConstants.kFlywheelShootPercent;
        intakeSpeed = ShooterConstants.kIntakeOuttakingPercent;
        break;
      case INTAKE:
        flywheelSpeed = ShooterConstants.kFlywheelIntakingPercent;
        intakeSpeed = ShooterConstants.kIntakeIntakingPercent;
        break;
      case OUTTAKE:
        flywheelSpeed = ShooterConstants.kFlywheelOuttakingPercent;
        intakeSpeed = ShooterConstants.kIntakeOuttakingPercent;
        break;
      case IDLE:
        flywheelSpeed = 0;
        intakeSpeed = 0;
        break;
      default:
        flywheelSpeed = 0;
        intakeSpeed = 0;
        break;
    }
  }
}
