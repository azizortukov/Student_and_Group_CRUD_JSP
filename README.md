# Student and Group CRUD JSP Project

This project is a Java web application for managing students and groups using JSP (JavaServer Pages) technology. It provides CRUD (Create, Read, Update, Delete) functionality for both students and groups, with features including secure admin pages, login functionality with remember me option, and cascading deletion of students when a group is deleted.

## Technologies Used

- **Java Backend**: Utilizes Java for server-side logic and business operations.
- **Hibernate JPA**: Object-relational mapping framework for working with PostgreSQL database.
- **PostgreSQL**: Open-source relational database management system.
- **JSP (JavaServer Pages)**: Dynamic web pages technology for displaying HTML content with Java code.
- **Bootstrap**: Front-end framework for creating responsive and visually appealing web pages.
- **Servlets**: Handles requests and responses between the client and server.
- **Tomcat**: Servlet container that hosts the Java web application.
- **Cookies**: Utilized for implementing the remember me functionality.

## Features

- **CRUD Operations**: Allows creating, reading, updating, and deleting both students and groups.
- **Secure Admin Pages**: Access to admin pages is restricted to authenticated users only.
- **Login Page**: Provides a login page for user authentication.
- **Remember Me Checkbox**: Users can choose to stay logged in even after the session is disconnected by checking the "Remember Me" checkbox.
- **Cookie-Based Session Management**: Saves a cookie on the user's device to maintain their login status across sessions if the "Remember Me" option is selected.
- **Cascading Deletion**: When a group is deleted, all associated students are automatically removed from the PostgreSQL database to maintain data integrity.

## Installation and Setup

1. Clone the repository to your local machine:

git clone https://github.com/yourusername/student-group-crud-jsp.git

2. Import the project into your preferred IDE (Eclipse, IntelliJ, etc.).
3. Set up the PostgreSQL database and configure the connection details in the project's configuration files.
4. Deploy the application to a servlet container such as Apache Tomcat.
5. Access the application through your web browser.

## Usage

1. Navigate to the login page and enter your credentials.
2. Optionally, check the "Remember Me" checkbox to stay logged in across sessions.
3. Access the admin pages to perform CRUD operations on students and groups.
4. Log out when finished using the application.

## Contributing

Contributions are welcome! Please feel free to submit issues, feature requests, or pull requests to help improve this project.
