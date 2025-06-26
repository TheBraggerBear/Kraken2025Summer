package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    VictorSPX frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor;
    // VictorSPXSimProfile frontLeftSim, rearLeftSim, frontRightSim, rearRightSim;
    public DriveTrain() {
        // frontLeftMotor = new VictorSPX(1);
        // rearLeftMotor = new VictorSPX(2);
        // frontRightMotor = new VictorSPX(3);
        // rearRightMotor = new VictorSPX(4);
    }

    public Command drive(double leftSpeed, double rightSpeed) {
        return run(() -> {
            frontLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
            rearLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
            frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
            rearRightMotor.set(ControlMode.PercentOutput, rightSpeed);
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        System.out.println("DriveTrain is running");
        System.out.println("frontLeftMotor:"  + frontLeftMotor.getMotorOutputPercent());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

}
