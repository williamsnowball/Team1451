/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Commands;

/**
 *
 * @author keenan
 */
public class GrappleDisableCommand extends CommandBase{
    public GrappleDisableCommand(){
        requires(grapple);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        grapple.retract();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
    }
}
