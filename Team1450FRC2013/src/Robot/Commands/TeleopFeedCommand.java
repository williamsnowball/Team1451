/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands;

import Robot.Utils.Threading;

/**
 *
 * @author keenan
 */
public class TeleopFeedCommand extends CommandBase {
    
    private boolean startloop=false;
    
    public TeleopFeedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(grapple);
        requires(feeder);
        
    }
    
    protected void initialize(){
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //grapple.retract();
        feeder.feed();
        
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //if (feeder.)
        if (!feeder.limitEngaged()){
            startloop=true;
        }
        return feeder.limitEngaged() && startloop;
    }

    // Called once after isFinished returns true
    protected void end() {
        feeder.off();
        startloop=false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //feeder.off();
    }
}
