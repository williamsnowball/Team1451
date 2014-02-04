/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands.Feeder;

import Robot.Commands.CommandBase;

/**
 *
 * @author keenan
 */
public class FeederFeedCommand extends CommandBase {

    public FeederFeedCommand() {
        requires(feeder);
        requires(grapple);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (grapple.Engaged){
            grapple.retract();
        }
        feeder.feed();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
