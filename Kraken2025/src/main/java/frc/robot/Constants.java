// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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

  public static class DriveTrainConstants {
    public static final int FRONT_LEFT_MOTOR_ID = 0;
    public static final int BACK_LEFT_MOTOR_ID = 1; 
    public static final int FRONT_RIGHT_MOTOR_ID = 2;
    public static final int BACK_RIGHT_MOTOR_ID = 3;
    public static final double OPEN_LOOP_RAMP = 0.5; // seconds from 0 to full
    public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
  }

  public static class ShooterConstants {
    public static final int BIG_SHOOTER_MOTOR_ID = 5;
    public static final int SMALL_SHOOTER_MOTOR_ID = 6;
    public static final boolean SHOOTER_INVERTED = true;
    public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast; // Set the neutral mode for the shooter motors
    public static final int FRISBEE_SHOOTING_SOLENOID_CHANNEL = 1;
    public static final int RAISING_SOLENOID_CHANNEL = 3;
    public static final int FRISBEE_BLOCKING_SERVO_CHANNEL = 0;
    public static final int ARM_BLOCKING_SERVO_CHANNEL = 1;
    public static final double FRISBEE_BLOCKING_SERVO_DEFAULT_ANGLE = 0.0;
    public static final double ARM_BLOCKING_SERVO_DEFAULT_ANGLE = 0.0; // Default angle for the arm blocking servo
  }

  public static class IntakeArmConstants {
    public static final int ARM_DOUBLESOLENOID_FORWARD_CHANNEL = 0; // Forward channel for the intake arm double solenoid
    public static final int ARM_DOUBLESOLENOID_REVERSE_CHANNEL = 2; // Reverse channel for the intake arm double solenoid
  }

  public static class ClimberConstants {
    public static final int CLIMBER_MOTOR_1_ID = 7;
    public static final int CLIMBER_MOTOR_2_ID = 8;
    public static final int CLIMBER_MOTOR_3_ID = 9;
    public static final int CLIMBER_MOTOR_4_ID = 10;
    public static final double OPEN_LOOP_RAMP = 0.5; // seconds from 0 to full
    public static final double CLIMBER_POTENTIOMETER_MAX_ANGLE = 3600; // Max angle for the climber potentiometer
    public static final int CLIMBER_POTENTIOMETER_CHANNEL = 2;
    public static final NeutralMode NEUTRAL_MODE = NeutralMode.Coast; // Set the neutral mode for the climber motors
  }

  public static class PnuematicConstants {
    public static final PneumaticsModuleType PNUEMATIC_MODULE_TYPE = PneumaticsModuleType.CTREPCM;
    public static final boolean DEFAULT_SINGLESOLENOID_STATE = false; // Default state for single solenoids
    public static final Value DEFAULT_DOUBLESOLENOID_STATE = Value.kOff; // Default state for double solenoids
  }
}
