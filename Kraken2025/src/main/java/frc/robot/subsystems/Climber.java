package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    WPI_VictorSPX climberMotor1 = new WPI_VictorSPX(7);
    WPI_VictorSPX climberMotor2 = new WPI_VictorSPX(8);
    WPI_VictorSPX climberMotor3 = new WPI_VictorSPX(9);
    WPI_VictorSPX climberMotor4 = new WPI_VictorSPX(10);
    AnalogPotentiometer climberPotentiometer = new AnalogPotentiometer(0, 3600, 0);    
    
    public Climber () {
        climberMotor1.configOpenloopRamp(0.5);
        climberMotor2.configOpenloopRamp(0.5);
        climberMotor3.configOpenloopRamp(0.5);
        climberMotor4.configOpenloopRamp(0.5);

        climberMotor1.setNeutralMode(NeutralMode.Coast);
        climberMotor2.setNeutralMode(NeutralMode.Coast);
        climberMotor3.setNeutralMode(NeutralMode.Coast);
        climberMotor4.setNeutralMode(NeutralMode.Coast);
        

    }

    public Command raiseClimber(Double power) {
        return runOnce(() -> {
            // climberMotor1.set(power);
            climberMotor2.set(power);
            climberMotor3.set(power);
            climberMotor4.set(power);
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("climberPotentiometer", climberPotentiometer.get());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // Update SmartDashboard with motor outputs and positions

    }
}
