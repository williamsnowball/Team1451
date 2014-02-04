/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Subsystems;

import Robot.Commands.Feeder.FeederOffCommand;
import Robot.Utils.Threading;
import RobotMain.IODefines;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 *
 * @author brian
 */
public class Feeder extends Subsystem {
    
    private Jaguar feederMotor = new Jaguar(IODefines.FEEDER_MOTOR);
    //private DigitalInput feederLimit = new DigitalInput(IODefines.FEEDER_LIMIT_SWITCH);
    boolean feeding;
    
    protected void initDefaultCommand() {
        setDefaultCommand(new FeederOffCommand());
    }
    
    public void feed() {
        
        //feederLimit.
        feederMotor.set(-.6);
       
        
    }
    
    public boolean limitEngaged(){
        return true;//feederLimit.get();
    }
    
    public void unfeed()  {
        feederMotor.set(.7);
    }
    
    public void off()  {
        feederMotor.set(0.0);
    }
}
