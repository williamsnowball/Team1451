/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package Robot2011;

import Robot.Devices.DrivePlatform;
import Robot.Devices.SolenoidSwitcher;
import Robot.Devices.Elevator;
import Robot.Devices.Wrist;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BotMain extends SimpleRobot {
    //Joystick Instantiation (shared between devices)
    private Joystick joy1 = new Joystick(1);
    private Joystick joy2 = new Joystick(2);

    //Device Instantiation
    DrivePlatform drives = new DrivePlatform(joy1);
    Wrist wrist = new Wrist(joy2);
    Elevator elevator = new Elevator(joy1);

    //Compressor
    private Compressor compressor = new Compressor(IODefines.PRESSURE_SWITCH,
            IODefines.COMPRESSOR_POWER);

  

    //Gripper
    private Solenoid gripperGrab = new Solenoid(IODefines.GRIPPER_GRAB);
    private Solenoid gripperRelease = new Solenoid(IODefines.GRIPPER_RELEASE);
    SolenoidSwitcher gripperGuy = new SolenoidSwitcher(gripperGrab,
            gripperRelease,
            joy2,
            IODefines.GRIPPER_GRAB_BUTTON,
            IODefines.GRIPPER_RELEASE_BUTTON);

//    //Minibot
//    private Solenoid miniBotDeploy = new Solenoid(IODefines.MINI_BOT_DEPLOY);
//    private Solenoid miniBotRetract = new Solenoid(IODefines.MINI_BOT_RETRACT);
//    SolenoidSwitcher miniBotGuy = new SolenoidSwitcher(miniBotDeploy,
//            miniBotRetract,
//            joy2,
//            IODefines.MINI_BOT_DEPLOY_BUTTON,
//            IODefines.MINI_BOT_RETRACT_BUTTON);
    
    public BotMain() {
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        System.out.println("Entering autonomous control.");
        elevator.setManualPosition(Constants.Elevator.initialPosition);
        wrist.setManualPosition(Constants.Wrist.initialPosition);
        System.out.println("Wrist and elevator set to initial positions.");
        elevator.setManualPosition(Constants.Elevator.scoringPosition);
        System.out.println("Elevator in scoring position");
        wrist.setManualPosition(Constants.Wrist.upPosition);
        drives.goToScoringRack();
        System.out.println("At scoring rack");
        wrist.setManualPosition(Constants.Wrist.scoringPosition);
        System.out.println("Wrist in scoring position.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Sleep timer caught some exception.");
        }
        System.out.println("Dropping yellow tube!");
        gripperGuy.actuate(gripperRelease);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Sleep timer caught some exception.");
        }
        System.out.println("Backing up the bot.");
        drives.backOffScoringRack();
        System.out.println("Setting wrist and elevator back to initial positions.");
        wrist.setManualPosition(Constants.Wrist.initialPosition);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Second sleep timer caught some exception.");
        }
        elevator.setManualPosition(Constants.Elevator.initialPosition);
        System.out.println("Autonomous Complete.");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        if(joy2.getRawButton(1))
        {
            elevator.rehome();
        }

        System.out.println("Operator Control!");
        elevator.setUserCommandMode();
        wrist.setUserCommandMode();
        drives.setUserCommandMode();
    }

    public void disabled() {
        drives.disable();
        wrist.disable();
        elevator.disable();
    }

    protected void robotInit() {
        System.out.println("Disabling motors.");
        drives.disable();
        wrist.disable();
        elevator.disable();

        System.out.println("Starting solenoid guys");
        gripperGuy.start();
        //shoulderGuy.start();
        //transmissionGuy.start();
        //miniBotGuy.start();
        //miniBotGuy.actuate(miniBotRetract);

        System.out.println("Setting actuation position");
        gripperGuy.actuate(gripperGrab);
        //shoulderGuy.actuate(shoulderDown);
        //transmissionGuy.actuate(driveHighGear);

        System.out.println("Starting devices");
        wrist.start();
        System.out.println("Wrist started");
        elevator.start();
        System.out.println("Elevator started");
        compressor.start();
        System.out.println("Compressor started");
        drives.start();
        System.out.println("Drives started");
    }
}
