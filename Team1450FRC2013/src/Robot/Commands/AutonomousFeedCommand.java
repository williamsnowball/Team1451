/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands;
import Robot.Utils.Threading;

/**
 *
 * @author parallels
 */
public class AutonomousFeedCommand extends CommandBase {
    
    private boolean isDone = false;
    private int discs = 3;
    
    public AutonomousFeedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("**** INITITALIZE TEST COMMAND");
        shooter.throttle(-0.75);
        Threading.sleep(2000);
        discs--;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        feeder.feed();
        Threading.sleep(2000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
