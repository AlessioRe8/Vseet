# V-seet App
V-seet is an application that focuses on creating an interactive environment
between users that want to discover unknown territories.
It comprehends a system that allows users to load Point of Interests in a database (JPA).
These point of interests are classified as verified or not wether the user is assigned
an authorized role. Point of interests may be grouped into Itineraries, and loaded in the
system as "Contents".
## Project
The project was intended to represent a working flow of an organisation between several people,
including the analysis phase, and the defining of project standards and pre-requisites.
Inside the [utils directory](utils) there is the Visual Paradigm project (<code>Vseet-project.vpp</code>), with all the details
about the evolving of the project. The time frame was 1 week per iteration and the project was concluded
in a time span of 4 weeks in total.

## Application
The application was built firstly in Java and then parsed into Springboot for enhanced functionalities.
It's not intended as a final application, and may be extended with many more interactions,
the work was concluded in the way the project specification asked in the University course.

## Run
1. To completely understand the application, download and install Postman 
to execute HTTP requests on the system.
2. Next you will need to run this code as a Springboot application and check the [utils directory](utils).
You will need to import into Postman the <code>Vseet-App-requests.postman_collection</code> file.
3. Once done
you are free to navigate the Postman requests.
The application loads 4 basic users to log in, to try the different
scenarios and permissions the system puts you in.



### License
Copyright (c) 2024 Alessio Re

The license for this repository is MIT License.

Please see the [LICENSE](LICENSE) file for full reference.
