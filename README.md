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
