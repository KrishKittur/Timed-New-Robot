package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {

      // Instantiating the hardware
      CANSparkMax spindexerMotor = new CANSparkMax(Constants.SPINDEXER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Constants.CONTROLLER_CHANNEL);
      DutyCycleEncoder spindexerEncoder = new DutyCycleEncoder(Constants.SPINDEXER_ENCODER_CHANNEL);

      @Override
      public void teleopInit() {
        // Set setters
        spindexerMotor.setSmartCurrentLimit(14);
        spindexerMotor.setSecondaryCurrentLimit(16);
        spindexerEncoder.setDistancePerRotation(Constants.SPINDEXER_ENCODER_DPR);
      }

      @Override
      public void teleopPeriodic() {
        // If the A button is pressed spin the spindexer
        spindexerMotor.set(controller.getAButton() ? 0.6 : 0);

        // Print out the spindexers encoders readings
        spindexerEncoder.getDistance();
      }

}



