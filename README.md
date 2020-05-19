Coverage: SonarQube-97.2%, JUnit-98%
# Hobby Project (Playbook Pro)

This project took advantage of various technologies such as Google Cloud Platform (GCP), MySQL database, SonarQube, Nexus, Java, Spring, Javascript, CSS, HTML, JUnit, Selenium, TestNG and Mockito to create a full-stack application. 

The project used Java as its code base using the Spring framework. It also consisted of a front-end website which controlled and interacted with the back-end via API calls. The application was packaged using a Continuous Integration (CI) pipeline tool, Jenkins.

The Project Documentation folder contains the surefire report, extent report, risk assessment, the ERD and the presentation.

The Kanban Board used for this project was the GitHib Project board that is connected to this project.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

1. Clone/fork the repo unto your computer, alternatively you could just download the project and open it in your IDE.
2. Open the project as a Maven project in your IDE.
3. Change the hardcoded MySQL link `jdbc:mysql://35.246.5.68:3306/hobby2` in the `application.properties` file to an instance of your own MySQL database or whichever database of your choice.
4. You will also need to update the username and password in the file above.
5. The `data.sql` connection in the `application.properties` file makes sure that your database is populated with the correct data for the front-end. 

### Prerequisites

In order for this project to run, the computer needs to have java installed and the project needs to be built using maven. 

A MySQL instance has to be set up and then linked as stated in number 3 above.

Git has to also be installed unto the computer so that the project can be cloned or forked.

## Testing

The tests were written using Mockito, TestNG, Selenium and Junit. These test covered a good majority of the program that could be tested this inclused the controllers, the services and the domain classes. 

## Deployment

After the project has been forked and opened in the IDE. Go to the project folder which the project is located and open the command window in the folder. You should then be pointing to the location where the project is in your command window. Preform the following command

`mvn clean package`

The output should be as follows:

![](Documentation/build%20success.JPG)

After this is done, the jar file can now be run using the following command in the command line:

`java -jar target/DavidWilliams-SoftwareMarch16-HobbyProject-jar-with-dependencies.jar`

The program will then be up and running.
The front-end for the application can then be accessed by `http://localhost:8181/index.html`
 
![](Documentation/playbookHomePage.jpg) 

The picture above will appear if you run the application and go to the correct webpage.

As you can see from the nav bar, there are three options. The home page, the playbooks page and the Teams page.

When you navigate to each page there are different options. On the playbooks page you can either create a new playbook, delete an existing playbook as shown below.
 
 ![](Documentation/playbookCRUD.jpg)
 
 You can view the playbooks that are available with the plays that are currently in them. You can choose whether to delete a play from a playbook here as well.
 
 ![](Documentation/playbookDelete.jpg)
 
 In the teams section, you can see the available teams and select which team you would like to pick.
 
 ![](Documentation/teams.jpg)
 
 When each team is selected you can select between the offence and defence to view their respective playbooks. 
 
 ![](Documentation/plays.jpg)
 
 After either the offensive or defensive playbook is selected, you can then view the plays in the respective team playbooks as seen below.
 
 ![](Documentation/playList.jpg)
 
 A play can now be selected and added to any playbook via the dropdown as shown above
 
 ![](Documentation/playSelected.jpg)
 
  

 
 


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jenkins](https://www.jenkins.io/) - CI Pipeline Tool
* [Eclipse](https://www.eclipse.org/) - IDE
* [Java](https://www.java.com/en/download/) - Code Base
* [Trello](https://trello.com/) - Project Planning Kanban Board
* [JUnit](https://junit.org/junit5/) - Testing Tool
* [Mockito](https://site.mockito.org/) - Testing Tool
* [GCP](https://cloud.google.com/) - Cloud Host
* [SonarQube](https://www.sonarqube.org/) - Static Analysis Tool
* [Nexus](https://www.sonatype.com/product-nexus-repository) - Artefact Repository
* [Git](https://git-scm.com/) - Version Control System
* [Selenium](https://www.selenium.dev/projects/) - Automation Testing tool

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **David Williams** -  [DavidWilliamsQA](https://github.com/DavidWilliamsQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*
