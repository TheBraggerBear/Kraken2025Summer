package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AirCompressor extends SubsystemBase {
    // This subsystem is responsible for managing the air compressor.
    // It can be used to control the compressor's state, monitor pressure, etc.
    Compressor compressor;

    public AirCompressor() {
        // Initialize the compressor here if needed
        // For example, set up sensors or configure the compressor settings
        compressor = new Compressor(PneumaticsModuleType.REVPH); // Use the REVPH module type for the compressor
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // You can add code here to check the compressor status or update its state
    }
    
}
