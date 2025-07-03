package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeArm extends SubsystemBase{
    // Define motors, sensors, and other components here
    DoubleSolenoid intakeArmSolenoid; // Example solenoid for the intake arm
    DoubleSolenoid intakeArmSolenoid2; // Example second solenoid for the intake arm
    private State state = State.LOWERED; // Initial state of the intake arm

    public enum State {
        RAISED(DoubleSolenoid.Value.kForward), // Intake arm is raised
        LOWERED(DoubleSolenoid.Value.kReverse); // Intake arm is lowered

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
        intakeArmSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH ,7, 8); // Example ports for the solenoid
        intakeArmSolenoid2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 9, 10); // Example second solenoid ports
    }

    // Define methods to control the intake arm
    public Command raise() {
        return run(() -> {
            intakeArmSolenoid.set(DoubleSolenoid.Value.kForward); // Extend the intake arm
            intakeArmSolenoid2.set(DoubleSolenoid.Value.kForward); // Extend the second intake arm
        });
    }

    public Command lower() {
        return run(() -> {
            intakeArmSolenoid.set(DoubleSolenoid.Value.kReverse); // Retract the intake arm
            intakeArmSolenoid2.set(DoubleSolenoid.Value.kReverse); // Retract the second intake arm
        });
    }

    public Command setState(State state) {
        return runEnd(() -> this.state = state, () -> this.state = State.LOWERED);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // You can add code here to check the state of the intake arm or update its position
        System.out.println("IntakeArm is running");
        if (state == State.RAISED) {
            raise(); // Call the raise method to extend the intake arm
        } else if (state == State.LOWERED) {
            lower();
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the intake arm's behavior
        System.out.println("IntakeArm simulation is running");
        SmartDashboard.putString("IntakeArm/armSolenoid1", intakeArmSolenoid.get().toString());
        SmartDashboard.putString("IntakeArm/armSolenoid2", intakeArmSolenoid2.get().toString());
    }
    
}
