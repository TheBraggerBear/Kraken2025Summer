package frc.robot.subsystems;

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
    WPI_VictorSPX bigShooter = new WPI_VictorSPX(5); // Example motor ID
    WPI_VictorSPX smallShooter = new WPI_VictorSPX(6); // Example motor ID
    // Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 3); // Example solenoid ID, adjust as needed
    // Solenoid raisingSolenoid1 = new Solenoid(PneumaticsModuleType.REVPH, 4); // Example solenoid for raising mechanism
    // Solenoid raisingSolenoid2 = new Solenoid(PneumaticsModuleType.REVPH, 5); // Example second solenoid for raising mechanism
    DoubleSolenoid exampleDoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 2);
    Solenoid exampleSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    Solenoid exampleSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
    Servo servo1 = new Servo(0); // Example servo for shooter angle adjustment, adjust port as needed
    Servo servo2 = new Servo(1); // Example second servo for shooter angle adjustment, adjust port as needed

    public Shooter() {
        bigShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        smallShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        bigShooter.setInverted(true);
        smallShooter.setInverted(true);
        exampleDoubleSolenoid.set(Value.kOff); // Initialize the solenoid to a default state (off)
        exampleSolenoid.set(false);
        exampleSolenoid2.set(false);
        servo1.setAngle(90);
        servo2.setAngle(0);
        // raisingSolenoid1.set(false); // Initialize the raising solenoid to a default state (off)
        // raisingSolenoid2.set(false); // Initialize the second raising solenoid to a default state (off)\
        // Initialize shooter motors, sensors, etc.
    }

    public Command changeState() {
        return runOnce(() -> {
            exampleDoubleSolenoid.set(Value.kForward);
            // raisingSolenoid2.toggle(); // Toggle the state of the raising solenoids
        });
    }
    public Command changeState2() {
        return runOnce(() -> {
            exampleDoubleSolenoid.set(Value.kReverse);
            // raisingSolenoid2.toggle(); // Toggle the state of the raising solenoids
        });
    }    
    public Command changeState3() {
        
        return runOnce(() -> {
            exampleDoubleSolenoid.set(Value.kOff);
            // raisingSolenoid2.toggle(); // Toggle the state of the raising solenoids
        });
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

    public Command moveServo(Double angle) {
        return runOnce(() -> {
            servo1.setAngle(angle); // Set the angle of the second servo
        });
    }

    public Command moveServo2(Double angle) {
        return runOnce(() -> {
            servo2.setAngle(angle); // Set the angle of the second servo
        });
    }

    public Command launchFrisbee() {
        return runOnce(() -> {
            // shooterSolenoid.set(true); // Activate the shooter solenoid to launch the frisbee
        }).withTimeout(0.2).andThen(runOnce(() -> {
            // shooterSolenoid.set(false); // Deactivate the shooter solenoid after launching
        }));
    }
    public Command halfSpeed() {
        // Set the speed of the shooter motors
        return new Command() {
            @Override
            public void initialize() {
                bigShooter.set(0.5); // Set speed for shooter motor 1
                smallShooter.set(0.5); // Set speed for shooter motor 2
            }

            @Override
            public boolean isFinished() {
                return false; // This command runs indefinitely until interrupted
            }

            @Override
            public void end(boolean interrupted) {
                bigShooter.set(0); // Stop the motors when the command ends
                smallShooter.set(0);
            }
        };
    }

    public Command fullSpeed() {
        return new Command() {
            @Override
            public void initialize() {
                bigShooter.set(1.0);
                smallShooter.set(1.0);
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public void end(boolean interrupted) {
                bigShooter.set(0);
                smallShooter.set(0);
            }
        };
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        System.out.println("Servo1" + servo1.getAngle());
        System.out.println("Servo2" + servo2.getAngle());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the behavior of the shooter subsystem
        // SmartDashboard.putBoolean("Solenoid1", exampleSolenoid.get());
        // SmartDashboard.putBoolean("Solenoid2", exampleSolenoid2.get());
        SmartDashboard.putString("doubleSolenoid", exampleDoubleSolenoid.get().toString());
        // SmartDashboard.putBoolean("shooterSolenoid", shooterSolenoid.get());
        // SmartDashboard.putNumber("Servo1 Position", servo1.get());
        // SmartDashboard.putNumber("Servo2 Position", servo2.get());
    }


    
}
