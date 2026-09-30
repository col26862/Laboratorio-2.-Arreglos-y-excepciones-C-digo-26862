import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

     static final Scanner TECLADO = new Scanner(System.in);
    private static CasoInvestigacion casoActual;

    public static void main(String[] args) {
        System.out.println("=== AGENCIA DE DETECTIVES ===");

        crearNuevoCaso();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 13);

        TECLADO.close();
        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicación");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicación");
        System.out.println("5. Modificar ubicación");
        System.out.println("6. Descartar ubicación");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigación");
        System.out.println("13. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearNuevoCaso();
                break;

            case 2:
                registrarUbicacion();
                break;

            case 3:
                System.out.println(
                        casoActual.consultarUbicaciones()
                );
                break;

            case 4:
                consultarUnaUbicacion();
                break;

            case 5:
                modificarUbicacion();
                break;

            case 6:
                descartarUbicacion();
                break;

            case 7:
                registrarPista();
                break;

            case 8:
                System.out.println(
                        casoActual.consultarPistas()
                );
                break;

            case 9:
                buscarPista();
                break;

            case 10:
                modificarPista();
                break;

            case 11:
                eliminarPista();
                break;

            case 12:
                System.out.println(
                        casoActual.generarReporte()
                );
                break;

            case 13:
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);

                int valor = TECLADO.nextInt();
                TECLADO.nextLine();

                return valor;
            } catch (InputMismatchException e) {
                System.out.println(
                        "Error: debe ingresar un número entero."
                );

                // Limpia la entrada incorrecta.
                TECLADO.nextLine();
            }
        }
    }

    private static String leerTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = TECLADO.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(
                        "El dato no puede estar vacío."
                );
            }
        } while (texto.isEmpty());

        return texto;
    }

    private static void crearNuevoCaso() {
        try {
            String nombre = leerTexto(
                    "Nombre del caso: "
            );

            String codigo = leerTexto(
                    "Código del caso: "
            );

            String detective = leerTexto(
                    "Detective responsable: "
            );

            casoActual = new CasoInvestigacion(
                    nombre,
                    codigo,
                    detective
            );

            System.out.println(
                    "Caso creado correctamente."
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "No fue posible crear el caso: "
                    + e.getMessage()
            );
        }
    }

    private static void registrarUbicacion() {
        try {
            int posicion = leerEntero(
                    "Posición del arreglo (0-4): "
            );

            String codigo = leerTexto(
                    "Código: "
            );

            String nombre = leerTexto(
                    "Nombre: "
            );

            String direccion = leerTexto(
                    "Dirección o descripción: "
            );

            int riesgo = leerEntero(
                    "Nivel de riesgo (1-10): "
            );

            String estado = leerTexto(
                    "Estado: "
            );

            Ubicacion ubicacion = new Ubicacion(
                    codigo,
                    nombre,
                    direccion,
                    riesgo,
                    estado
            );

            casoActual.registrarUbicacion(
                    posicion,
                    ubicacion
            );

            System.out.println(
                    "Ubicación registrada correctamente."
            );
        } catch (IllegalArgumentException
                 | IndexOutOfBoundsException
                 | IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        } finally {
            /*
             * Este bloque se ejecuta siempre, independientemente
             * de que la ubicación haya sido registrada o se haya
             * producido una excepción.
             */
            System.out.println(
                    "Operación de registro finalizada."
            );
        }
    }

    private static void consultarUnaUbicacion() {
        try {
            int posicion = leerEntero(
                    "Posición de la ubicación (0-4): "
            );

            Ubicacion ubicacion =
                    casoActual.obtenerUbicacion(posicion);

            System.out.println(ubicacion);
        } catch (IndexOutOfBoundsException
                 | IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void modificarUbicacion() {
        try {
            int posicion = leerEntero(
                    "Posición de la ubicación (0-4): "
            );

            int riesgo = leerEntero(
                    "Nuevo nivel de riesgo (1-10): "
            );

            String estado = leerTexto(
                    "Nuevo estado: "
            );

            casoActual.modificarUbicacion(
                    posicion,
                    riesgo,
                    estado
            );

            System.out.println(
                    "Ubicación modificada correctamente."
            );
        } catch (IllegalArgumentException
                 | IndexOutOfBoundsException
                 | IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void descartarUbicacion() {
        try {
            int posicion = leerEntero(
                    "Posición de la ubicación (0-4): "
            );

            casoActual.descartarUbicacion(posicion);

            System.out.println(
                    "Ubicación descartada correctamente."
            );
        } catch (IndexOutOfBoundsException
                 | IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void registrarPista() {
        try {
            Pista pista = leerDatosPista();

            casoActual.registrarPista(pista);

            System.out.println(
                    "Pista registrada correctamente."
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static Pista leerDatosPista() {
        String codigo = leerTexto(
                "Código: "
        );

        String descripcion = leerTexto(
                "Descripción: "
        );

        String tipo = leerTexto(
                "Tipo de evidencia: "
        );

        int importancia = leerEntero(
                "Nivel de importancia (1-10): "
        );

        int confiabilidad = leerEntero(
                "Nivel de confiabilidad (0-100): "
        );

        return new Pista(
                codigo,
                descripcion,
                tipo,
                importancia,
                confiabilidad
        );
    }

    private static void buscarPista() {
        String codigo = leerTexto(
                "Código de la pista: "
        );

        Pista pista = casoActual.buscarPista(codigo);

        if (pista == null) {
            System.out.println(
                    "Pista no encontrada."
            );
        } else {
            System.out.println(pista);
        }
    }

    private static void modificarPista() {
        try {
            String codigo = leerTexto(
                    "Código de la pista: "
            );

            String descripcion = leerTexto(
                    "Nueva descripción: "
            );

            String tipo = leerTexto(
                    "Nuevo tipo de evidencia: "
            );

            int importancia = leerEntero(
                    "Nueva importancia (1-10): "
            );

            int confiabilidad = leerEntero(
                    "Nueva confiabilidad (0-100): "
            );

            casoActual.modificarPista(
                    codigo,
                    descripcion,
                    tipo,
                    importancia,
                    confiabilidad
            );

            System.out.println(
                    "Pista modificada correctamente."
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void eliminarPista() {
        String codigo = leerTexto(
                "Código de la pista: "
        );

        boolean eliminada =
                casoActual.eliminarPista(codigo);

        if (eliminada) {
            System.out.println(
                    "Pista eliminada correctamente."
            );
        } else {
            System.out.println(
                    "No se encontró una pista con ese código."
            );
        }
    }
}