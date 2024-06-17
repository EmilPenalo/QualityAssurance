# Practica 2
Este repositorio contiene un varias practicas, por lo que, a continuación se detalla cómo clonar el proyecto, cambiar al branch correspondiente, configurar IntelliJ y ejecutar la aplicación y las pruebas.

## Repositorio del Proyecto

```bash
https://github.com/EmilPenalo/QualityAssurance.git
```

## Cambio de Branch usando consola
Para entrar a la práctica 2, debes cambiar al branch _practica2_. Esto se puede hacer clonando solo ese branch con el siguiente comando de git:

```bash
git clone --single-branch --branch practica2 https://github.com/EmilPenalo/QualityAssurance.git
```
Como alternativa, se puede utilizar IntelliJ para el cambio de branch.

## Configuración en IntelliJ
Luego de clonar el proyecto completo desde IntelliJ, se cambia de branch con los siguientes pasos:

- Ve a las opciones de control de versiones.
- Bajo los _remotes_, selecciona el branch _practica2_.
- Selecciona la opción de _Checkout_.
- Si es necesario, selecciona _Force Checkout_ en la confirmación. 

![image](https://github.com/EmilPenalo/QualityAssurance/assets/87092621/ea2d5148-b09e-4830-afcd-2d84843af7ef)

> Carga del Proyecto: Deja que el proyecto cargue completamente. Esto puede demorar un momento. En pruebas realizadas, fue necesario reiniciar IntelliJ, ya que el proyecto permanecía cargando por demasiado tiempo.

## Ejecución de la Aplicación
Este proyecto utiliza Spring Boot. Para ejecutar la aplicación:

- Corre _Practica2Application_.
- Accede a _localhost:8080_ en tu navegador.

## Ejecución de los Tests
Los tests están divididos en los siguientes archivos:

- ContactTest
- LoginTest
- TodoTest

Estos archivos de test se ejecutan de manera independiente y configuran el entorno que necesitan utilizando su herencia de PlaywrightIT, donde se define el formato de las pruebas.

## Requisitos del Sistema
- Java 17+ (Java 21 recomendado)
- IntelliJ IDEA (recomendado)
