/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

      CANSparkMax motorOne = new CANSparkMax(Ports.MOTOR_ONE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax motorTwo = new CANSparkMax(Ports.MOTOR_TWO_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Ports.CONTROLLER_CHANNEL);

      @Override
      public void teleopInit() {
        motorOne.setSmartCurrentLimit(4);
        motorOne.setSecondaryCurrentLimit(5);
      }

      @Override
      public void teleopPeriodic() {

        if (controller.getAButton()) {
          motorOne.set(1);
        }

        if (controller.getBButton()) {
          motorTwo.set(1);
        }

      }

}

