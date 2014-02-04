/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Robot.Commands;

import Robot.Commands.CommandBase;
/**
 *
 * @author keenan
 */
public class GrappleEnableCommands extends CommandBase {
    
    public GrappleEnableCommands(){
            requires(grapple);
    }

    protected void initialize() {
    }

    protected void execute() {
        grapple.engage();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}


