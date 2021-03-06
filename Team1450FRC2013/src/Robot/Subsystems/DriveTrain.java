package Robot.Subsystems;

import Robot.Commands.Drive.DriveWithJoystick;
import RobotMain.Constants;
import RobotMain.IODefines;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 */
public class DriveTrain extends Subsystem {

    private RobotDrive robotDrive = new RobotDrive(IODefines.LEFT_DRIVE,IODefines.LEFT_SECONDARY_DRIVE,IODefines.RIGHT_DRIVE,IODefines.RIGHT_SECONDARY_DRIVE);
   // private Encoder rightEncoder = new Encoder(IODefines.RIGHT_DRIVE_ENCODER_A,IODefines.RIGHT_DRIVE_ENCODER_B);
    //private Encoder leftEncoder = new Encoder(IODefines.LEFT_DRIVE_ENCODER_A,IODefines.LEFT_DRIVE_ENCODER_B);
/**  originally switched around*/
    public DriveTrain() {
      
       // leftEncoder.setReverseDirection(true);
       // leftEncoder.setDistancePerPulse(Constants.Drives.distancePerPulse);
       // rightEncoder.setDistancePerPulse(Constants.Drives.distancePerPulse);
    }
/**originally switched around*/
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }

    public void arcadeDrive(double throttle, double rotation) {
        System.out.println("Driving driving driving");
        robotDrive.arcadeDrive(throttle*-1.0,rotation*.65);
    }
}