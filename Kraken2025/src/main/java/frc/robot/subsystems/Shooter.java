package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    public WPI_VictorSPX bigShooter = new WPI_VictorSPX(5); // Example motor ID
    public WPI_VictorSPX smallShooter = new WPI_VictorSPX(6); // Example motor ID
    Solenoid exampleSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    Solenoid exampleSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
    public Servo frisbeeBlockingServo = new Servo(0); // Example servo for shooter angle adjustment, adjust port as needed
    public Servo armBlockingServo = new Servo(1); // Example second servo for shooter angle adjustment, adjust port as needed

    public Shooter() {
        bigShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        smallShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        bigShooter.setInverted(true);
        smallShooter.setInverted(true);

        exampleSolenoid.set(false);
        exampleSolenoid2.set(false);
        frisbeeBlockingServo.setAngle(90);
        armBlockingServo.setAngle(0);
        // raisingSolenoid1.set(false); // Initialize the raising solenoid to a default state (off)
        // raisingSolenoid2.set(false); // Initialize the second raising solenoid to a default state (off)\
        // Initialize shooter motors, sensors, etc.
    }

    public Command fireSolenoid1() {
        return runOnce(() -> {
        exampleSolenoid.toggle();}
        );
    }

    public Command fireSolenoid2 () {
        return runOnce(() -> {
        exampleSolenoid2.toggle(); });
    }


    /*
     * This method moves the selected servo to the specified angle.
     * @param angle The desired angle to set the servo to (in degrees).
     * @param servo The servo to be moved (servo1 or servo2).
     * 
     * @return A Command that, when executed, moves the specified servo to the given angle.
     */
    public Command testMoveServo(Double angle, Servo servo) {
        return runEnd(() -> {
            servo.setAngle(angle); // Set the angle of the second servo
        }, () -> {
            servo.setAngle(0); // Reset the servo to 90 degrees when the command ends
        });
    }



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
