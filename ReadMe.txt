For Assignment 7:

Thank You!!! & Hope you are enjoyed!!

\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
\\1. Changes: (We have rewritten everything!!!!!!!!!!!!!!everything!!!!!!!!!!!!!!!!!!!!!!!)       \\
\\													\\
\\1>. We changed our original editor: List<List<Note>> to TreeMap					\\
\\    It should be so much cleaner, powerful, and effective right now!!!				\\
\\2>. We add a new method: moveANote(int x1, int y1, int x2, int y2), which allow a user to move a\\
\\    note from its original position to a new position                                           \\
\\3>. A new field Note storedNote, which works with the method moveANote. It is for restore the   \\
\\    information of the original nodes. In other words, it allow a user to move a note, without  \\
\\    change the information of the note.         						       \\
\\4>. Some subtle changes in Console, just for working with the new TreeMap.                      \\
\\5>. A new method moveOnTime(int time) in view interface to allow the image to move              \\
\\    as the time moves forward.                                                                  \\
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
\\2. Update:												\\
\\1>. New function moveANote will allow the user to move a note.					\\
\\2>. It should now let users to add multiple notes at the some position!				\\
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
\\3. Debugs:												\\
\\1>. One major bug we left last time is the note channel does’t work, but we have fixed it! Enjoy!!
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
\\4. Operations:											\\
\\1>. addNote: A user can simply press and hold the key “c”,                                      \\
\\              meanwhile, type the information of a note.                                        \\
\\             remember to separate each field by a “,”.                                          \\
\\              A new note will be created when you release “c”.                                  \\
\\	      eg: if I wanna create a Note with 							\\
\\		   instrument: 10               							\\
\\		   volume    : 100 								        \\
\\		   duration  : 3 								        \\
\\		   startTime : 5 								        \\
\\		   pitch     : E2 								        \\
\\	Using Keyboard: I will hold “c”,                                                           \\
\\                  and sequentially type “10” “,” “100” “,” “3” “,” “5” “,” “40” “,”              \\
\\			 then release “c” to finish.                                                \\
\\	Using Mouse   : I will hold “c”, and sequentially type “10” “,” “100” “,” “3” “,”          \\
\\			 then click the position(5 E2), finally release “c”.				\\
\\                                                                                                \\
\\2>. removeNotes: A user can remove a note or multiple notes at one time!!                       \\
\\		  eg: if I wanna remove two notes at (5, E2) and (4, C3)                           \\
\\		  Using Keyboard: I will hold “r”, then click “5” “,” “28” “,” “4” “,” “43” “,”    \\
\\		  Using Mouse   : I will hold “r:, then just using mouse to click all the notes    \\
\\				   I want to remove. Finally release “r”                            \\
\\                                                                                                \\
\\3>. moveANote: A user can move a note by simply holding the right bottom on the mouse,          \\
\\	       and drag a note to the new position.                                                \\
\\                                                                                                \\
\\4>. changeTime: A user can change the current playing time by holding “t”, then type the new time.
\\		 eg: The red line indicator is at time: 10. I can hold “t”, then type any number.  \\
\\		     The red line indicator will be moved to the new position,                     \\
\\		     and play it’s current first note.                                             \\
\\                                                                                                \\
\\                                                                                                \\
\\5>. UP   : to scroll up                                                                         \\
\\6>. DOWN : to scroll down                                                                       \\
\\7>. LEFT : to scroll left                                                                       \\
\\8>. RIGHT: to scroll right                                                                      \\
\\                                                                                                \\
\\9 >.W    : jump to the top                                                                      \\
\\10>.S    : jump to the bottom                                                                  \\
\\11>.A    : jump to the left most                                                                \\
\\12>.D    : jump to the right most                                                               \\
\\                                                                                                \\
\\13>. SPACE: PAUSE / PLAY                                                                        \\
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

03/27/2016 Updates:

1. Repeat Signs:
   When the indicator hits the second repeat sign, it will jump back to the first repeat sign,
   then repeat the notes in between.

   By adding repeat signs, the user can hold “L”, and meanwhile, press time.
   Ex: Repeat Signs at 16s and 20s:
   Holding “L”, press “16” “,” “20” “,”

2. Multiple Endings:
   This feature allows user to add multiple endings. When the first ending has been played, the second
   time will automatically skip the first ending, and goes to the next ending.









