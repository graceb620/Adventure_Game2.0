# Adventure_Game2.0
This is the second version of the adventure game that I created. This was done for my final in my CS67 (object-oriented programming) class with Professor Tim Urness during my Fall 2023 semester. 

## Guide to the files
* You shouldn't have to worry about the .vscode, bin, and lib folders. These should be all good unless you run into a specific issue. The resolution to this specific issue is explained below
* The .jpg and .png files are just images that will appear in the GUI
* **Main.java** This is the file that will run the game. When run, a GUI should pop up. If you run into errors refer to the 'If you have issues' section
* **CharacterInterface.java** This is the file that creates an interface for the Goblin, Boss, and Wizard
* **Character.java** This is the file that holds that Character class. This gives all the characters a name, health, stamina, and damage
* **Spell.java** This is the file that holds the Spell class. This is how spells are created.
* **Wizard.java** This is the file that holds the Wizard class. This inherits traits from the Character class and implements the CharacterInterface. The Wizard is the player
* **Goblin.java** This is the file that holds the Goblin class. This inherits traits from the Character class and implements the CharacterInterface. The Gobin is the first enemy the Wizard faces
* **Boss.java** This is the file that holds the Boss class. This inherits traits from the Character class and implements the CharacterInterface. The Boss is the final enemy the Wizard faces

## If you have issues
One common issue while trying to run this program is the bin folder clearing the needed files. This is the first area I would check if you have issues. 
* If you find that after attempting to run the program, the bin folder doesn't have a long list of files that begin with 'api' follow the steps below.

### Fixing the bin issue
1. First click the link below, or put the link in your search bar. This will download the needed JavaFX files.
   https://download2.gluonhq.com/openjfx/21.0.1/openjfx-21.0.1_windows-x64_bin-sdk.zip
2. Open the downloaded file and find the bin folder. You will need these later, so I would ctrl+a, ctrl+c 
3. Delete everything in the original bin folder and replace them with the files in the newly downloaded one. I use ctrl+a then delete then ctrl+p the copied files from the other folder into the now empty folder
4. After this is done it should be working properly. 
