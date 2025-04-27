# V-seet App
V-seet is an Single Page Application that focuses on creating an interactive environment
between users that want to discover unknown territories.
It comprehends a system that allows users to load Point of Interests in a database (JPA).
These point of interests are classified as verified or not wether an authorized user verify the authenticity. Point of interests may be grouped into Itineraries, and loaded in the system as "Contents".

## Application & frontend
The application was built firstly in Java and then parsed into Springboot for enhanced functionalities.
Frontend was delegated to Angular, with added plugins. Bootstrap (& BS Icons) and ToastrService (ngx-toastr) were added for better graphics.
It's not intended as a final application, and may be extended with many more interactions,
the work was concluded in the way the project specification asked in the University course.

## Security
Security was enhanced from the previous version with backend only. JWT Authentication was added in Springboot, and eventually 
an interceptor was added in the frontend for all calls the made to the backend to include the JWT in these (calls). 
CORS security was added in [SecurityConfig](SecurityConfig) file. Now backend only accepts calls from the frontend, preventing XSS (Cross site scripting).
Code Injection is already prevented in Angular, thanks to sanitization, as in <a href="https://angular.dev/best-practices/security#sanitization-and-security-contexts.">Sanitization Page </a> in the Angular Docs.
Data security in the frontend is granted by Java model classes contained in the backend. Data parsed to the frontend is returned in <code>Projections</code> of the database's tables.


## Run
1. To run the application you will need to run this code as a Spring Boot application, as in the [VseetApplication](main).
2. Once the Spring Boot application is running, you will need npm and Angular CLI installed in your system.
3. You will need to navigate on the command prompt to the [frontend](frontend) folder, and run the command
    <code>$ng serve</code>.
4. At this point you will open a browser window and route to <u>http://localhost:4200</u>, and you'll have successfully run 
    the application and can interact with all the features in it.

<b>The application loads 4 basic users to log in (all with password: <i>password</i>), to try the different
scenarios and permissions the system puts you in:</b>

alessio.re@example.it -> Platform Manager (Suggested for more interactions)</br>
andrea.bianchi@example.it -> Animator</br>
mario.rossi@example.it -> Contributor</br>
anna.verdi@example.it -> Tourist</br>

Or you can create your own profile, via the "Sign up" page with your own email and password.

## PWA Implementation
This application is implemented also as a <b>Progressive Web App</b>. To run the PWA, navigate to [frontend](frontend) folder
and run in a command prompt (bash) the code:</br>
<code>$npm run build:pwa</br>
$npm run serve:pwa
</code></br>

This commands will run the PWA. To access it navigate to <u>http://localhost:8081</u> and then try all the features of the application from your desktop!



### License
Copyright (c) 2025 Alessio Re

The license for this repository is MIT License.

Please see the [LICENSE](LICENSE) file for full reference.
