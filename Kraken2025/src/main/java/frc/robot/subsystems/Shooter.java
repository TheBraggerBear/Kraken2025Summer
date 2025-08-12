package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    public WPI_VictorSPX bigShooter = new WPI_VictorSPX(Constants.ShooterConstants.BIG_SHOOTER_MOTOR_ID); 
    public WPI_VictorSPX smallShooter = new WPI_VictorSPX(Constants.ShooterConstants.SMALL_SHOOTER_MOTOR_ID);
    Solenoid frisbeeShootingSolenoid = new Solenoid(Constants.PnuematicConstants.PNUEMATIC_MODULE_TYPE, Constants.ShooterConstants.FRISBEE_SHOOTING_SOLENOID_CHANNEL);
    Solenoid raisingSolenoid1 = new Solenoid(Constants.PnuematicConstants.PNUEMATIC_MODULE_TYPE, Constants.ShooterConstants.RAISING_SOLENOID_CHANNEL);
    public Servo frisbeeBlockingServo = new Servo(Constants.ShooterConstants.FRISBEE_BLOCKING_SERVO_CHANNEL); // Example servo for shooter angle adjustment, adjust port as needed
    public Servo armBlockingServo = new Servo(Constants.ShooterConstants.ARM_BLOCKING_SERVO_CHANNEL); // Example second servo for shooter angle adjustment, adjust port as needed

    public Shooter() {
        bigShooter.setNeutralMode(Constants.ShooterConstants.NEUTRAL_MODE); // Set the neutral mode for the shooter motors
        smallShooter.setNeutralMode(Constants.ShooterConstants.NEUTRAL_MODE); // Set the neutral mode for the shooter motors
        bigShooter.setInverted(Constants.ShooterConstants.SHOOTER_INVERTED);
        smallShooter.setInverted(Constants.ShooterConstants.SHOOTER_INVERTED);

        frisbeeShootingSolenoid.set(Constants.PnuematicConstants.DEFAULT_SINGLESOLENOID_STATE);
        raisingSolenoid1.set(Constants.PnuematicConstants.DEFAULT_SINGLESOLENOID_STATE);
        frisbeeBlockingServo.setAngle(Constants.ShooterConstants.FRISBEE_BLOCKING_SERVO_DEFAULT_ANGLE); // Initialize the blocking servo to a default angle
        armBlockingServo.setAngle(Constants.ShooterConstants.ARM_BLOCKING_SERVO_DEFAULT_ANGLE);
        // raisingSolenoid1.set(false); // Initialize the raising solenoid to a default state (off)
        // raisingSolenoid2.set(false); // Initialize the second raising solenoid to a default state (off)
        // Initialize shooter motors, sensors, etc.
    }

    public Command shootFrisbee() {
        return runOnce(() -> {
            frisbeeShootingSolenoid.toggle();}
        );
    }

    public Command toggleLargeShooterPiston() {
        return runOnce(() -> {
            raisingSolenoid1.toggle(); });
    }

    /*
     * This method moves the selected servo to the specified angle.
     * @param angle The desired angle to set the servo to (in degrees).
     * @param servo The servo to be moved (servo1 or servo2).
     * 
     * @return A Command that, when executed, moves the specified servo to the given angle.
     */
    public Command testMoveServo(Double angle, Servo servo) {
        return new Command() {
            @Override
            public void initialize() {
                servo.setAngle(angle); // Set the servo to the desired angle
            }

            @Override
            public boolean isFinished() {
                return false; // Keep running until explicitly canceled
            }

            @Override
            public void end(boolean interrupted) {
                servo.setAngle(0); // Reset the servo to 0 degrees when the command ends
            }
        };
    }

    /*
     * This method sets the output percentage of both shooter motors.
     * @param outputPercentage A DoubleSupplier that provides the desired output percentage (between -1.0 and 1.0).
     * 
     * @return A Command that, when executed, sets the output percentage of both shooter motors.
     *         The motors will stop when the command ends.
     */
    public Command runAtOutput(DoubleSupplier outputPercentage) {
        return runEnd(() -> {
            bigShooter.set((outputPercentage.getAsDouble())); // Set the output percentage for the big shooter motor
            smallShooter.set(outputPercentage.getAsDouble()); // Set the output percentage for the small shooter motor
        }, () -> {
            bigShooter.set(0); // Stop the big shooter motor
            smallShooter.set(0); // Stop the small shooter motor
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("frisbeeBlockingServo Angle", frisbeeBlockingServo.getAngle());
        SmartDashboard.putNumber("armBlockingServo Angle", armBlockingServo.getAngle());
        SmartDashboard.putNumber("Big Shooter Output", bigShooter.get());
        SmartDashboard.putNumber("Small Shooter Output", smallShooter.get());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the behavior of the shooter subsystem
    }


    
}