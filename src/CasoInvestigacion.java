import java.util.ArrayList;

public class CasoInvestigacion {

    private String nombre;
    private String codigo;
    private String detectiveResponsable;

    // Arreglo básico obligatorio de 5 ubicaciones.
    private Ubicacion[] ubicaciones;

    // Colección dinámica obligatoria de pistas.
    private ArrayList<Pista> pistas;

    public CasoInvestigacion(
            String nombre,
            String codigo,
            String detectiveResponsable
    ) {
        setNombre(nombre);
        setCodigo(codigo);
        setDetectiveResponsable(detectiveResponsable);

        ubicaciones = new Ubicacion[5];
        pistas = new ArrayList<Pista>();
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    campo + " no puede estar vacío."
            );
        }
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException(
                    "La posición debe estar entre 0 y 4."
            );
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetectiveResponsable() {
        return detectiveResponsable;
    }

    public void setNombre(String nombre) {
        validarTexto(nombre, "El nombre del caso");
        this.nombre = nombre.trim();
    }

    public void setCodigo(String codigo) {
        validarTexto(codigo, "El código del caso");
        this.codigo = codigo.trim();
    }

    public void setDetectiveResponsable(String detectiveResponsable) {
        validarTexto(
                detectiveResponsable,
                "El detective responsable"
        );

        this.detectiveResponsable = detectiveResponsable.trim();
    }

    // ---------------- UBICACIONES ----------------

    public void registrarUbicacion(
            int posicion,
            Ubicacion ubicacion
    ) {
        validarPosicion(posicion);

        if (ubicacion == null) {
            throw new IllegalArgumentException(
                    "La ubicación no puede ser null."
            );
        }

        if (ubicaciones[posicion] != null) {
            throw new IllegalStateException(
                    "La posición ya está ocupada."
            );
        }

        ubicaciones[posicion] = ubicacion;
    }

    public Ubicacion obtenerUbicacion(int posicion) {
        validarPosicion(posicion);

        if (ubicaciones[posicion] == null) {
            throw new IllegalStateException(
                    "La posición está vacía."
            );
        }

        return ubicaciones[posicion];
    }

    public String consultarUbicaciones() {
        String resultado = "";

        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                resultado += "Posición " + i + ": "
                        + ubicaciones[i] + "\n";
            }
        }

        if (resultado.isEmpty()) {
            return "No hay ubicaciones registradas.";
        }

        return resultado;
    }

    public void modificarUbicacion(
            int posicion,
            int nuevoRiesgo,
            String nuevoEstado
    ) {
        Ubicacion ubicacion = obtenerUbicacion(posicion);

        ubicacion.setNivelRiesgo(nuevoRiesgo);
        ubicacion.setEstado(nuevoEstado);
    }

    public void descartarUbicacion(int posicion) {
        // También comprueba que la posición exista y no esté vacía.
        obtenerUbicacion(posicion);

        ubicaciones[posicion] = null;
    }

    // ---------------- PISTAS ----------------

    public void registrarPista(Pista pista) {
        if (pista == null) {
            throw new IllegalArgumentException(
                    "La pista no puede ser null."
            );
        }

        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una pista con ese código."
            );
        }

        pistas.add(pista);
    }

    public Pista buscarPista(String codigoPista) {
        for (Pista pista : pistas) {
            if (pista.getCodigo().equalsIgnoreCase(codigoPista)) {
                return pista;
            }
        }

        return null;
    }

    public String consultarPistas() {
        if (pistas.isEmpty()) {
            return "No hay pistas registradas.";
        }

        String resultado = "";

        for (Pista pista : pistas) {
            resultado += pista + "\n";
        }

        return resultado;
    }

    public void modificarPista(
            String codigoPista,
            String descripcion,
            String tipoEvidencia,
            int importancia,
            int confiabilidad
    ) {
        Pista pista = buscarPista(codigoPista);

        if (pista == null) {
            throw new IllegalArgumentException(
                    "No se encontró la pista."
            );
        }

        pista.setDescripcion(descripcion);
        pista.setTipoEvidencia(tipoEvidencia);
        pista.setNivelImportancia(importancia);
        pista.setNivelConfiabilidad(confiabilidad);
    }

    public boolean eliminarPista(String codigoPista) {
        Pista pista = buscarPista(codigoPista);

        if (pista == null) {
            return false;
        }

        pistas.remove(pista);
        return true;
    }

    // ---------------- CÁLCULOS ----------------

    public int contarUbicaciones() {
        int cantidad = 0;

        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int contarEspaciosDisponibles() {
        return ubicaciones.length - contarUbicaciones();
    }

    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;

        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null) {
                if (mayor == null
                        || ubicacion.getNivelRiesgo()
                        > mayor.getNivelRiesgo()) {
                    mayor = ubicacion;
                }
            }
        }

        return mayor;
    }

    public int contarPistas() {
        return pistas.size();
    }

    public Pista pistaMayorImportancia() {
        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (Pista pista : pistas) {
            if (pista.getNivelImportancia()
                    > mayor.getNivelImportancia()) {
                mayor = pista;
            }
        }

        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {
        if (pistas.isEmpty()) {
            return null;
        }

        Pista mayor = pistas.get(0);

        for (Pista pista : pistas) {
            if (pista.getNivelConfiabilidad()
                    > mayor.getNivelConfiabilidad()) {
                mayor = pista;
            }
        }

        return mayor;
    }

    public double promedioImportancia() {
        if (pistas.isEmpty()) {
            return 0;
        }

        int suma = 0;

        for (Pista pista : pistas) {
            suma += pista.getNivelImportancia();
        }

        return (double) suma / pistas.size();
    }

    public String generarReporte() {
        String reporte = "\n--- REPORTE DE INVESTIGACIÓN ---\n";

        reporte += "Caso: " + nombre + "\n";
        reporte += "Código: " + codigo + "\n";
        reporte += "Detective: " + detectiveResponsable + "\n";
        reporte += "Ubicaciones registradas: "
                + contarUbicaciones() + "\n";
        reporte += "Espacios disponibles: "
                + contarEspaciosDisponibles() + "\n";

        Ubicacion mayorRiesgo = ubicacionMayorRiesgo();

        if (mayorRiesgo == null) {
            reporte += "Ubicación con mayor riesgo: No hay ubicaciones.\n";
        } else {
            reporte += "Ubicación con mayor riesgo: "
                    + mayorRiesgo + "\n";
        }

        reporte += "Pistas registradas: " + contarPistas() + "\n";

        if (pistas.isEmpty()) {
            reporte += "No hay pistas para realizar cálculos.\n";
        } else {
            reporte += "Pista con mayor importancia: "
                    + pistaMayorImportancia() + "\n";

            reporte += "Pista con mayor confiabilidad: "
                    + pistaMayorConfiabilidad() + "\n";

            reporte += String.format(
                    "Promedio de importancia: %.2f%n",
                    promedioImportancia()
            );
        }

        return reporte;
    }
}