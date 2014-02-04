/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands.Camera;

import Robot.Commands.CommandBase;
import RobotMain.IODefines;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author keenan
 */
public class CameraBase extends CommandBase {
    
    public Servo CameraXAxis = new Servo(IODefines.CAMERA_X_AXIS);
    public Servo CameraYAxis = new Servo(IODefines.CAMERA_Y_AXIS);
    public Relay CameraLights = new Relay(IODefines.CAMERA_LIGHTS);
    private double xPos = 0.5;
    private double yPos = 0.5;
    
    public CameraBase() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Initializing camera");
        CameraXAxis.set(xPos);
        CameraYAxis.set(yPos);
        CameraLights.setDirection(Relay.Direction.kForward);
        CameraLights.set(Relay.Value.kOn);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //System.out.println("X: " + oi.getSecondaryJoystickX());
        //System.out.println("Y: " + oi.getSecondaryJoystickY());
        
        //CameraXAxis.set(oi.)
        if (Math.abs(oi.getDriveThrottle())<0.1 && Math.abs(oi.getDriveRotation())<0.1){
            if (Math.abs(oi.getSecondaryJoystickX())>0.07){
                xPos = xPos + oi.getSecondaryJoystickX()/50;
            }
            if (Math.abs(oi.getSecondaryJoystickY())>0.07){
                yPos = yPos + oi.getSecondaryJoystickY()/50;
            }
        } else {
            yPos = .7;
            xPos = (((oi.getDriveRotation()*-1)/2)+1)/2;
        }
        if (xPos>1){
            xPos = 1;
        }
        if (xPos<0){
            xPos = 0;
        }
        if (yPos>1){
            yPos = 1;
        }
        if (yPos<0){
            yPos = 0;
        }
        
        CameraXAxis.set(xPos);
        CameraYAxis.set(yPos);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        CameraLights.set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
