# StudentRoster_GUI
A simple GUI software that maintains a student roster for tuition procedures.\
The system uses an array to perform the roster.\
Student is an abstract classes, and students with different statuses, including Resident, NonResident, TriState, and International, are extended from Student.

The software uses a single GUI to perform all the tasks.
The GUI contains 4 top tabs that are Student Profile, Payment/Financial Aid, Print Roster, and About.

# Student Profile
Add new students to the roster with his/her name, major, status, and credit hours.
Remove a student by searching his/her name and major and removing him/her from the roster.
Calculate a student's tuition depending on his/her status and credit hours.

# Payment/Financial Aid
Pay a certain amount of tuition on a selected date.
Set the amount of financial aid to a student.
Update the tuition after a financial aid is set or a payment is made.
Calculate the tuition for all student on one click.

# Print Roster
Print the roster by calling their overrided toString method.
Print the raw roster.
Print and sort the roster by student's name.
Print and sort the roster by students' last payment date.

# About
Show the author's and software's information. (Currently empty)
