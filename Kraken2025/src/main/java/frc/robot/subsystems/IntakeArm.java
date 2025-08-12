package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase{
    // Define motors, sensors, and other components here
    DoubleSolenoid armDoubleSolenoid = new DoubleSolenoid(Constants.PnuematicConstants.PNUEMATIC_MODULE_TYPE, Constants.IntakeArmConstants.ARM_DOUBLESOLENOID_FORWARD_CHANNEL, Constants.IntakeArmConstants.ARM_DOUBLESOLENOID_REVERSE_CHANNEL);
    
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
        armDoubleSolenoid.set(Constants.PnuematicConstants.DEFAULT_DOUBLESOLENOID_STATE); // Initialize the double solenoid to the off state
    }


    /*
     * This method sets the doubleSolenoid that controls the intake arm to the correct state
     * @param state The desired state of the intake arm (RAISED, LOWERED, OFF)
     */
    public Command setIntakeArm(Value state) {
        return new SequentialCommandGroup(
            new InstantCommand(() -> armDoubleSolenoid.set(state)), // Set the solenoid to the desired state
            new WaitCommand(2.5), // Wait for 2.5 seconds
            new InstantCommand(() -> armDoubleSolenoid.set(Constants.PnuematicConstants.DEFAULT_DOUBLESOLENOID_STATE)) // Turn the solenoid to a default state (off state) after the wait
        );
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("IntakeArm/State", armDoubleSolenoid.get().toString());
        System.out.println("Intake arm state" + armDoubleSolenoid.get().toString());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the intake arm's behavior
    }
    
}