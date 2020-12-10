package frc.robot;


public class Ports {

    // Ports
    static int FLYWHEEL_ENCODER_CHANNEL_A = 1;
    static int FLYWHEEL_ENCODER_CHANNEL_B = 2;
    static int MOTOR_ONE_ID = 20;
    static int MOTOR_TWO_ID = 21;
    static int MOTOR_THREE_ID = 22;
    static int MOTOR_FOUR_ID = 23;
    static int CONTROLLER_CHANNEL = 0;

    // For tuning
    static double Kp = 0;
    static double Ki = 0;
    static double Kd = 0;
    static double setPoint = 4000;

    static double voltagePerRPM = 0;
    static double staticBreakVoltage = 0;
}