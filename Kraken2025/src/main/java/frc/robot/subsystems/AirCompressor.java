package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AirCompressor extends SubsystemBase {
    // This subsystem is responsible for managing the air compressor.
    // It can be used to control the compressor's state, monitor pressure, etc.
    Compressor compressor;

    public AirCompressor() {
        // Initialize the compressor here if needed
        // For example, set up sensors or configure the compressor settings
        compressor = new Compressor(PneumaticsModuleType.CTREPCM); // Use the REVPH module type for the compressor
        compressor.enableDigital(); // Enable the compressor to r0un

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // You can add code here to check the compressor status or update its state
    }
    
    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
        // You can add code here to simulate the compressor behavior if needed
    }
}
