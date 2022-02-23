// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class modules extends SubsystemBase {
  /** Creates a new modules. */
  MotorPair FL_pair = new MotorPair(1, 11, false, 0);
  MotorPair FR_pair = new MotorPair(3, 10, false, 1);
  MotorPair BL_pair = new MotorPair(7, 8, false, 2);
  MotorPair BR_pair = new MotorPair(12, 9, false, 3);
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
    FL_pair.turnTo(degree);
    FR_pair.turnTo(degree);
    BL_pair.turnTo(degree);
    BR_pair.turnTo(degree);
    // SmartDashboard.putNumber("deg", degree);
    // double currentDeg = FL_pair.getPosition();
    // double acceptable = 2.5;
    // if (Math.abs(currentDeg - degree) <= acceptable){
    //   FL_pair.set(ControlMode.PercentOutput, 0);
    //   FR_pair.set(ControlMode.PercentOutput, 0);
    //   BL_pair.set(ControlMode.PercentOutput, 0);
    //   BR_pair.set(ControlMode.PercentOutput, 0);
    // }
    // else{
    //   if (currentDeg > degree){
    //     FL_pair.set(ControlMode.PercentOutput, 0.1);
    //     FR_pair.set(ControlMode.PercentOutput, 0.1);
    //     BL_pair.set(ControlMode.PercentOutput, 0.1);
    //     BR_pair.set(ControlMode.PercentOutput, 0.1);
    //   }
    //   else{
    //     FL_pair.set(ControlMode.PercentOutput, -0.1);
    //     FR_pair.set(ControlMode.PercentOutput, -0.1);
    //     BL_pair.set(ControlMode.PercentOutput, -0.1);
    //     BR_pair.set(ControlMode.PercentOutput, -0.1);
    //   }
    // }
  }
  
  public void stop(){
    FL_pair.set(ControlMode.PercentOutput, 0);
    FR_pair.set(ControlMode.PercentOutput, 0);
    BL_pair.set(ControlMode.PercentOutput, 0);
    BR_pair.set(ControlMode.PercentOutput, 0);
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
