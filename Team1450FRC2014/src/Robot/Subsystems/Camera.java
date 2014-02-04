/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Subsystems;

import Robot.Commands.Camera.CameraBase;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author keenan
 */
public class Camera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //setDefaultCommand()
        
        setDefaultCommand(new CameraBase());
    }
}
