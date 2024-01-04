package frc.robot;

import java.util.EnumMap;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.ShuffleBoardExample;

public final class ShuffleBoard {
		// public static class ShuffleBoardSubsystems{
		public String UserTab = "Main"; //anything the drivers need to see should be on this tab
		public String DebugTab = "Debug"; //anything that will need to be referenced for debugging should be on this tab
		
		/**
		 * this is a class that represents the shuffleboard interactions with the shuffleboard
		 * each subsystem that intends to interface with the shuffleboard will interact with their corresponding TabManager
		 * they consist of a tabname field, a enum of entries, and a method that returns a map of the entries
		 */
		public static class ShuffleBoardSubsysEx{
			public static String tabName = "ExSubsysA";

			/**
			 * this is an enum of the entries in the shuffleBoard tab and its name
			 * this is so that it is simple to rename entries and also so you don't have to lookup the entry name each time you want to set/get the value
			 */
			public static enum EntriesShuffleBoardSubsysEx{
				ExampleEntryA ("nameOfEntry");

				private String entryName;
				private EntriesShuffleBoardSubsysEx(String entryName) {this.entryName = entryName;}
			}


			/**
			 * this is the main method of the class
			 * @param subsystem this is the subsystem that the tab is associated with, allowing for commands to be called from the shuffleboard (feel free to change the type to a command)
			 * @return enumMap returns an enumMap that matches the values in the enum to a entry, this is what is used in the subsystem 
			 */
			public static EnumMap<EntriesShuffleBoardSubsysEx, GenericEntry> getEnumMap(ShuffleBoardExample subsystem) { //the parameter passed is for the commands
				EnumMap<EntriesShuffleBoardSubsysEx, GenericEntry> out = new EnumMap<>(EntriesShuffleBoardSubsysEx.class);
				ShuffleboardTab tab = Shuffleboard.getTab(tabName);
				
				// this is where you will create the actual subsystem tabs
				out.put(EntriesShuffleBoardSubsysEx.ExampleEntryA, 
					tab.add(EntriesShuffleBoardSubsysEx.ExampleEntryA.entryName, 0.0).getEntry()
				);
				//this is a command being added to the tab
				tab.add("reset", subsystem.resetToZero());

				return out;
			}
		}
}
