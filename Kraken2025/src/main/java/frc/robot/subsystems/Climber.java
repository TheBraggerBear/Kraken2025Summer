package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.thethriftybot.ThriftyNova.ExternalEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    WPI_VictorSPX climberMotor1 = new WPI_VictorSPX(7);
    WPI_VictorSPX climberMotor2 = new WPI_VictorSPX(8);
    WPI_VictorSPX climberMotor3 = new WPI_VictorSPX(9);
    WPI_VictorSPX climberMotor4 = new WPI_VictorSPX(10);
    // Encoder climbEncoder = new Encoder(0, 0);
    
    
    public Climber () {
        // climbEncoder.reset();
        climberMotor1.configOpenloopRamp(0.5);
        climberMotor2.configOpenloopRamp(0.5);
        climberMotor3.configOpenloopRamp(0.5);
        climberMotor4.configOpenloopRamp(0.5);

        climberMotor1.setNeutralMode(NeutralMode.Brake);
        climberMotor2.setNeutralMode(NeutralMode.Brake);
        climberMotor3.setNeutralMode(NeutralMode.Brake);
        climberMotor4.setNeutralMode(NeutralMode.Brake);
        

    }

    public Command raiseClimber(Double power) {
        return run(() -> {
            climberMotor1.set(power);
            climberMotor2.set(power);
            climberMotor3.set(power);
            climberMotor4.set(power);
        });
    }
}
