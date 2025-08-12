package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    WPI_VictorSPX climberMotor1 = new WPI_VictorSPX(Constants.ClimberConstants.CLIMBER_MOTOR_1_ID);
    WPI_VictorSPX climberMotor2 = new WPI_VictorSPX(Constants.ClimberConstants.CLIMBER_MOTOR_2_ID);
    WPI_VictorSPX climberMotor3 = new WPI_VictorSPX(Constants.ClimberConstants.CLIMBER_MOTOR_3_ID);
    WPI_VictorSPX climberMotor4 = new WPI_VictorSPX(Constants.ClimberConstants.CLIMBER_MOTOR_4_ID);
    AnalogPotentiometer climberPotentiometer = new AnalogPotentiometer(Constants.ClimberConstants.CLIMBER_POTENTIOMETER_CHANNEL, Constants.ClimberConstants.CLIMBER_POTENTIOMETER_MAX_ANGLE, 0);    
    
    public Climber () {
        climberMotor1.configOpenloopRamp(Constants.ClimberConstants.OPEN_LOOP_RAMP);
        climberMotor2.configOpenloopRamp(Constants.ClimberConstants.OPEN_LOOP_RAMP);
        climberMotor3.configOpenloopRamp(Constants.ClimberConstants.OPEN_LOOP_RAMP);
        climberMotor4.configOpenloopRamp(Constants.ClimberConstants.OPEN_LOOP_RAMP);

        climberMotor1.setNeutralMode(Constants.ClimberConstants.NEUTRAL_MODE);
        climberMotor2.setNeutralMode(Constants.ClimberConstants.NEUTRAL_MODE);
        climberMotor3.setNeutralMode(Constants.ClimberConstants.NEUTRAL_MODE);
        climberMotor4.setNeutralMode(Constants.ClimberConstants.NEUTRAL_MODE);
    }

    public Command moveClimber(Double power) {
        return runEnd(() -> {
            // climberMotor1.set(power);
            climberMotor2.set(power);
            climberMotor3.set(power);
            climberMotor4.set(power);
        }, () -> {
            // climberMotor1.set(0);
            climberMotor2.set(0);
            climberMotor3.set(0);
            climberMotor4.set(0);
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