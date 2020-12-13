package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpiutil.math.MathUtil;

public class Robot extends TimedRobot {

      // Instantiating the hardware
      CANSparkMax spindexerMotor = new CANSparkMax(Constants.SPINDEXER_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax acceleratorMotor = new CANSparkMax(Constants.ACCELERATOR_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor1 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      CANSparkMax flywheelMotor2 = new CANSparkMax(Constants.FLYWHEEL_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
      XboxController controller = new XboxController(Constants.CONTROLLER_CHANNEL);
      PIDController pid = new PIDController(Constants.KP, Constants.KI, Constants.KD);
      SimpleMotorFeedforward ff = new SimpleMotorFeedforward(Constants.STATIC_BREAK_VOLTAGE, Constants.VOLTAGE_PER_RPM);
      Encoder flywheelEncoder = new Encoder(Constants.FLYWHEEL_ENCODER_CHANNEL_A, Constants.FLYWHEEL_ENCODER_CHANNEL_B);
      
      double velocity;

      @Override
      public void teleopInit() {
        // set Setters
        spindexerMotor.setSmartCurrentLimit(4);
        spindexerMotor.setSecondaryCurrentLimit(5);
        pid.setTolerance(10);

        flywheelEncoder.setDistancePerPulse(Math.PI / 8192);
        flywheelEncoder.setMinRate(10);
      
      }

      @Override
      public void teleopPeriodic() {
        
        // When the A button is pressed turn the spindexer on
        spindexerMotor.set(controller.getAButton() ? 0.3 : 0);

        // When the B button is pressed turn the shooter on
        if (controller.getBButton()) {
          acceleratorMotor.set(-1.00);
          flywheelMotor1.setVoltage(-1 * MathUtil.clamp(pid.calculate(flywheelEncoder.getDistance(), Constants.SETPOINT), -12, 12));
          flywheelMotor2.setVoltage(MathUtil.clamp((pid.calculate(flywheelEncoder.getDistance(), Constants.SETPOINT) + ff.calculate(Constants.SETPOINT)), -12, 12));
        } else {
          acceleratorMotor.set(0);
          flywheelMotor1.setVoltage(0);
          flywheelMotor2.setVoltage(0);
        }     
        
      }

}



