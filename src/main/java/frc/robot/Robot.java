package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

      // Instantiate all of the hardware
      CANSparkMax intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax suckerMotor = new CANSparkMax(Constants.SUCKER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor1 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor2 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Constants.CONTROLLER_CHANNEL);
      Encoder flyWheelEncoder = new Encoder(Constants.FLYWHEEL_ENCODER_CHANNEL_A, Constants.FLYWHEEL_ENCODER_CHANNEL_B);
      
      double velocity;

      @Override
      public void teleopInit() {
        // Set setters such as current limits and distances
        intakeMotor.setSmartCurrentLimit(14);
        intakeMotor.setSecondaryCurrentLimit(16);

      }

      @Override
      public void teleopPeriodic() {  
      
        // If the A button is held turn on the intake
        if (controller.getAButton()) {
          intakeMotor.set(0.6);
        } else {
          intakeMotor.set(0);
        }

        // If the left trigger is held turn on the flywheel and sucker
        if (controller.getTriggerAxis(Hand.kLeft) > 0.1) {
          suckerMotor.set(-1);
          flywheelMotor1.set(-1 * controller.getTriggerAxis(Hand.kLeft));
          flywheelMotor2.set(controller.getTriggerAxis(Hand.kLeft));      
        } else {
          suckerMotor.set(0);
          flywheelMotor1.set(0);
          flywheelMotor2.set(0);
        }

        SmartDashboard.putNumber("Flywheel Distance", flyWheelEncoder.getRate() * 60); // Print out the flywheel encoders value 
   }

}



