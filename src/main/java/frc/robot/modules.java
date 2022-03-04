// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class modules extends SubsystemBase {
  /** Creates a new modules. */
  MotorPair FL_pair = new MotorPair(1, 1, false, 0);
  MotorPair FR_pair = new MotorPair(3, 9, false, 1);
  MotorPair BL_pair = new MotorPair(7, 10, false, 2);
  MotorPair BR_pair = new MotorPair(12, 8, false, 3);
  public modules() {}
  
  public void _reset(){
    FL_pair.setPos(0);
    FR_pair.setPos(0);
    BL_pair.setPos(0);
    BR_pair.setPos(0);
  }
  
  public void spin(double speed){
    FL_pair.set(ControlMode.PercentOutput, speed);
    FR_pair.set(ControlMode.PercentOutput, speed);
    BL_pair.set(ControlMode.PercentOutput, speed);
    BR_pair.set(ControlMode.PercentOutput, speed);
  }
  
  public void turnTo(double degree){
    FL_pair.turnTo(degree, true);
    FR_pair.turnTo(degree, true);
    BL_pair.turnTo(degree, true);
    BR_pair.turnTo(degree, true);
  }
  
  public void stop(){
    FL_pair.set(ControlMode.PercentOutput, 0);
    FR_pair.set(ControlMode.PercentOutput, 0);
    BL_pair.set(ControlMode.PercentOutput, 0);
    BR_pair.set(ControlMode.PercentOutput, 0);
  }

  public double angle(){
    return FL_pair.getDeg();
  }

  public boolean isFLModuleAt(double degree){
    return (Math.abs(degree - angle()) < 1);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    FL_pair.debugToDashboard();
    FR_pair.debugToDashboard();
    BL_pair.debugToDashboard();
    BR_pair.debugToDashboard();
  }
}
