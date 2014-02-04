/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import Robot.Utils.Threading;
import RobotMain.IODefines;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import Robot.Commands.GrappleDisableCommand;
import Robot.Commands.GrappleEnableCommands;
/**
 * @author keenan
 */
public class Grapple extends Subsystem {
    private Servo Grapple = new Servo(IODefines.GRAPPLE_FRONT_SERVO); 
    public boolean Engaged = false;
    protected void initDefaultCommand(){
        setDefaultCommand(new GrappleDisableCommand());
    }
    
    public void retract(){
        Grapple.setAngle(0);
        Engaged = false;
    }
    
    public void engage(){
        System.out.println("Engaging grapple");
        Grapple.setAngle(150);
        Engaged = true;
    }
    
}
