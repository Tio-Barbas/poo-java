// =====================================================
// Práctica 5 - Sistema de Personal
// Herencia, sobrescritura de métodos, final y clases selladas
// =====================================================
// ---------- CLASE PERSONA ----------
class Persona {
protected String nombre;
protected int edad;
public Persona(String nombre, int edad) {
this.nombre = nombre;
this.edad = edad;
}
public void mostrarDatos() {
System.out.println("Nombre: " + nombre);
System.out.println("Edad: " + edad + " años");
}
}
// ---------- CLASE EMPLEADO ----------
class Empleado extends Persona {
protected double sueldo;
public Empleado(String nombre, int edad, double sueldo) {
super(nombre, edad);
this.sueldo = sueldo;
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Sueldo: S/ " + sueldo);
}
// Método final: no puede ser sobrescrito por las subclases
public final void mostrarIdentificacion() {
System.out.println("Empleado registrado en el sistema.");
}
}
// ---------- CLASE PROGRAMADOR ----------
class Programador extends Empleado {
protected String lenguaje;
public Programador(String nombre, int edad, double sueldo, String lenguaje) {
super(nombre, edad, sueldo);
this.lenguaje = lenguaje;
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Lenguaje: " + lenguaje);
}
}
// ---------- CLASE GERENTE ----------
class Gerente extends Empleado {
private String area;
public Gerente(String nombre, int edad, double sueldo, String area) {
super(nombre, edad, sueldo);
this.area = area;
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Área: " + area);
}
}
// ---------- CLASE PROGRAMADOR SENIOR (final) ----------
final class ProgramadorSenior extends Programador {
private int aniosExperiencia;
public ProgramadorSenior(String nombre, int edad, double sueldo,
String lenguaje, int aniosExperiencia) {
super(nombre, edad, sueldo, lenguaje);
this.aniosExperiencia = aniosExperiencia;
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Años de experiencia: " + aniosExperiencia);
}
}
// ---------- CLASE SELLADA USUARIO ----------
sealed class Usuario permits Administrador, Cliente {
protected String nombreUsuario;
public Usuario(String nombreUsuario) {
this.nombreUsuario = nombreUsuario;
public void mostrarDatos() {
System.out.println("Usuario: " + nombreUsuario);
}
}
}
final class Administrador extends Usuario {
public Administrador(String nombreUsuario) {
super(nombreUsuario);
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Rol: Administrador del sistema");
}
}
final class Cliente extends Usuario {
public Cliente(String nombreUsuario) {
super(nombreUsuario);
}
@Override
public void mostrarDatos() {
super.mostrarDatos();
System.out.println("Rol: Cliente");
}
}
// ---------- PROGRAMA PRINCIPAL ----------
public class Main {
public static void main(String[] args) {
Programador programador = new Programador("Luis Quispe", 28, 4500.0, "Java");
Gerente gerente = new Gerente("Ana Mamani", 45, 9000.0, "Recursos Humanos");
ProgramadorSenior programadorSenior =
new ProgramadorSenior("Carlos Flores", 38, 7500.0, "Python", 12);
Administrador administrador = new Administrador("admin01");
Cliente cliente = new Cliente("cliente_juliaca");
System.out.println("=== PROGRAMADOR ===");
programador.mostrarDatos();
programador.mostrarIdentificacion();
System.out.println("\n=== GERENTE ===");
gerente.mostrarDatos();
gerente.mostrarIdentificacion();
System.out.println("\n=== PROGRAMADOR SENIOR ===");
programadorSenior.mostrarDatos();
programadorSenior.mostrarIdentificacion();
System.out.println("\n=== ADMINISTRADOR ===");
administrador.mostrarDatos();
System.out.println("\n=== CLIENTE ===");
cliente.mostrarDatos();
}
}
// =====================================================
// COMPROBACIÓN (casos de prueba comentados: si se descomentan,
// el compilador genera error)
// =====================================================
// CASO 1: Intentar sobrescribir el método final mostrarIdentificacion()
// Error: mostrarIdentificacion() in Tester cannot override
mostrarIdentificacion() in Empleado; overridden method is final
// /*
class Tester extends Empleado {
public Tester(String nombre, int edad, double sueldo) {
super(nombre, edad, sueldo);
}
@Override
public void mostrarIdentificacion() {
System.out.println("Intento de sobrescritura");
}
}
*/
// CASO 2: Intentar crear una clase que herede de ProgramadorSenior
// Error: cannot inherit from final ProgramadorSenior
/*
class ProgramadorLider extends ProgramadorSenior {
public ProgramadorLider(String nombre, int edad, double sueldo,
String lenguaje, int aniosExperiencia) {
super(nombre, edad, sueldo, lenguaje, aniosExperiencia);
}
}
*/
// CASO 3: Intentar crear la clase Invitado que herede de Usuario
// sin estar incluida en permits
// Error: class is not allowed to extend sealed class: Usuario
/*
final class Invitado extends Usuario {
public Invitado(String nombreUsuario) {
super(nombreUsuario);
}
}
*/
