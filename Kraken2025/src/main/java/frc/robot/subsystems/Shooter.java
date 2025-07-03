package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    WPI_VictorSPX bigShooter = new WPI_VictorSPX(5); // Example motor ID
    WPI_VictorSPX smallShooter = new WPI_VictorSPX(6); // Example motor ID
    Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.REVPH, 3); // Example solenoid ID, adjust as needed
    Solenoid raisingSolenoid1 = new Solenoid(PneumaticsModuleType.REVPH, 4); // Example solenoid for raising mechanism
    Solenoid raisingSolenoid2 = new Solenoid(PneumaticsModuleType.REVPH, 5); // Example second solenoid for raising mechanism
    // Servo servo = new Servo(0); // Example servo for shooter angle adjustment, adjust port as needed
    // Servo servo2 = new Servo(1); // Example second servo for shooter angle adjustment, adjust port as needed

    public Shooter() {
        bigShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        smallShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        bigShooter.setInverted(true);
        smallShooter.setInverted(true);
        shooterSolenoid.set(false); // Initialize the solenoid to a default state (off)
        raisingSolenoid1.set(false); // Initialize the raising solenoid to a default state (off)
        raisingSolenoid2.set(false); // Initialize the second raising solenoid to a default state (off)
        // Initialize shooter motors, sensors, etc.
    }

    public Command changeState() {
        return runOnce(() -> {
            raisingSolenoid1.toggle();
            raisingSolenoid2.toggle(); // Toggle the state of the raising solenoids
        });
    }

    public Command launchFrisbee() {
        return runOnce(() -> {
            shooterSolenoid.set(true); // Activate the shooter solenoid to launch the frisbee
        }).withTimeout(0.2).andThen(runOnce(() -> {
            shooterSolenoid.set(false); // Deactivate the shooter solenoid after launching
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
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the behavior of the shooter subsystem
        SmartDashboard.putBoolean("raisingSolenoid1", raisingSolenoid1.get());
        SmartDashboard.putBoolean("raisingSolenoid2", raisingSolenoid2.get());
        SmartDashboard.putBoolean("shooterSolenoid", shooterSolenoid.get());
    }


    
}
