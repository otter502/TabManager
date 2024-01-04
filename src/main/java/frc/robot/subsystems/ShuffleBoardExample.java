// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.EnumMap;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ShuffleBoard.ShuffleBoardSubsysEx;
import frc.robot.ShuffleBoard.ShuffleBoardSubsysEx.EntriesShuffleBoardSubsysEx;;


public class ShuffleBoardExample extends SubsystemBase {

  public final GenericEntry exampleEntry;
  
  /** Creates a new ExampleSubsystem. */
  public ShuffleBoardExample() {
    EnumMap<EntriesShuffleBoardSubsysEx, GenericEntry> entries = ShuffleBoardSubsysEx.getEnumMap(this);
    exampleEntry = entries.get(EntriesShuffleBoardSubsysEx.ExampleEntryA); //you can also have the enumMap itself be a global entry which I would recommend for larger subsystems
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase resetToZero() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          exampleEntry.setDouble(0.0);
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() { 
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    exampleEntry.setDouble(exampleEntry.getDouble(0.0)+1);
    System.out.println(exampleEntry.getDouble(-1));
    // This method will be called once per scheduler run during simulation
  }
}
