package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class IntakeArm extends SubsystemBase{
    // Define motors, sensors, and other components here
    DoubleSolenoid exampleDoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 2);
    
    // public enum State {
    //     RAISED(DoubleSolenoid.Value.kForward), // Intake arm is raised
    //     LOWERED(DoubleSolenoid.Value.kReverse), // Intake arm is lowered
    //     OFF(DoubleSolenoid.Value.kOff); // Intake arm is off

    //     private final DoubleSolenoid.Value value;

    //     public DoubleSolenoid.Value getValue() {
    //         // Getter for the solenoid value
    //         return value;
    //     }

    //     private State(DoubleSolenoid.Value value) {
    //         // Constructor for the State enum
    //         this.value = value;
    //     }
    // }

    // public Command setState(State state) {
    //     return runEnd(() -> this.state = state, () -> this.state = State.LOWERED);
    // }

    public IntakeArm() {
        // Initialize motors, sensors, etc.

    }


    /*
     * This method sets the doubleSolenoid that controls the intake arm to the correct state
     * @param state The desired state of the intake arm (RAISED, LOWERED, OFF)
     */
    public Command setIntakeArm(Value state) {
        return new SequentialCommandGroup(
            new InstantCommand(() -> exampleDoubleSolenoid.set(state)), // Set the solenoid to the desired state
            new WaitCommand(1.0), // Wait for 1 second
            new InstantCommand(() -> exampleDoubleSolenoid.set(Value.kOff)) // Turn the solenoid off
        );
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("IntakeArm/State", exampleDoubleSolenoid.get().toString());
        System.out.println("Intake arm state" + exampleDoubleSolenoid.get().toString());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the intake arm's behavior
    }
    
}
