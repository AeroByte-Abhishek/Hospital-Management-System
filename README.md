# Hospital Management System (HMS) 🏥

A console-based Java application for managing patient and doctor information, and scheduling appointments. This system is built using Java, JDBC, and MySQL, with robust exception handling to ensure a smooth user experience.

## Features

- **Patient Management:** Add, view, and check patient details.
- **Doctor Management:** View and check doctor information.
- **Appointment Scheduling:** Manage appointments between patients and doctors.
- **Database Integration:** Utilizes MySQL for data persistence.
- **Robust Exception Handling:** Gracefully handles common errors and user input issues.
- **Console-Based Interface:** A user-friendly, text-based interface.

## Technologies Used

- **Language:** Java ☕
- **Database:** MySQL
- **Database Connectivity:** JDBC (Java Database Connectivity)

## Database Schema

The database consists of three tables:

- **Patient:** Stores patient information. The `Id` field is the Primary Key.
- **Doctors:** Stores doctor information. The `Id` field is the Primary Key.
- **Appointments:** Manages appointments. It links patients and doctors using `Patient Id` and `Doctor Id` as Foreign Keys referencing respective tables. The `Id` field is its Primary Key.

## System Architecture

The application follows a modular design:

- **Driver():** Entry point of the application.
- **Main Menu():** Guides the user with options to manage patients and doctors.
- **Patient and Doctor Modules:** Contains functions for adding, viewing, and checking information.
- **Database:** Interacts with MySQL database to store and retrieve data.

  ![image alt](https://github.com/AeroByte-Abhishek/Hospital-Management-System/blob/main/HMS%20schema.png?raw=true)

## How to Contribute

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Abhishek - [abhishekmsira7225@gmail.com](mailto:abhishekmsira7225@gmail.com)  
Project Link: [https://github.com/AeroByte-Abhishek/Hospital-Management-System](https://github.com/AeroByte-Abhishek/Hospital-Management-System)
