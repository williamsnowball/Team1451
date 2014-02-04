/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands;

import Robot.Commands.CommandBase;
import Robot.Utils.Threading;
/**
 *
 * @author keenan
 */
public class testAutonomous extends CommandBase {
    public int discs = 4;
    public CommandBase myCommand = new TeleopFeedCommand();
    public testAutonomous() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.throttle(-0.75);
        Threading.sleep(4000);       //delay();
    }

    private void delay(){
        try {
                Threading.sleep(2000);}
                catch (Exception ex) {}
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.throttle(-0.75);
        if (!myCommand.isRunning() && discs>0){
            
            System.out.println("Firing disc " + discs);
            myCommand.start();
            delay();
            discs--;
        }
        
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("Discs: " + discs);
        return discs<=0;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("End?????");
        delay();
        shooter.throttle(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //shooter.throttle(0);
    }
}
