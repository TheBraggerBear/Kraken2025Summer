// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Shooter shooter = new Shooter();
  private final IntakeArm intakeArm = new IntakeArm();
  private final DriveTrain driveTrain = new DriveTrain();
  private final AirCompressor airCompressor = new AirCompressor();
  private final Climber climber = new Climber();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  public DriveTrain getDriveTrain() {
    return driveTrain;
  }

  public CommandXboxController getDriverController() {
    return m_driverController;
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    driveTrain.setDefaultCommand(driveTrain.arcadeDrive(m_driverController.getLeftY(), m_driverController.getRightX()));

    new Trigger(() -> m_driverController.getLeftTriggerAxis() > 0.05) // Trigger activates when left trigger is pressed
    .whileTrue(shooter.runAtOutput(m_driverController::getLeftTriggerAxis));
    
    m_driverController.rightBumper().whileTrue(shooter.fireSolenoid2());
    m_driverController.rightTrigger()
        .whileTrue(shooter.fireSolenoid1());

    m_driverController.povLeft().toggleOnTrue(shooter.testMoveServo(180.0, shooter.armBlockingServo));
    m_driverController.povRight().toggleOnTrue(shooter.testMoveServo(90.0, shooter.frisbeeBlockingServo));
    m_driverController.povUp().onTrue(intakeArm.setIntakeArm(DoubleSolenoid.Value.kForward));
    m_driverController.povDown().onTrue(intakeArm.setIntakeArm(DoubleSolenoid.Value.kReverse));

    m_driverController.y().whileTrue(climber.raiseClimber(0.25));
    m_driverController.a().whileTrue(climber.raiseClimber(-0.25));
    m_driverController.x().whileTrue(climber.raiseClimber(0.0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

  public void teleopReset() {
    // Reset the drive train to its default state
    driveTrain.driveTrain.stopMotor();
    // Reset the shooter to its default state
    shooter.bigShooter.set(0);
    shooter.smallShooter.set(0);
    // Reset the intake arm to its default state
    intakeArm.setIntakeArm(DoubleSolenoid.Value.kOff).schedule();
    // Reset the climber to its default state
    // climber.raiseClimber(0.0).schedule();
  }
}
