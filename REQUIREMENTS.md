Instrucciones para IA - Desafío Práctico: App de Países y Clima

Este documento detalla los requisitos para desarrollar (y mejorar) una aplicación Android en Kotlin. La IA debe seguir todos los puntos descritos para asegurar que la implementación sea correcta.

Información Clave y Contexto

API Key de OpenWeather: b606774ec77d4d1e8d133554253010 (Usar esta clave para todas las llamadas a la API de clima).

Código Existente: Ya existe una base de código o lógica para la gestión de países. Tu tarea principal es mejorar, completar y refactorizar esta lógica para cumplir con todos los requisitos funcionales y técnicos, asegurando una arquitectura MVC limpia y un manejo de errores robusto.

Checklist de Tareas de Desarrollo

1. Requisitos Funcionales (Pantallas y UX)

[ ] Pantalla 1: Lista de Regiones

Debe ser la pantalla inicial de la aplicación.

Mostrar una lista de las regiones principales (ej. Americas, Europe, Asia, Africa, Oceania). Estos datos pueden obtenerse del endpoint all de RestCountries y luego agruparse.

[ ] Pantalla 2: Países por Región

Al seleccionar una región de la Pantalla 1, navegar a esta pantalla.

Mostrar una lista de países pertenecientes a la región seleccionada.

Cada elemento de la lista debe mostrar:

Nombre del país.

Bandera del país (cargar con Glide o Coil).

Nombre de la capital.

[ ] Pantalla 3: Detalle del País y Clima

Al seleccionar un país de la Pantalla 2, navegar a esta pantalla.

Sección de Información del País (API: RestCountries):

Nombre oficial.

Bandera (en tamaño más grande).

Capital.

Región y Subregión.

Población.

Códigos ISO.

Monedas (listar las monedas que utiliza).

Idiomas (listar los idiomas).

Latitud y Longitud (latlng).

Sección de Clima (API: WeatherAPI):

Obtener la capital de la llamada anterior (RestCountries).

Usar la capital para consultar el endpoint de WeatherAPI.

Mostrar:

Temperatura (en °C).

Condición del clima (ej. "Soleado", "Parcialmente nublado").

Velocidad del viento (en kph).

Humedad (%).

Icono del estado del clima (cargar con Glide o Coil).

[ ] Experiencia de Usuario (UX)

Implementar indicadores de carga (Spinners, Shimmers o ProgressBars) mientras se espera la respuesta de las APIs.

La navegación entre pantallas debe ser clara e intuitiva (ej. usando un Navigation Component o Intents claros).

2. Requisitos Técnicos (Arquitectura y Librerías)

[ ] Arquitectura MVC

Aunque ahorita así como está estructurado el proyecto está correcto.

Refactorizar el código existente para adherirse estrictamente al patrón Modelo-Vista-Controlador (MVC).

Modelo: Clases de datos (POJOs/Data Classes) que representan la respuesta de las APIs (País, Clima, etc.) y la lógica de negocio si la hubiera.

Vista: Activities/Fragments (XML o Jetpack Compose). Deben ser "tontas" y solo responsables de mostrar datos y recibir interacciones del usuario.

Controlador: Clases (o ViewModels, aunque el requisito pide MVC) que manejan la lógica de la aplicación, realizan las llamadas a la API y actualizan la Vista con los datos del Modelo.

[ ] Consumo de APIs (Retrofit + Gson)

Configurar Retrofit para consumir las dos APIs.

Configurar Gson (o Moshi) como el convertidor (converter) para parsear las respuestas JSON a las clases del Modelo.

API 1: RestCountries

Base URL: https://restcountries.com/

Endpoints a implementar: GET /v3.1/region/{region} (para la Pantalla 2) y GET /v3.1/all (si es necesario para la Pantalla 1).

API 2: WeatherAPI

Base URL: http://api.weatherapi.com/v1

Endpoint a implementar: GET /current.json

Llamada de ejemplo: GET /current.json?key=b606774ec77d4d1e8d133554253010&q={nombre_de_la_capital}

[ ] Carga de Imágenes (Glide o Coil)

Integrar Glide o Coil en el proyecto.

Usar la librería para cargar todas las imágenes desde URLs (banderas e iconos del clima) en los ImageViews correspondientes.

[ ] Manejo de Errores (Crítico)

Implementar un manejo de errores robusto para las llamadas de red.

Mostrar mensajes amigables al usuario (ej. usando Toasts, Snackbars o un TextView en la pantalla) para los siguientes casos:

Error de red (Sin conexión a Internet).

Timeout de la solicitud.

API Key inválida (Error 401 o 403).

Ciudad no encontrada (Error 404 o similar de la API de clima).

Errores genéricos del servidor (5xx).

[ ] Icono de la Aplicación

Asegurarse de que la aplicación tenga un icono personalizado (reemplazar el icono por defecto de Android en los directorios mipmap).