package Robot.Commands.Drive;

import Robot.Commands.CommandBase;

/**
 */
public class DriveWithJoystick extends CommandBase {

    public DriveWithJoystick() {
        requires(driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        driveTrain.arcadeDrive(oi.getDriveThrottle()*-0.7, oi.getDriveRotation()0.6);
    else 
        driveTrain.arcadeDrive(oi.getDriveThrottle()*-0.0, oi.getDriveRotation()0.0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
