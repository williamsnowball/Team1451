/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot.Devices;

import Robot.Utils.DrivePIDOutput;
import Robot.Utils.PIDTuner;
import Robot2011.Constants;
import Robot2011.IODefines;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author chad
 */
public class Elevator implements PIDSource {

    private Joystick stick;
    private Thread m_task;
    private PIDController pid;
    private DriverStationLCD myStationLCD = DriverStationLCD.getInstance();
    private PIDTuner pidTuner;
    private double autoCommandTarget;
    private boolean autoCommandMode;
    private boolean pidMode;

    private DigitalInput limitUp = new DigitalInput(IODefines.ELEVATOR_LIMIT_UP);
    private DigitalInput limitDown = new DigitalInput(IODefines.ELEVATOR_LIMIT_DOWN);
    private SpeedController drives = new Jaguar(IODefines.ELEVATOR_DRIVE);
    private Encoder encoder = new Encoder(IODefines.ELEVATOR_DRIVE_ENCODER_A,
            IODefines.ELEVATOR_DRIVE_ENCODER_B);
    private DrivePIDOutput elevatorPIDOutput = new DrivePIDOutput(drives, limitUp, limitDown, true, "Elevator");

    public Elevator(Joystick _stick) {
        stick = _stick;
        encoder.setDistancePerPulse(Constants.Elevator.distancePerPulse);

        pid = new PIDController(0.16, 0, 0, this, elevatorPIDOutput);
        pid.setTolerance(1);
        pid.setOutputRange(-1, 1);
        pidTuner = new PIDTuner(pid, stick, .04, 0, 0);
        m_task = new ElevatorThread(this);
    }

    private class ElevatorThread extends Thread {

        private Elevator elevator;
        private boolean m_run = true;

        ElevatorThread(Elevator _elevator) {
            elevator = _elevator;
        }

        public void run() {
            while (m_run) {
                elevator.run();

                try {
                    Thread.sleep(Constants.Elevator.loopTime);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void start() {
        encoder.reset();
        //encoder.setReverseDirection(true);
        encoder.start();
        m_task.start();
        pidMode = true;
        pid.enable();
        pidTuner.start();
        elevatorPIDOutput.start();

        setManualPosition(Constants.Elevator.initialPosition);
    }

    private double getUserInput() {
        return (1 - stick.getZ()) / 2;
    }

    private void setPidTarget(double target) {
        pid.setSetpoint(target);
    }

    public void setManualPosition(double position) {
        setAutoCommandMode();
        if (position < Constants.Elevator.lowerLimit) {
            autoCommandTarget = Constants.Elevator.lowerLimit;
        }
        else if (position > Constants.Elevator.upperLimit) {
            autoCommandTarget = Constants.Elevator.upperLimit;
        }
        else {
            autoCommandTarget = position;
        }
    }

    public void rehome() {
        pid.disable();
        pid.setOutputRange(-0.3, 0.3);
        autoCommandTarget = -Constants.Elevator.upperLimit * 2;
        pid.enable();

        while (limitDown.get()) {

        }

        encoder.reset();
        pid.disable();
        autoCommandTarget = 0;
        pid.setOutputRange(-1, 1);
        pid.enable();
    }

    private void goAllTheWayDown() {
        //Requires testing before use.
        //If it works reliably, we can call this in rehome()
        //Hopefully, this can be called whenever user command is < 2
        pid.disable();

        drives.set(1);
        while (limitDown.get()) {
            if (encoder.get() < 5.0 && drives.get() != 0.5)  {
                drives.set(0.5);
            }
            else if(encoder.get() < 1.0  && drives.get() != 0.2) {
                drives.set(0.2);
            }
        }

        encoder.reset();
        autoCommandTarget = 0;
        pid.setOutputRange(-1, 1);
        pid.reset();
        pid.enable();
    }

    private void goAllTheWayUp() {
        //Requires testing before use.
        //Hopefully, this can be called whenever user command is > 40
        pid.disable();

        drives.set(-1);
        while (limitDown.get()) {
            if (encoder.get() > Constants.Elevator.upperLimit - 5.0
                    && drives.get() != -0.5)  {
                drives.set(-0.5);
            }
            else if(encoder.get() > Constants.Elevator.upperLimit - 1.0
                    && drives.get() != -0.2) {
                drives.set(-0.2);
            }
        }

        //wish we could encoder.set(Constants.Elevator.upperLimit) here,
        //    maybe we need an encoder wrapper?
        autoCommandTarget = Constants.Elevator.upperLimit;
        pid.setOutputRange(-1, 1);
        pid.reset();
        pid.enable();
    }

    public void setAutoCommandMode() {
        autoCommandMode = true;
    }

    public void setUserCommandMode() {
        autoCommandMode = false;
    }

    public void atUpperLimit() {
        System.out.println("Elevator Upper Limit.");
    }

    public void atLowerLimit() {
        System.out.println("Elevator Lower Limit.");
    }

    private void run() {
        double driveTarget;
        if (autoCommandMode) {
            driveTarget = autoCommandTarget;
        }
        else {
            driveTarget = (getUserInput() * Constants.Elevator.distanceToTop);
        }
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4,
                1, "Elevator Target = " + driveTarget);
        DriverStationLCD.getInstance().updateLCD();
        setPidTarget(driveTarget);
    }

    public double pidGet() {
        myStationLCD.println(DriverStationLCD.Line.kUser5, 1,
                "Elevator Height = " + encoder.getDistance());
        myStationLCD.updateLCD();
        return encoder.getDistance();
    }

    public void disable() {
        drives.disable();
    }
}
