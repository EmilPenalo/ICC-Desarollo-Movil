# ICC-Desarollo-Movil

### Tarea #3 (Lista de Tareas) - Informe:
>**Manejo de Imagenes:**
En el proceso de desarrollo de la aplicación, se buscó inicialmente utilizar la plataforma "Picsum", que proporciona imágenes aleatorias, como fuente para las fotos en los ítems del RecycleView. Sin embargo, esto resultó ser problemático, ya que las bibliotecas de manejo de imágenes, como Glide y Picasso, no lograron cargar las imágenes correctamente. Además, se exploraron otras fuentes de imágenes aleatorias, pero todas enfrentaron desafíos similares. Por esta razón, se optó por un enfoque alternativo en donde se usaron imágenes predefinidas como recursos, de las cuales se seleccionaría una aleatoriamente al crear un item. Aunque este método fue más sencillo de implementar, no logró proporcionar la funcionalidad deseada en un principio ya que está limitado a las imágenes que se suban como recursos.

>**Uso de Diálogos:**
Un aspecto interesante del desarrollo fue el uso de diálogos. Inicialmente, se pretendía tener un botón para agregar tareas que serían compartidas entre los diferentes listados. Sin embargo, para simplificar la gestión en la siguiente asignación, se decidió separar esta funcionalidad. Dado su inicio como un botón compartido, se optó por implementar esta función utilizando un diálogo. Esta decisión se mantuvo a lo largo del proceso de desarrollo y resultó interesante debido a la falta de un editor visual para diseñar dichos diálogos. A pesar de que el diseño en sí era relativamente sencillo, se tuvo que agregar un LinearLayout en código para lograr un margen de 48 píxeles, lo que introdujo cierta complejidad inesperada pero interesante como quiera.

>**Manejo de ViewModels:**
La gestión de ViewModels se convirtió en un desafío adicional, impulsado por ambiciones iniciales de utilizar LiveData para un mejor control de los elementos y para anticipar la asignación siguiente. Sin embargo, debido a limitaciones de tiempo, esta parte del proyecto se volvió demasiado complicada y se pospuso para un futuro cercano. A pesar de los desafíos encontrados, este proceso permitió adquirir un mayor entendimiento sobre el tema, aunque no será parte del proyecto en este momento.

>De manera general, y dado a que se lograron los requisitos de la asignación, considero que se han logrados los objetivos propuestos y se encontraron áreas en donde debo mejorar para seguir progresando en la materia y en mis estudios.
