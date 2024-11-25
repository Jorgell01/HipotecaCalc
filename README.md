# HipotecaCalc

HipotecaCalc es una aplicación JavaFX para calcular las cuotas de una hipoteca. La aplicación permite ingresar el capital, el interés y el plazo de la hipoteca, y muestra una tabla con el desglose de las cuotas.

## Requisitos

- Java 11 o superior
- Maven 3.6.0 o superior

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/Jorgell01/HipotecaCalc.git
    cd HipotecaCalc
    ```

2. Compila el proyecto usando Maven:
    ```sh
    mvn clean install
    ```

## Uso

1. Ejecuta la aplicación:
    ```sh
    mvn clean compile
    mvn exec:java
    ```
#### Estado inicial
Aquí puedes ver cómo luce la aplicación cuando está vacía, sin datos ingresados:   
![Vista inicial](images/appdefault.png)

2. Ingresa el capital, el interés y el plazo de la hipoteca en los campos correspondientes.

#### Datos ingresados
Este es el aspecto de la aplicación después de ingresar el capital, interés y plazo:
![Vista con datos](images/appcondatos.png)

3. Haz clic en el botón "Calcular" para obtener el desglose de las cuotas.

## Contribuciones

¡Las contribuciones son bienvenidas! Para contribuir, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Sube tus cambios a tu fork (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request en el repositorio original.
