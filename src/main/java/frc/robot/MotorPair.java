// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class MotorPair {
  private WPI_TalonSRX motor;
  private CANCoder coder;
  private int number;
  public MotorPair(int motorID, int coderID, boolean motorReversed, int num){
    motor = new WPI_TalonSRX(motorID);
    this.coder = new CANCoder(coderID);
    initTalons(motor, coder);
    
    this.number = num;
  }
  public void initTalons(WPI_TalonSRX motor, CANCoder coder) {
    // motor.setSafetyEnabled(true);
    motor.setInverted(false);
    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    motor.setSensorPhase(false);
    motor.config_kP(0, 0.5);
    motor.config_kI(0, 0);
    motor.config_kD(0, 0);
    motor.config_kF(0, 0.9);
    motor.configMotionCruiseVelocity(3000);
    motor.configMotionAcceleration(2250);
    motor.configNominalOutputForward(0);
    motor.configNominalOutputReverse(0);
    motor.configPeakOutputForward(1);
    motor.configPeakOutputReverse(-1);
    motor.configAllowableClosedloopError(0, 0, 30);
    motor.setSelectedSensorPosition(0);
  }
  
  public void turnTo(double degree, boolean isMotionMagic) {
    if (isMotionMagic) {
      SmartDashboard.putNumber("deg", degree);
      SmartDashboard.putNumber("Encoder Units", degree * 2048 / 360);
      motor.set(ControlMode.MotionMagic, degree * 2048 / 360);
    }
    else{
      // double currentDeg = coder.getPosition();
      // double acceptable = 2.5;
      // if (Math.abs(currentDeg - degree) <= acceptable){
      //   motor.set(ControlMode.PercentOutput, 0);
      // }
      // else{
      //   if (currentDeg > degree){
      //     motor.set(ControlMode.PercentOutput, 0.12);
      //   }
      //   else{
      //     motor.set(ControlMode.PercentOutput, -0.12);
      //   }
      // }
    }
  }
  
  public double getDeg(){
    // return coder.getPosition() % 360;
    // return 0;
    return ((motor.getSelectedSensorPosition() % 2048) / 2048 * 360);
  }
  
  public void debugToDashboard(){
    // SmartDashboard.putNumber("Module " + number + " angle", getDeg());
    SmartDashboard.putNumber("Module " + number + " motorpos", -motor.getSelectedSensorPosition() % 2048 / 2048 * 360);
  }
  
  public void set(ControlMode mode, double value){
    motor.set(mode, value);
  }
  
  public void setPos(double pos){
    // coder.setPosition(pos);
    motor.setSelectedSensorPosition(pos);
  }
}
