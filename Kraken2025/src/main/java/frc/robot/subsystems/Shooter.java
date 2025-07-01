package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Shooter {
    WPI_VictorSPX shooterMotor1 = new WPI_VictorSPX(5); // Example motor ID
    WPI_VictorSPX shooterMotor2 = new WPI_VictorSPX(6); // Example motor ID

    public Shooter() {
        // Initialize shooter motors, sensors, etc.
    }
}
