public class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(
            String codigo,
            String descripcion,
            String tipoEvidencia,
            int nivelImportancia,
            int nivelConfiabilidad
    ) {
        setCodigo(codigo);
        setDescripcion(descripcion);
        setTipoEvidencia(tipoEvidencia);
        setNivelImportancia(nivelImportancia);
        setNivelConfiabilidad(nivelConfiabilidad);
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void setCodigo(String codigo) {
        validarTexto(codigo, "El código");
        this.codigo = codigo.trim();
    }

    public void setDescripcion(String descripcion) {
        validarTexto(descripcion, "La descripción");
        this.descripcion = descripcion.trim();
    }

    public void setTipoEvidencia(String tipoEvidencia) {
        validarTexto(tipoEvidencia, "El tipo de evidencia");
        this.tipoEvidencia = tipoEvidencia.trim();
    }

    public void setNivelImportancia(int nivelImportancia) {
        if (nivelImportancia < 1 || nivelImportancia > 10) {
            throw new IllegalArgumentException(
                    "El nivel de importancia debe estar entre 1 y 10."
            );
        }

        this.nivelImportancia = nivelImportancia;
    }

    public void setNivelConfiabilidad(int nivelConfiabilidad) {
        if (nivelConfiabilidad < 0 || nivelConfiabilidad > 100) {
            throw new IllegalArgumentException(
                    "El nivel de confiabilidad debe estar entre 0 y 100."
            );
        }

        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo
                + " | Descripción: " + descripcion
                + " | Tipo: " + tipoEvidencia
                + " | Importancia: " + nivelImportancia
                + " | Confiabilidad: " + nivelConfiabilidad + "%";
    }
}
