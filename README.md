# ICC-Desarollo-Movil

### Tarea #3 (Lista de Tareas) - Informe:
>**Manejo de Imagenes:**
En el proceso de desarrollo de la aplicación, se buscó inicialmente utilizar la plataforma "Picsum", que proporciona imágenes aleatorias, como fuente para las fotos en los ítems del RecycleView. Sin embargo, esto resultó ser problemático, ya que las bibliotecas de manejo de imágenes, como Glide y Picasso, no lograron cargar las imágenes correctamente. Además, se exploraron otras fuentes de imágenes aleatorias, pero todas enfrentaron desafíos similares. Por esta razón, se optó por un enfoque alternativo en donde se usaron imágenes predefinidas como recursos, de las cuales se seleccionaría una aleatoriamente al crear un item. Aunque este método fue más sencillo de implementar, no logró proporcionar la funcionalidad deseada en un principio ya que está limitado a las imágenes que se suban como recursos.

>**Uso de Diálogos:**
Un aspecto interesante del desarrollo fue el uso de diálogos. Inicialmente, se pretendía tener un botón para agregar tareas que serían compartidas entre los diferentes listados. Sin embargo, para simplificar la gestión en la siguiente asignación, se decidió separar esta funcionalidad. Dado su inicio como un botón compartido, se optó por implementar esta función utilizando un diálogo. Esta decisión se mantuvo a lo largo del proceso de desarrollo y resultó interesante debido a la falta de un editor visual para diseñar dichos diálogos. A pesar de que el diseño en sí era relativamente sencillo, se tuvo que agregar un LinearLayout en código para lograr un margen de 48 píxeles, lo que introdujo cierta complejidad inesperada pero interesante como quiera.

>**Manejo de ViewModels:**
La gestión de ViewModels se convirtió en un desafío adicional, impulsado por ambiciones iniciales de utilizar LiveData para un mejor control de los elementos y para anticipar la asignación siguiente. Sin embargo, debido a limitaciones de tiempo, esta parte del proyecto se volvió demasiado complicada y se pospuso para un futuro cercano. A pesar de los desafíos encontrados, este proceso permitió adquirir un mayor entendimiento sobre el tema, aunque no será parte del proyecto en este momento.

>De manera general, y dado a que se lograron los requisitos de la asignación, considero que se han logrados los objetivos propuestos y se encontraron áreas en donde debo mejorar para seguir progresando en la materia y en mis estudios.

### Tarea #4 (Lista de Tareas 2.0) - Informe:
>El desafío más significativo que enfrenté se relacionó con algunas incertidumbres en los temas que se trataron en clase, en particular, la conexión entre el adaptador (adapter) y el ViewModel. Dado que el adaptador tenía funciones que modificaban los datos de alguna manera a través de botones de eliminación y marcado como completado, se requería una forma de enviar los datos de vuelta al ViewModel. Inicialmente, intenté utilizar Callbacks siguiendo información que encontré en Internet, pero esto complicaba el código y lo hacía menos legible.

>Una solución que resultó efectiva, aunque implicaba sacrificar cierta abstracción en favor de una mayor legibilidad, fue pasar una instancia del ViewModel al adaptador para que pudiera ser llamado directamente cuando fuera necesario. Sin embargo, este enfoque tenía sus desventajas, como se mencionó anteriormente.

>Finalmente, la solución que se adoptó fue la utilización de Consumers para llevar a cabo las acciones y definir las funciones dentro del fragmento que gestionaba la vista donde ya se tenía el ViewModel.

>Otro desafío importante fue la configuración de la base de datos Room. A pesar de que las anotaciones eran simples, el proceso se complicó debido a la cantidad de clases requeridas, lo que inicialmente me desconcertó, ya que no podía seguir el flujo de información. Después de analizar los archivos y el proceso, así como consultar algunos tutoriales, logré comprenderlo y llevar a cabo la configuración de manera correcta.

### Tarea #5 (App en Flutter) - Informe:
> Al inicio se me dificulto decidirme en qué proyecto hacer ya que la tarea era abierta. Inicie tratando de hacer una galería de fotos infinita usando picsum.photos, un generador de fotos aleatorias. Para esto se requería un manejo de apis el cual no habíamos tocado en clase asi que decidi buscar otro proyecto. Decidí hacer un manejador de gastos, ya que es algo que me ha interesado recientemente. Inicie creando dos tabs ya que quería tener un listado de gastos y otro de categorías. Utilizando los componentes básicos de material cree uno navbar básico pero no me gusto así que busqué librerías externas para terminar en el navbar animado con el cual acabe.

> Pasando a las categorías, esto inició como un simple crud para agregar y eliminar strings de una lista pero después decidí que quería asignarles un color aleatorio. Para esto tuve que crear un modelo, lo cual fue muy similar como en java, y por ende fue fácil incorporarlo a la lista. Pasando al listado de gastos, lo más difícil fue la gestión de su organización. Quería tener gastos con la misma fecha bajo un solo bloque, lo cual me hizo pensar un diferente en la manera en que se creaban los ítems de una lista. También utilicé ListTile, lo cual fue perfecto para el uso que necesitaba dado a sus atributos de subtitle, title y trailing. En un principio utilicé otra estructura pero esto resultó mucho más complicado. ListTile fue la solución perfecta.

> Por último está el gráfico, lo cual pensé que iba a ser sencillo dado a lo fácil que fue el manejo del navbar externo pero claramente no es lo mismo. El gráfico requirió mucha lógica y tuve que leer mucha documentación pero viendo ejemplos y buscando tutoriales, se logró y quedó muy bien en mi opinión.

> Hasta ahora pienso que flutter es mucho más fácil y versátil que android, aunque se pierde mucha de la personalización que se tenían con los layouts y el editor de código visual. Tome esta tarea sencilla para adelantarme y practicar más con flutter. También me di cuenta de lo complicado que se ve el código cuando está todo junto y sin comentarios. Tome esa experiencia y al iniciar el proyecto final ayer, configuré junto a mi compañero una estructura de código mucho más organizada y legible.

