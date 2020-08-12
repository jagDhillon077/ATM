# My Personal Project

## Simple atm

Description:
- What does this application do? This application runs a **simple atm** with basic user interface and graphics.
- Anyone that would like to withdraw and deposit money into their own personal .
- This project interests me because I like to understand what happens behind the scenes of everything. This project is
going to be a much more simplified version of general atm's, but I will be able to build my knowledge
n how day-to-day machines like this work. The next time I would be able to walk up to an atm or a cash register,
I would be able to visualize what is actually going on under the hood.


##User Stories:

- As a user, I want to be able make an account.
- As a user, I want to be greeted by the atm and for the atm to give details about my account once created.
- As a user, I want to be able to deposit into my checking or savings account.
- As a user, I want to be able to withdraw into my checking or savings account.
- As a user, I want to be able to make transfers in between accounts of an atm.
- As a user, I want to be able to logout
- As a user, I want to be able to be able to save my accounts balances to a file.
- As a user, I want to be able to optionally load my saved accounts from a file when the program starts.


##Instruction For Grader

- You can generate the first required event by first running the GUI in the UI package
  - Typing in "1" as the username and "1" as the password
  - This opens the options screen, and you are able to choose any of the options from deposit, withdraw, and transfer. 
All these options relate to my "Multiple X's to Y" user story, where the multiple X's
are the transactions that are created in the transaction class where (deposits/withdraws/transfers ==x) are generated. 
After creation of the financial action, the transactions apply to Accounts (Accounts == Y) of the user logged into the 
atm.
- You can locate the audio component by going into the sound package for the method used, and the Music folder for the
sound effects used for the ATM, you can alternatively just run the GUI and make a deposit, withdraw, and transfer to
hear the different effects for each.
- You can locate the visual component by running the GUI and a login interface will appear on 
screen (follow first bullet point)
- You can save the state of the application by clicking the save accounts button before exiting.
- You can reload the state of the application by simply logging back into the GUI and the information will be
- automatically retrieved.


## Phase 4 Tasks Below:
- Phase 4 task 2: I chose option 1 *Test and design a class that is robust.  
You must have at least one method that throws a checked exception.  You must have one test 
for the case where the exception is expected and another where the exception is not expected.*

The main class involved in this task is Account and UserInfo, and the main method involved is located in the class 
UserInfo addAcctTransaction().
This method adds a transaction to the account that must be strictly greater than 0. Otherwise an 
OnlyPositiveException will be thrown asking for only positive numbers in the input. The exclusions of using 
negative numbers was used to avoid confusion, so if a user decided to deposit, the positive amount
will be applied to the transaction however if the user tries to withdraw, amount*(-1) will be applied
to the withdraw function.
To locate the tests please open the test folder > atmTest > AccountTest > and it will be located in between the "Phase 
4 Task 2" block located at the top.

- Phase 4 Task 3: In the GUI class, there were multiple occurrences of copied and pasted code so I decided to refactor 
it all into 1 method in order to lower cohesion, increase coupling, and increases the level of SPOC 
(singular point of control). 
- 4 Different Methods were refactored:
- getjPanel() (Line 613) was refactored in 8 different occurrences:
   - List of occurrences  for getjPanel 630, 643, 658, 679, 693, 708, 728, 742
- bottomHalfTextFieldUpdate() was refactored in 16 different occurrences:
   - List of occurrences for bottomHalfTextFieldUpdate() 251, 257, 276, 282, 298, 303, 308, 324, 329, 334, 345, 375, 
   386, 391, 406, 411
- numberFormatExceptionGui() was refactored in 6 different occurrences:
   - List of occurrences for numberFormatExceptionGui() 261, 286, 312, 339, 395, 416
- playSound(String soundName) was refactored in into a separate class with 17 different occurrences.
   - List of occurrences for playSound(String soundName) 232, 301, 327, 348, 353, 359, 389, 409, 423, 430, 437, 444, 
   451, 458, 466, 477, 488
   
