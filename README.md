# Suma de Vector con Threads

Se sumara un vector a trozos con la ayuda de hilos, usando la clase **Thread** con dos enfoques:
1. Uso de exclusión mutua: Se utilizara un recurso **Recurso**, el cual modificara cada hilo con el fin de actualizar el valor de la suma, el cual representa la sección critica, por lo cual utilizaremos un **Lock**.
2. Uso de algoritmo paralelo: Se utilizaran hilos para realizar las operaciones de sumado por secciones y se almacenara ese valor en una variable para cada hilo, al final el hilo main calsulara la suma total.