call mvn clean install
start run-auth.bat
call cd server && start mvn spring-boot:run && cd ../
call cd client-admin && start npm start && cd ../
call cd client-student && start npm start && cd ../
call cd client-tutor && start npm start && cd ../
