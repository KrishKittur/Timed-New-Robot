/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpiutil.math.MathUtil;

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
      CANSparkMax motorThree = new CANSparkMax(Ports.MOTOR_THREE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax motorFour = new CANSparkMax(Ports.MOTOR_FOUR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Ports.CONTROLLER_CHANNEL);
      PIDController pid = new PIDController(Ports.Kp, Ports.Ki, Ports.Kd);
      Encoder flyWheelEncoder = new Encoder(Ports.FLYWHEEL_ENCODER_CHANNEL_A, Ports.FLYWHEEL_ENCODER_CHANNEL_B);
      
      double velocity;

      @Override
      public void teleopInit() {
        motorOne.setSmartCurrentLimit(4);
        motorOne.setSecondaryCurrentLimit(5);
        pid.setTolerance(10);

        flyWheelEncoder.setDistancePerPulse(Math.PI / 8192);
        flyWheelEncoder.setMinRate(10);
      
      }

      @Override
      public void teleopPeriodic() {
        
        if (controller.getAButton()) {
          motorOne.set(0.3);
        } else {
          motorOne.set(0.00);
        }

        if (controller.getBButton()) {

          motorTwo.set(-1.00);
          motorThree.setVoltage(-1 * MathUtil.clamp(pid.calculate(flyWheelEncoder.getDistance(), Ports.setPoint), -12, 12));
          motorFour.setVoltage( MathUtil.clamp(pid.calculate(flyWheelEncoder.getDistance(), Ports.setPoint), -12, 12));

        } else {
          motorTwo.set(0);
          motorThree.setVoltage(0);
          motorFour.setVoltage(0);
        }     
        
      }

}



