/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package RobotMain;

import Robot.Commands.CommandBase;
import Robot.Commands.Shooter.OperatorControlShooterCommand;
import Robot.Commands.AutonomousFeedCommand;
import Robot.Commands.Winch.WinchOffCommand;
import Robot.Utils.Threading;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import Robot.Commands.testAutonomous;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//CHADBARBER
public class BotMain extends IterativeRobot {

    private Command autoCommand;
    private Command autonomousFeedCommand;
    private Command Autonomous;

    
    //Compressor
    private Compressor compressor = new Compressor(IODefines.PRESSURE_SWITCH, IODefines.COMPRESSOR_POWER);
    
    public void robotInit() {
        CommandBase.init();
        //autoCommand = new WinchOffCommand();
        //autonomousFeedCommand = new AutonomousFeedCommand();
        //Autonomous = new testAutonomous();
        System.out.println("Starting compressor");
        compressor.start();
        //compressor.enabled();
        System.out.println("Compressor enabled: " + compressor.enabled());
    }
    public void autonomousInit() {
        // schedule the autonomous command (example)
        //autonomousFeedCommand.start();
        Autonomous.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
       autoCommand.cancel();
       new OperatorControlShooterCommand().start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Initialization of disabled code.
     */
    public void disabledInit() {
    }
}
