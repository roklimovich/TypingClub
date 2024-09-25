## Project Title
Monkeytype-style Typing Efficiency Application


## Description
This project is a single-window JavaFX application designed to evaluate fast and accurate typing efficiency,
similar to "Monkeytype." It tracks typing speed and accuracy while providing various statistics and visual
feedback on typing performance. The application supports multiple languages and various test durations,
with a dynamically generated word list based on the user's language selection.

The project adheres to the **Model-View-Controller (MVC)** architecture and includes no **FXML** or **WYSIWYG** tools.
Additionally, it ensures the application scales smoothly across different window sizes and handles errors gracefully.


## Features
* **Multiple Language Support:** The application dynamically loads a list of languages based on text files provided in the 
``dictionary`` folder. Each text file represents a different language.

* **Typing Test:** Users can take a typing test with a configurable duration of 15, 20, 45, 60, 90, 120, or 300 seconds.

* **Dynamic Word List:** Each test generates a list of 30 random words from the selected language's dictionary.

* **Character Highlighting:**
  * **Gray:** Unentered text
  * **Green:** Correctly entered characters
  * **Red:** Incorrect characters
  * **Orange:** Redundant characters
  * **Black:** Omitted characters

* **Keyboard Shortcuts:**
  * ``tab + enter``: Restart the test
  * ``ctrl + shift + p``: Pause the test
  * ``esc``: End the test
* **Statistics:** After each test, the app provides:
  * Current Words Per Minute (WPM)
  * Average WPM over the test duration
  * Detailed character statistics: Correct, Incorrect, Extra, Missed
  * Percentage of correctly entered characters
* **Test Report:** After each test, a file is generated in the project directory with a breakdown of each word and its 
corresponding WPM.


## Installation
1. Clone or download the repository.
2. Ensure the ``dictionary`` directory is placed in the project structure and contains text files for each supported 
language. Each file should be named based on the language it represents.
3. Compile the project using a Java Development Kit (JDK) that supports JavaFX.
4. Launch the application via your preferred method (command line or IDE).


## Requirements
* **JavaFX** framework
* **Java Development Kit (JDK) 11** or higher
* **No FXML or Scene Builder** 


## How to Run
1. Open the project in an IDE like IntelliJ IDEA or Eclipse.
2. Ensure JavaFX is properly configured.
3. Run the main class to start the application.


## Usage
1. Select a language from the top menu (based on available language files in the ``dictionary`` folder).
2. Choose the test duration.
3. Start typing the words displayed.
4. After the test, view your WPM and detailed statistics.
5. To restart or pause the test, use the keyboard shortcuts mentioned above.


## License
This project is for educational purposes and is subject to academic integrity rules. 
Plagiarism will result in disciplinary action.



## Author
Roman Klimovich

## Lecturer 
Sławomir Aleksander Dańczak, M.Sc. PJAIT