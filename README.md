# Mutant Battle

## Descripción

Mutant Battle es un juego automático en el que dos equipos de mutantes se enfrentan en un campo de batalla.

El usuario únicamente proporciona el tamaño de los equipos. A partir de este dato, el sistema genera los mutantes de forma aleatoria y ejecuta la batalla automáticamente hasta que uno de los equipos quede sin mutantes vivos.

El proyecto será desarrollado en Java y estará organizado en cuatro capas: **Model, Game, Control y UI**.

---

# Especificación de objetos

## Model Layer

Esta capa representa los objetos principales del sistema y sus características.

### Persona

Clase base que representa la información general de una persona.

**Responsabilidades:**

- Almacenar información básica de una persona.
- Permitir consultar y modificar sus datos.
- Servir como clase base para los mutantes.

### Mutante

Representa a un participante de la batalla.

**Características:**

- Nombre.
- Edad.
- Energía.
- Capacidad de defensa.
- Posición dentro del campo.
- Velocidad de movimiento.
- Un poder mutante.

**Responsabilidades:**

- Mantener su estado durante la batalla.
- Moverse dentro del campo de batalla.
- Atacar a otros mutantes.
- Defenderse de los ataques.
- Recibir daño.
- Mantener actualizada su energía.
- Determinar si continúa con vida.

### IPower

Interfaz que define el comportamiento general de los poderes mutantes.

**Responsabilidades:**

- Definir las operaciones que debe tener un poder.
- Mantener su capacidad de daño.
- Permitir diferentes tipos de poderes mediante polimorfismo.

### PoderHielo

Representa un poder basado en hielo e implementa `IPower`.

### PoderRayos

Representa un poder basado en rayos e implementa `IPower`.

### PoderTelepatia

Representa un poder basado en telepatía e implementa `IPower`.

### PoderFuego

Representa un poder basado en el fuego e implementa `IPower`.

### PoderRegeneracion

Representa un poder basado en regeneración e implementa `IPower`.

---

# Game Layer

Esta capa representa el espacio de juego y administra los equipos que participan en la batalla.

### Team

Representa uno de los dos equipos de mutantes.

**Características:**

- Color.
- Escudo o símbolo.
- Mutantes pertenecientes al equipo.

**Responsabilidades:**

- Mantener los mutantes del equipo.
- Identificar al equipo mediante su color y símbolo.
- Contabilizar los mutantes vivos.
- Contabilizar los mutantes muertos.

### Battlefield

Representa el campo de batalla y su estado actual.

**Características:**

- Dimensiones del campo.
- Dos equipos.
- Marcador de la batalla.

**Responsabilidades:**

- Crear y mantener los dos equipos.
- Mantener la misma cantidad de mutantes en ambos equipos.
- Proporcionar las dimensiones del campo a los mutantes.
- Mantener el control del estado de la batalla.
- Llevar el conteo de mutantes vivos y muertos.
- Mantener el marcador.
- Determinar cuándo termina la batalla.
- Determinar el equipo ganador.

El tamaño de los equipos estará entre **3 y 11 mutantes**.

---

# Control Layer

Esta capa contiene la lógica necesaria para ejecutar la batalla.

### BattleController

Controla el desarrollo general de una partida.

**Responsabilidades:**

- Iniciar una nueva partida.
- Generar los equipos y sus mutantes.
- Iniciar el movimiento de los mutantes.
- Coordinar el desarrollo de la batalla.
- Detectar cuándo termina la partida.
- Permitir iniciar una nueva batalla.

### MutantThread

Controla las acciones de un mutante durante la batalla mediante concurrencia.

**Responsabilidades:**

- Ejecutar el movimiento del mutante.
- Detectar enemigos dentro del radio configurado.
- Coordinar los encuentros con otros mutantes.
- Permitir que diferentes encuentros ocurran de forma paralela.

### CombatManager

Administra los encuentros entre mutantes enemigos.

**Responsabilidades:**

- Detectar encuentros entre mutantes.
- Coordinar las decisiones de ataque y defensa.
- Calcular el daño producido.
- Actualizar la energía de los mutantes.
- Aumentar la capacidad de daño del poder cuando corresponda.
- Determinar cuándo un mutante queda fuera de combate.

---

# UI Layer

Esta capa se encarga de representar visualmente el estado de la batalla. No contiene la lógica del juego.

### BattlefieldView

Representa la información de la batalla en la interfaz gráfica.

**Responsabilidades:**

- Mostrar el campo de batalla.
- Mostrar todos los mutantes y sus posiciones.
- Identificar el equipo de cada mutante.
- Mostrar la energía de cada mutante.
- Mostrar la cantidad de mutantes vivos y muertos por equipo.
- Mostrar el marcador.
- Mostrar el equipo ganador.
- Permitir iniciar una nueva partida.

La vista utilizará el patrón **Observer** para recibir las actualizaciones del estado de la batalla.

### BattleControllerUI

Controla la interacción del usuario con la interfaz.

**Responsabilidades:**

- Recibir el tamaño de los equipos.
- Iniciar la batalla.
- Permitir iniciar una nueva partida después de finalizar un combate.

---

# Reglas principales

- Los equipos tendrán entre **3 y 11 mutantes**.
- Ambos equipos comenzarán con la misma cantidad de mutantes.
- Cada mutante comenzará con **100 puntos de energía**.
- Cada mutante tendrá una capacidad de defensa entre **1 y 3**.
- Cada mutante podrá tener como máximo **un poder**.
- Cada poder comenzará con una capacidad de daño entre **1 y 3**.
- La capacidad de daño de un poder podrá aumentar hasta un máximo de **7**.
- Los mutantes se moverán continuamente dentro del campo de batalla.
- El movimiento tendrá una velocidad determinada y seguirá un comportamiento aleatorio.
- Cuando dos mutantes enemigos estén dentro del radio configurado, ambos decidirán entre atacar o defender.
- Si el oponente no se defiende, recibirá el daño completo del poder.
- Si el oponente se defiende, el daño se dividirá entre su capacidad de defensa.
- Cuando un ataque reduzca la energía del oponente, el poder del atacante aumentará en **1 unidad**, hasta un máximo de **7**.
- Los encuentros entre diferentes pares de mutantes podrán ejecutarse en paralelo mediante concurrencia.
- La batalla terminará cuando uno de los equipos no tenga mutantes vivos.

---

# Arquitectura del proyecto

| Capa | Responsabilidad |
|---|---|
| **Model** | Representar los objetos, mutantes y poderes. |
| **Game** | Administrar los equipos y el campo de batalla. |
| **Control** | Ejecutar el movimiento y controlar los encuentros y combates. |
| **UI** | Representar visualmente el estado de la batalla. |

El diseño aplicará los principios de:

- **Encapsulamiento**
- **Herencia**
- **Polimorfismo**
- **Separación de responsabilidades**

La interfaz gráfica utilizará los patrones:

- **MVC (Model-View-Controller)**
- **Observer**

Los valores configurables del juego se manejarán mediante una clase de constantes para evitar valores definidos directamente dentro de las clases.

# UML

```plantuml
@startuml

title Mutant Battle - UML

' ========================= MODEL =========================

package "Model" {

    class Persona {
        - edad: byte
        # nombre: String
        - deudasAPagar: double
        - power: IPower

        + Persona()
        + Persona(edad: byte, nombre: String)
        + Persona(nombre: String, edad: byte)
        + Persona(nombre: String)

        + getEdad(): byte
        + setEdad(edad: byte): void
        + getNombre(): String
        + setNombre(nombre: String): void
        + reducirDeudaConIngreso(ingreso: double): void
        + cantar(): void
        + setPower(power: IPower): void
        + atacar(): void
    }

    class Mutante {
        - energia: double
        - defensa: int
        - velocidad: double
        - posicionX: double
        - posicionY: double

        + Mutante(...)
        + getEnergia(): double
        + setEnergia(energia: double): void
        + getDefensa(): int
        + setDefensa(defensa: int): void
        + getVelocidad(): double
        + setVelocidad(velocidad: double): void
        + getPosicionX(): double
        + getPosicionY(): double
        + recibirDanio(danio: double): void
        + estaVivo(): boolean
        + mover(ancho: int, alto: int): void
        + atacar(oponente: Mutante): void
        + defender(): void
    }

    interface IPower {
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderHielo
    class PoderRayos
    class PoderTelepatia
    class PoderFuego
    class PoderRegeneracion
}

' ================= INHERITANCE AND POLYMORPHISM =================

Mutante --|> Persona

PoderHielo ..|> IPower
PoderRayos ..|> IPower
PoderTelepatia ..|> IPower
PoderFuego ..|> IPower
PoderRegeneracion ..|> IPower

Mutante --> IPower : posee


' ========================= GAME =========================

package "Game" {

    class Team {
        - color: String
        - simbolo: String
        - mutantes: List<Mutante>

        + Team(color: String, simbolo: String)
        + agregarMutante(mutante: Mutante): void
        + obtenerMutantes(): List<Mutante>
        + contarVivos(): int
        + contarMuertos(): int
        + estaEliminado(): boolean
    }

    class Battlefield {
        - ancho: int
        - alto: int
        - equipo1: Team
        - equipo2: Team
        - marcador: Scoreboard

        + Battlefield(ancho: int, alto: int)
        + crearEquipos(cantidad: int): void
        + getAncho(): int
        + getAlto(): int
        + getEquipo1(): Team
        + getEquipo2(): Team
        + getMarcador(): Scoreboard
        + batallaTerminada(): boolean
        + obtenerGanador(): Team
    }

    class Scoreboard {
        - vivosEquipo1: int
        - muertosEquipo1: int
        - vivosEquipo2: int
        - muertosEquipo2: int

        + actualizar(equipo1: Team, equipo2: Team): void
        + getVivosEquipo1(): int
        + getMuertosEquipo1(): int
        + getVivosEquipo2(): int
        + getMuertosEquipo2(): int
    }
}

Team "1" o-- "*" Mutante : contiene
Battlefield "1" o-- "2" Team : tiene
Battlefield --> Scoreboard : utiliza


' ======================== CONTROL ========================

package "Control" {

    class BattleController {
        - battlefield: Battlefield
        - combatManager: CombatManager
        - hilos: List<MutantThread>

        + iniciarJuego(cantidad: int): void
        + generarEquipos(cantidad: int): void
        + iniciarMovimiento(): void
        + controlarBatalla(): void
        + finalizarJuego(): void
        + nuevaBatalla(): void
    }

    class MutantThread {
        - mutante: Mutante
        - battlefield: Battlefield
        - combatManager: CombatManager

        + run(): void
        + detectarEnemigos(): void
    }

    class CombatManager {
        - radioEncuentro: double

        + detectarEncuentro(mutante1: Mutante, mutante2: Mutante): boolean
        + ejecutarEncuentro(mutante1: Mutante, mutante2: Mutante): void
        + decidirAtaqueDefensa(mutante: Mutante): boolean
        + calcularDanio(atacante: Mutante, defensor: Mutante): double
        + aplicarDanio(defensor: Mutante, danio: double): void
        + aumentarDanioPoder(atacante: Mutante): void
    }
}

BattleController --> Battlefield
BattleController --> CombatManager
BattleController --> MutantThread

MutantThread --> Mutante
MutantThread --> Battlefield
MutantThread --> CombatManager

CombatManager --> Mutante


' =========================== UI ===========================

package "UI" {

    interface Observer {
        + actualizar(): void
    }

    class BattlefieldView {
        - battlefield: Battlefield

        + mostrarCampo(): void
        + dibujarMutantes(): void
        + mostrarMarcador(): void
        + mostrarGanador(): void
        + actualizar(): void
    }

    class BattleControllerUI {
        - battleController: BattleController
        - battlefieldView: BattlefieldView

        + recibirCantidadEquipo(): int
        + iniciarBatalla(): void
        + nuevaBatalla(): void
    }

    class BattleWindow {
        - view: BattlefieldView
        - controllerUI: BattleControllerUI

        + iniciar(): void
    }
}

BattlefieldView ..|> Observer
BattlefieldView --> Battlefield
BattleControllerUI --> BattleController
BattleControllerUI --> BattlefieldView
BattleWindow --> BattlefieldView
BattleWindow --> BattleControllerUI


' ======================== CONSTANTS ========================

package "Constants" {

    class Constants {
        {static} + INITIAL_ENERGY: double
        {static} + MIN_DEFENSE: int
        {static} + MAX_DEFENSE: int
        {static} + MIN_DAMAGE: int
        {static} + INITIAL_MAX_DAMAGE: int
        {static} + MAX_DAMAGE: int
        {static} + MIN_TEAM_SIZE: int
        {static} + MAX_TEAM_SIZE: int
        {static} + ENCOUNTER_RADIUS: double
        {static} + BATTLEFIELD_WIDTH: int
        {static} + BATTLEFIELD_HEIGHT: int
        {static} + UI_REFRESH_RATE: int
    }
}

@enduml
```


