package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class Robot extends TimedRobot {

      // Instantiate all of the hardware
      CANSparkMax intakeMotor = new CANSparkMax(Ports.INTAKE_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax suckerMotor = new CANSparkMax(Ports.SUCKER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor1 = new CANSparkMax(Ports.FLYWHEEL_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor2 = new CANSparkMax(Ports.FLYWHEEL_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Ports.CONTROLLER_CHANNEL);
      Encoder flyWheelEncoder = new Encoder(Ports.FLYWHEEL_ENCODER_CHANNEL_A, Ports.FLYWHEEL_ENCODER_CHANNEL_B);
      
      double velocity;

      @Override
      public void teleopInit() {
        // set setters such as current limits and distances
        intakeMotor.setSmartCurrentLimit(8);
        intakeMotor.setSecondaryCurrentLimit(10);
        flyWheelEncoder.setDistancePerPulse(Math.PI * 2 / 8192);
        flyWheelEncoder.setMinRate(10);
      }

      @Override
      public void teleopPeriodic() {
        
        // If the A button is pressed turn on the intake
        if (controller.getAButton()) {
          intakeMotor.set(0.6);
        } else {
          intakeMotor.set(0);
        }

        // If the left trigger is pressed turn on the flywheel and sucker
        if (controller.getTriggerAxis(Hand.kLeft) > 0.1) {
          suckerMotor.set(-1);
          flywheelMotor1.set(-1 * controller.getTriggerAxis(Hand.kLeft));
          flywheelMotor2.set(controller.getTriggerAxis(Hand.kLeft));      
        } else {
          suckerMotor.set(0);
          flywheelMotor1.set(0);
          flywheelMotor2.set(0);
        }

        System.out.println(flyWheelEncoder.getDistance()); // Print out the flywheel encoders value 

   }

}



