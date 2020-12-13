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
      CANSparkMax spindexerMotor = new CANSparkMax(Constants.SPINDEXER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax acceleratorMotor = new CANSparkMax(Constants.ACCELERATOR_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor1 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor2 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Constants.CONTROLLER_CHANNEL);
      Encoder flyWheelEncoder = new Encoder(Constants.FLYWHEEL_ENCODER_CHANNEL_A, Constants.FLYWHEEL_ENCODER_CHANNEL_B);

      
      @Override
      public void teleopInit() {
        // Set setters such as current limits and distances
        spindexerMotor.setSmartCurrentLimit(14);
        spindexerMotor.setSecondaryCurrentLimit(16);

      }

      @Override
      public void teleopPeriodic() {  
      
        // If the A button is held turn on the intake
        spindexerMotor.set(controller.getAButton() ? 0.6 : 0);

        // If the left trigger is held turn on the flywheel and sucker
        double leftTriggerVal = controller.getTriggerAxis(Hand.kLeft);

        flywheelMotor1.set(leftTriggerVal > 0.1 ? -1 * leftTriggerVal : 0);
        flywheelMotor2.set(leftTriggerVal > 0.1 ? leftTriggerVal : 0);
        acceleratorMotor.set(leftTriggerVal > 0.1 ? -1 : 0);

        // Add the flywheels speed to the smart dashboard
        SmartDashboard.putNumber("Flywheel Distance", flyWheelEncoder.getRate() * 60);
   }

}



