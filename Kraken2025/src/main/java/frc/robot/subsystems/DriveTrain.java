package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class DriveTrain extends SubsystemBase {
    // left motors
    private final WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(0);
    private final WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(1);

    // right motors
    private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(2);
    private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(3);

    private final MotorControllerGroup left = new MotorControllerGroup(leftMotor1, leftMotor2);
    private final MotorControllerGroup right = new MotorControllerGroup(rightMotor1, rightMotor2);

    // drive train
    public final DifferentialDrive driveTrain = new DifferentialDrive(left, right);

    public DriveTrain() {
        leftMotor1.setInverted(true);
        leftMotor1.configOpenloopRamp(0.5);
        leftMotor2.setInverted(true);
        leftMotor2.configOpenloopRamp(0.5);
        rightMotor1.setInverted(false);
        rightMotor1.configOpenloopRamp(0.5);
        rightMotor2.setInverted(false);
        rightMotor2.configOpenloopRamp(0.5);
        leftMotor1.setNeutralMode(NeutralMode.Brake);
        leftMotor2.setNeutralMode(NeutralMode.Brake);
        rightMotor1.setNeutralMode(NeutralMode.Brake);
        rightMotor2.setNeutralMode(NeutralMode.Brake);
    }

    public Command arcadeDrive(double speed, double rotation) {
        return run(() -> driveTrain.arcadeDrive(speed, rotation));
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // Update SmartDashboard with motor outputs and positions
        SmartDashboard.putNumber("DriveTrain/Left Motor 1 Output", leftMotor1.get());
        SmartDashboard.putNumber("DriveTrain/Left Motor 2 Output", leftMotor2.get());
        SmartDashboard.putNumber("DriveTrain/Right Motor 1 Output", rightMotor1.get());
        SmartDashboard.putNumber("DriveTrain/Right Motor 2 Output", rightMotor2.get());
        
    }

}
