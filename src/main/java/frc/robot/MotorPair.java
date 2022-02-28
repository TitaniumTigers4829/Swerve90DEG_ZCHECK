// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class MotorPair {
  private WPI_TalonFX motor;
  private CANCoder coder;
  private int number;
  public MotorPair(int motorID, int coderID, boolean motorReversed, int num){
    motor = new WPI_TalonFX(motorID);
    motor.setInverted(motorReversed);
    // motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // motor.config_kP(0, 0.2);
    motor.setNeutralMode(NeutralMode.Coast);
    
    this.coder = new CANCoder(coderID);
    this.number = num;
  }
  
  public void turnTo(double degree, boolean isMotionMagic){
    if (isMotionMagic){
      motor.set(ControlMode.MotionMagic, degree * 2048 / 360);
    }
    else{
      double currentDeg = coder.getPosition();
      double acceptable = 2.5;
      if (Math.abs(currentDeg - degree) <= acceptable){
        motor.set(ControlMode.PercentOutput, 0);
      }
      else{
        if (currentDeg > degree){
          motor.set(ControlMode.PercentOutput, 0.12);
        }
        else{
          motor.set(ControlMode.PercentOutput, -0.12);
        }
      }
    }
  }
  
  public double getDeg(){
    return coder.getPosition() % 360;
  }
  
  public void debugToDashboard(){
    SmartDashboard.putNumber("Module " + number + " angle", getDeg());
    SmartDashboard.putNumber("Module " + number + " motorpos", -motor.getSelectedSensorPosition() % 2048 / 2048 * 360);
  }
  
  public void set(ControlMode mode, double value){
    motor.set(mode, value);
  }
  
  public void setPos(double pos){
    coder.setPosition(pos);
    motor.setSelectedSensorPosition(pos);
  }
}
