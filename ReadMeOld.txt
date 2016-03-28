Music Editor
I created a Model interface for music editor that provides methods for 
editing music, tracking the whole game status, and playing music nodes.
MusicModel is a class that implements the Model, it contains
a enum status and an integer of currPosition to keep track the status of the
music, it also has an integer of measure and 
a ArrayList<LinkedList<NodeBeat>> that includes all the NodeBeat 
of the music.
I used ArrayList<LinkedList<NodeBeat>> to represent the whole music editor,
 it is a list of  LinkedLists of Nodes that has the same pitch(i.e. at
the same height).
The NodeBeat class has fields of Node, beatStatus(HEAD, BODY or NONE) and 
duration. When the music is playing, it will check each vertical line of 
NodeBeats, since it finds the HEAD, it will get the node and the duration
and send the information to Midi
The Node class contains fields of duration, position pitch and instrument.

To make the pitch easier to perform and locate, I used NodePitch class that 
takes a String for the pitch and a integer for the octave, and I created 
methods toInt() & toString() to transfer the pitch from NodePitch to int or
from NodePitch to string. I also implement a static method intToPitch to 
transfer any integer representation of pitch to NodePitch.

ConsoleView and main class is for rendering the music editor. 

Update:
This week, we added a builder class for building a cs3500.music.model
Some small changes on the methods. We changed addNote method to take a particular note.
For ConsoleView:
We used an adapter which takes a cs3500.music.model object, then change it to a ViewModel. We use the cs3500.music.view cs3500.music.model to render the cs3500.music.console cs3500.music.view. The advantage of this is avoiding client to mess up the cs3500.music.model information. The info can only be passed from Model to ViewModel.

For View & MIDI:
We also used the same ViewModel object which is adapted from Model. It is for preventing information leakage as well.

How to play:
An user can change the string in method “BuildView” under cs3500.music.MusicEditor class.
You can choose “visual”, “cs3500.music.console”, and “midi”.

Have Fun :)

