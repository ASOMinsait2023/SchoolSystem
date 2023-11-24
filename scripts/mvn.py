import subprocess
import argparse
import sys
import os

def ejecutar_comando_maven(comando):
    try:
        # Ejecutar el comando Maven
        resultado = subprocess.Popen(["mvn"] + comando, text=True, shell=True)

        # Mostrar el log
        print(resultado.stdout)

    except subprocess.CalledProcessError as e:
        # Mostrar el log de error en caso de fallo
        print("Error: ", e.returncode, e.output)

if __name__ == "__main__":
    if "SchoolSystem.iml" not in os.listdir():
        print("This script must be executed in the root folder of the project")
        sys.exit(1)
    # Configurar el analizador de argumentos
    parser = argparse.ArgumentParser(
        description="Maven Scripts",
        epilog="""
            Examples:
            python mvn.py
            python mvn.py --command "clean install"
            python mvn.py --command "clean install -DskipTests"
        """
    )
    parser.add_argument('--command', help='Maven command to execute')
    parser.add_argument('--clean', help='Clean maven project')
    parser.add_argument('--install', help='Install maven project')
    parser.add_argument('--skipTests', help='Skip tests')
    parser.add_argument('--test', help='Test maven project')
    parser.add_argument('--testModule', help='Test maven module')
    parser.add_argument('--package', help='Package maven project')


    # Analizar los argumentos de la línea de comandos
    args = parser.parse_args()

    # Comprobar si se proporcionó un comando personalizado
    if args.command:
        comando_personalizado = args.command.split()
        ejecutar_comando_maven(comando_personalizado)
    elif args.clean:
        ejecutar_comando_maven(['clean'])
    elif args.install:
        ejecutar_comando_maven(['install'])
    elif args.skipTests:
        ejecutar_comando_maven(['install','-DskipTests'])
    elif args.test:
        ejecutar_comando_maven(['test'])
    elif args.testModule:
        ejecutar_comando_maven(['test','-pl',args.testModule])
    elif args.package:
        ejecutar_comando_maven(['package'])
    else:
        # Ejecutar el comando Maven por defecto
        ejecutar_comando_maven(['clean', 'install'])
