package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

      // Instantiating the hardware
      CANSparkMax spindexerMotor = new CANSparkMax(Constants.SPINDEXER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Constants.CONTROLLER_CHANNEL);
      DutyCycleEncoder spindexerEncoder = new DutyCycleEncoder(Constants.SPINDEXER_ENCODER_CHANNEL);

      @Override
      public void teleopInit() {
        // Set setters
        spindexerMotor.setSmartCurrentLimit(10);
        spindexerMotor.setSecondaryCurrentLimit(12);
        spindexerEncoder.setDistancePerRotation(Constants.SPINDEXER_ENCODER_DPR);

        // Set the spindexer motor to 0
        spindexerMotor.set(0);
      }

      @Override
      public void teleopPeriodic() {
        // Print out the spindexers encoders readings
        SmartDashboard.putNumber("Distance", spindexerEncoder.getDistance());

      }

}



