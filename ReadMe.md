# Quick Start

this is a quick guide for how to use the shuffleboard tab manager syntax

the main idea is to move all of the shuffleboard specific code / boiler plate outside of the specific subsystems and in one place

> note: when I reference a `tabManager` class I am talking about the subclasses that correspond to each subsystem, mostly since I needed a name for them

## the format
---
the general structure of a `tabManager` class
```java
public static class ShuffleBoardSubsystem{
	public static String tabName = ...;

    public static enum SubsystemEntries{
       ...
    }

    public static EnumMap<...> getEnumMap(...) { 
        ...
    }
}
```

TabName
---
the tabname is the name for the subsystem's tab. 
> this can be moved into an enum all its own if other subsystems need to reference another subsystem's tab
> 
> this is the reason for having a `user` and `debug` tab name that is accessible from any tab by being a part of the superclass of the `tabmanager` class

SubsystemEntries
---
this is an enum that represents the Entries inside of the tab that will need to be referenced outside of the setup

> in the example code I didn't add the widget which ran the example command to the enum but if needed you can

getEnumMap
---
this function is called from the Subsystem of the tab.

It takes in a reference to the Subsystem and outputs the enum map.

> you can make a subsystem take in multiple inputs (ie subsystems or commandBases) or just the one

this is where the majority of the shuffleboard formatting code lives. it will create the entries and put a reference to them in the enum map so that the subsystem calling the function can easily access and update / receive the entries
> this is more elaborated on in the next section

> small implementation note when declaring a enumMap you need to format it like so:
> ```java 
> ... = new EnumMap<>(SubsystemEntries.class);
> ```
> <br>
## using this system
---

when intializing a subsystem you will call the getEnumMap function of its corresponding `tabManager` class

since the getEnumFunction is static it will look something like this:

```java
public initalizeFunctionName(){
    ... // initialization code 

    EnumMap<...> entries = ShuffleBoardSubsysEx.getEnumMap(this);

    // handling entries

}

```

> I am cutting out some specific stuff like class names, you can see full examples in the [shuffleboard.java](./src/main/java/frc/robot/ShuffleBoard.java) and [ShuffleBoardExample.java](./src/main/java/frc/robot/subsystems/ShuffleBoardExample.java) files

you can either store the entire enumMap or store the individual entries as fields, this is a judgment call and I haven't tested the first option but it *should* work fine.

after that it should be pretty simple to get/set the entries with the methods in the GenericEntry class as needed.

in order to call commands from shuffleboard you add the widgets to the Tab using `tab.add(...)` with the Subsystem's command / commandBase that was passed as a parameter