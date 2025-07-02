package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;

public class Shooter {
    WPI_VictorSPX bigShooter = new WPI_VictorSPX(5); // Example motor ID
    WPI_VictorSPX smallShooter = new WPI_VictorSPX(6); // Example motor ID

    public Shooter() {
        bigShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        smallShooter.setNeutralMode(NeutralMode.Coast); // Set the neutral mode for the shooter motors
        bigShooter.setInverted(true);
        smallShooter.setInverted(true);
        // Initialize shooter motors, sensors, etc.
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


    
}
