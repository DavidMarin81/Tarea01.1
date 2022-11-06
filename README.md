# Tarea01.1
Tarea01.1 de la primera evaluacion
Partiremos del proyecto que encontraréis en el repositorio https://github.com/adp-code-2223/Tarea01.1-NUEVA-Enunciado.git
En la clase DataIOPersistencia implementa, utilizando la clases DataInputStream y DataOutputStream, los métodos de la interfaz IPersistencia:

- void escribirPersonas(ArrayList<Persona> personas, String ruta);
  - Escribirá los datos de cada persona en el fichero indicado por ruta. Si el archivo ya tenía datos, deberán añadirse al final del fichero, no sobrescribirá desde el principio.
  - La longitud máxima de caracteres para el nombre de una persona viene determinado por la constante Persona.MAX_LENGTH_NOMBRE.
  - El dni tiene una longitud fija determinada por la constante Persona.MAX_LENGTH_DNI
  - Utiliza el método writeChars en lugar de writeUTF.

- ArrayList<Persona> leerTodo (String ruta);
    - Leerá todo el fichero indicado por el parámetro ruta. Deberá leer los datos en el mismo orden que la escritura. 
    - Generará objetos de la clase Persona y los devolverá en un ArrayList.

En ambos métodos, controla las posibles excepciones con try-with-resources.

A continuación completa el método cribarBorrados() y filtrarPersonas() de la clase Tarea01_1.java para que, utilizando la clase DataIOPersistencia, se lea el fichero origen.dat, se filtren las personas cuyo atributo borrado sea false y se cree una lista de las personas que no están borradas. Con ellas, se creará un nuevo fichero.

Modidica Tarea01_1.java siguiendo los pasos a continuación:

  - Completa el método ArrayList<Persona> filtrarPersonas(ArrayList<Persona> personas) para que devuelva un ArrayList<Persona> con las personas que no han sido borradas. No se podrá filtrar con streams: personas.stream().filter(p -> !p.isBorrado()).collect(Collectors.toList())

  - Completa el método cribarBorrados()  para que haga uso de ArrayList<Persona> filtrarPersonas(ArrayList<Persona> personas). Con la lista de personas filtradas, ha de crearse un nuevo fichero  en el destino indicado por PERSONAS_DESTINO_PATH haciendo uso de un método de la clase   DataIOPersistencia. Si el fichero ya existía en el destino, deberá borrarse previamente a la creación del fichero.

  - Por último, el fichero en la ruta PERSONAS_ORIGEN_PATH, deberá moverse a la ruta PERSONAS_ORIGEN_COPIA_PATH, sobrescribiéndose el destino en caso de existir ya.
  
EVALUACIÓN:

Además de los aspectos arriba mencionados:

  - Deberá utilizarse la clase Files en lugar de File siempre que sea posible
  - Deberán controlarse las excepciones en el propio método cribarBorrados() y no lanzar las excepciones.
  - Solo se podrán modificar las clases DataIOPersistencia.java y Tarea01_1.java

