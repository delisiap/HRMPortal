# Group1
Project repo for Group 1

# Group Members
Amit Prajapati (amit.prajapati027@gmail.com/amit.prajapati@dal.ca)
Laxmi Yadav    (laxmiy82@gmail.com / lx405836@dal.ca)
Delisia Philip (p.delisia@gmail.com/ dl280077@dal.ca)

# Project Description 
We are planning to create a Web application for Human Resource management. This application will act as an interface that brings the human resource managers and the employees to a single platform to share, request, retrieve personal HR-related information. There will be two types of users for this portal who are HRs and Employees. All the users have the common functionalities of logging in and editing their profiles. However, the employees can send leave requests and view salary information. The managers, on the other hand, can approve the leaves, enter appraisal information and approve hikes. Finally, the HRs can add users, generate salary slips for the employees to view. We are planning to use HTML, JavaScript for developing front-end and Java for back-end.

# Pre-requisites for running the application on local machine.
This project requires the following pre-requisites to be installed on the system before using the application.
1. Java to be installed in the system.
2. Maven to be installed in the system.

# Heroku Deployment links:
1.Production: https://hrm-prod-release.herokuapp.com/
1.Test: https://hrm-test.herokuapp.com/
2.Dev: https://hrm-devint.herokuapp.com/

# Instructions to build and execute the JAR:
1. Download the project from the GitHub location: https://github.com/DalhousieUniversityCSCI5308/Group1.git
2. Navigate to the location where the files are downloaded using command prompt/git bash
3. Execute `mvn clean package` for building the project and executing testcases. 
4. Navigate to the target folder.
5. Execute `java -jar HRMRest-0.1.jar` from the command prompt.
6. The application will start on the localhost on port 80.

The default user id and password is admin/admin.
