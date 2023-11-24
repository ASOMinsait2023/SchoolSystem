# School System

## Integrantes:
- Rodrigo Garcia Moctezuma.
- Johan Argenis Franco Rogel.
- Mario Alexis Amaro Romero.

## Description:

Final project for ASO academy. This project is a school platform which simulates
a School activity where Students and Teachers are in a Classroom where a Subject is
taught by a Teacher. Teachers can add Students to a determinate classroom and know all
the information about them.

##How to use:
- Clone the repository.
- Open the project in your Terminal.
- If you have Python 3.*.* installed, run the following command:
```bash	python .\scripts\mvn.py --package
```
- If you want to see other command options, run the following command:
```bash	python .\scripts\mvn.py --help
```
- All the jar files will be in the target folder of each module.
- To run the jar files, run the following command:
```bash	java -jar .\<Module_Name>\target\<Module_Name>-0.0.1-SNAPSHOT.jar
``` 
- Order modules to run:
    1. Configuratiom
    2. Eureka
    3. Subject
    4. Teacher
    5. Students
    6. Classroom
    7. Gateway (Optional)