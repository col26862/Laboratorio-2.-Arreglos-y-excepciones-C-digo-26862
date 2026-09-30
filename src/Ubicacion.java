public class Ubicacion {

    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado;

    public Ubicacion(
            String codigo,
            String nombre,
            String direccion,
            int nivelRiesgo,
            String estado
    ) {
        setCodigo(codigo);
        setNombre(nombre);
        setDireccion(direccion);
        setNivelRiesgo(nivelRiesgo);
        setEstado(estado);
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    campo + " no puede estar vacío."
            );
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo(String codigo) {
        validarTexto(codigo, "El código");
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        validarTexto(nombre, "El nombre");
        this.nombre = nombre.trim();
    }

    public void setDireccion(String direccion) {
        validarTexto(direccion, "La dirección");
        this.direccion = direccion.trim();
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new IllegalArgumentException(
                    "El nivel de riesgo debe estar entre 1 y 10."
            );
        }

        this.nivelRiesgo = nivelRiesgo;
    }

    public void setEstado(String estado) {
        validarTexto(estado, "El estado");
        this.estado = estado.trim();
    }

    @Override
    public String toString() {
        return "Código: " + codigo
                + " | Nombre: " + nombre
                + " | Dirección: " + direccion
                + " | Riesgo: " + nivelRiesgo
                + " | Estado: " + estado;
    }
}