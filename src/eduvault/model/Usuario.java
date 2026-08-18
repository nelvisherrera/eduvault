package eduvault.model;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
    private String estado;
    private String carrera;
    private int semestre;
    
    public Usuario() {}
    
    public Usuario(int id, String nombre, String correo, String contrasena, String rol, String estado, String carrera, int semestre) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = estado;
        this.carrera = carrera;
        this.semestre = semestre;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
}