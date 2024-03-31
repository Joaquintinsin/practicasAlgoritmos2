# practicasAlgoritmosII
Mi repositorio para la materia Algoritmos 2 / Diseño de Algoritmos
Universidad Nacional de Río Cuarto
Año 2024

# Quid novi
Archivos de Java y Haskell que principalmente corresponderán a las prácticas dadas en clase.

# Compilación de Java
Algunos archivos .java cuentan con ser proyectos Maven.
Por lo tanto, es posible compilarlos, correrlos y/o testearlos via Maven:

Pararse vía terminal en donde se encuentre el archivo <b>pom.xml</b>

En caso que haya un método main, ejecutar mediante mvn exec:java -Dexec.mainClass=<b><paquete></b>.<b><Clase principal></b>

Para Windows agregar 'c antes de exec <i>(Creo)</i>.

# Interprete de Haskell
Interpretamos los archivos de extensión <b>.hs</b> usando GHCI (Glasglow Haskell Compiler)

En Linux, abrir la terminal en la posición del archivo deseado a interpretar.

Usando el comando <i>ghci <b>nombreArchivo</b>.hs</i> se interpretará el archivo.

El intérprete puede abrirse individualmente usando solamente <i>ghci</i>.

En ese caso, usar <i>:l <b>nombreArchivo</b>.hs</i> si se quiere interpretar algún archivo desde el mismo intérprete.

Si se hacen cambios en el mismo archivo que estamos interpretando actualmente, con <i>:r</i> recargamos la interpretación.

# Compilación de Haskell
En caso de existir un proyecto stack, compilar, ejecutar y/o testear según correspondan los comandos stack.

·	stack build -- To build the project

·	stack test -- To test the specs

·	stack bench -- To run the benchmarks

·	stack exec <Path_to_listSorter-exe> -- To run main app
