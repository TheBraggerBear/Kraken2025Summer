package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeArm extends SubsystemBase{
    // Define motors, sensors, and other components here
    Solenoid intakeArmSolenoid; // Example solenoid for the intake arm
    Solenoid intakeArmSolenoid2; // Example second solenoid for the intake arm
    private State state = State.LOWERED; // Initial state of the intake arm

    public enum State {
        RAISED(DoubleSolenoid.Value.kForward), // Intake arm is raised
        LOWERED(DoubleSolenoid.Value.kReverse), // Intake arm is lowered
        OFF(DoubleSolenoid.Value.kOff); // Intake arm is off

        private final DoubleSolenoid.Value value;

        public DoubleSolenoid.Value getValue() {
            // Getter for the solenoid value
            return value;
        }

        private State(DoubleSolenoid.Value value) {
            // Constructor for the State enum
            this.value = value;
        }
    }
    public IntakeArm() {
        // Initialize motors, sensors, etc.
        intakeArmSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM , 0); // Example ports for the solenoid
        intakeArmSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, 1); // Example second solenoid ports
        // Set the initial state of the intake arm
        intakeArmSolenoid.set(false); // Set the solenoid to off initially
        intakeArmSolenoid2.set(false); // Set the second solenoid to off initially
    }

    public Command setState(State state) {
        return runEnd(() -> this.state = state, () -> this.state = State.LOWERED);
    }

    public Command changeState() {
        return runOnce(() -> {
            intakeArmSolenoid.toggle();
            intakeArmSolenoid2.toggle(); // Toggle the state of the intake arm solenoids
        });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // You can add code here to check the state of the intake arm or update its position
        if (state == State.RAISED) {
            // intakeArmSolenoid.set(state.getValue()); // Set the solenoid to raise the intake arm
            // intakeArmSolenoid2.set(state.getValue()); // Set the second solenoid to raise the intake arm
        } else if (state == State.LOWERED) {
            // intakeArmSolenoid.set(state.getValue()); // Set the solenoid to lower the intake arm
            // intakeArmSolenoid2.set(state.getValue()); // Set the second solenoid to lower the intake arm
        } else {
            // intakeArmSolenoid.set(state.getValue()); // Turn off the solenoid
            // intakeArmSolenoid2.set(state.getValue()); // Turn off the second solenoid
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the intake arm's behavior
        SmartDashboard.putBoolean("IntakeArm/armSolenoid1", intakeArmSolenoid.get());
        SmartDashboard.putBoolean("IntakeArm/armSolenoid2", intakeArmSolenoid2.get());
    }
    
}
