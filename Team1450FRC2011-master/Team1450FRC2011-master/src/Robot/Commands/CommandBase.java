package Robot.Commands;

import Robot.Subsystems.*;
import RobotMain.OperatorInterface;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system.
 */
public abstract class CommandBase extends Command {

    public static OperatorInterface oi;

    // Create a single static instance of all of your subsystems
    public static Tongue tongue = new Tongue();
    public static DriveTrain driveTrain = new DriveTrain();
    public static Shooter shooter = new Shooter();
    public static Waist waist = new Waist();
    public static Ramp ramp = new Ramp();
    public static Camera camera = new Camera();
    public static Trigger trigger = new Trigger();
    public static ShooterArc shooterArc = new ShooterArc();

    public static void init() {
        // This MUST be here. If the OperatorInterface creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OperatorInterface();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(tongue);
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(waist);
        SmartDashboard.putData(ramp);
        SmartDashboard.putData(camera);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(trigger);
        SmartDashboard.putData(shooterArc);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
