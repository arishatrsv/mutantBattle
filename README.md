# Mutant Battle

## Descripción

Mutant Battle es un juego automático en el que dos equipos de mutantes se enfrentan en un campo de batalla.

El usuario únicamente proporciona el tamaño de los equipos. A partir de este dato, el sistema genera los mutantes de forma aleatoria y ejecuta la batalla automáticamente hasta que uno de los equipos quede sin mutantes vivos.

El proyecto será desarrollado en Java y estará organizado en cuatro capas: **Model, Game, Control y UI**.

---

# Estructura Caso#1

## Capa Modelo

Representa la información general

### Persona

- `nombre: String`
- `edad: int`

**Metodos**

- `Persona(nombre: String, edad: int)`
- `getNombre(): String`
- `setNombre(nombre: String): void`
- `getEdad(): int`
- `setEdad(edad: int): void`

---

### Mutante

Hereda de `Persona` y representa a un personaje de la batalla.

- `energia: double`
- `defensa: int`
- `posicionX: double`
- `posicionY: double`
- `poder: IPower`

**Metodos**

- `Mutante(nombre: String, edad: int, energia: double, defensa: int, posicionX: double, posicionY: double, poder: IPower)`
- `getEnergia(): double`
- `setEnergia(energia: double): void`
- `getDefensa(): int`
- `setDefensa(defensa: int): void`
- `getPosicionX(): double`
- `setPosicionX(posicionX: double): void`
- `getPosicionY(): double`
- `setPosicionY(posicionY: double): void`
- `getPoder(): IPower`
- `setPoder(poder: IPower): void`
- `estaVivo(): boolean`
- `recibirDanio(danio: double): void`
- `mover(ancho: int, alto: int): void`

---

### IPower

 Comportamiento de los poderes mutantes.

**Metodos**

- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

Cada Poder usará IPower 

### PoderHielo

- `danio: int`

**Metodos**

- `PoderHielo(danio: int)`
- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

### PoderRayos

- `danio: int`

**Metodos**

- `PoderRayos(danio: int)`
- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

### PoderTelepatia

- `danio: int`

**Metodos**

- `PoderTelepatia(danio: int)`
- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

### PoderFuego

- `danio: int`

**Metodos**

- `PoderFuego(danio: int)`
- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

### PoderRegeneracion

- `danio: int`

**Metodos**

- `PoderRegeneracion(danio: int)`
- `dispararPoder(): void`
- `getDanio(): int`
- `aumentarDanio(): void`

---

# Capa Juego

Hace los equipos y el Campo de Batalla

### Equipo

- `color: String`
- `simbolo: String`
- `mutantes: List<Mutante>`

**Metodos**

- `Equipo(color: String, simbolo: String)`
- `agregarMutante(mutante: Mutante): void`
- `getMutantes(): List<Mutante>`
- `contarVivos(): int`
- `contarMuertos(): int`
- `estaEliminado(): boolean`

---

### Marcador

Para saber cuantos vivos y muertos hay

- `vivosEquipo1: int`
- `muertosEquipo1: int`
- `vivosEquipo2: int`
- `muertosEquipo2: int`

**Metodos**

- `actualizar(equipo1: Equipo, equipo2: Equipo): void`
- `getVivosEquipo1(): int`
- `getMuertosEquipo1(): int`
- `getVivosEquipo2(): int`
- `getMuertosEquipo2(): int`

---

### CampoBatalla

Hace el campo de batalla y mantiene los equipos y el marcador.

- `equipo1: Equipo`
- `equipo2: Equipo`
- `marcador: Marcador`

**Metodos**

- `crearEquipos(cantidad: int): void`
- `getEquipo1(): Equipo`
- `getEquipo2(): Equipo`
- `getMarcador(): Marcador`
- `batallaTerminada(): boolean`
- `obtenerGanador(): Equipo`

---

# Capa Control

Aquí controla el movimiento, los encuentros y el combate.

### ControladorBatalla

Controla una partida.

- `campoBatalla: CampoBatalla`
- `administradorCombate: AdministradorCombate`
- `hiloMutante: List<HiloMutante>`

**Metodos**

- `ControladorBatalla(campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate)`
- `iniciarJuego(cantidad: int): void`
- `generarEquipos(cantidad: int): void`
- `iniciarMovimiento(): void`
- `controlarBatalla(): void`
- `finalizarJuego(): void`
- `nuevaBatalla(): void`

---

### HiloMutante

Para detectar enemigos

Usaremos un HiloMutante para cada mutante.

- `mutante: Mutante`
- `campoBatalla: CampoBatalla`
- `administradorCombate: AdministradorCombate`

**Metodos**

- `HiloMutante(mutante: Mutante, campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate)`
- `correr(): void`
- `detener(): void`
- `detectarEnemigos(): List<Mutante>`

---

### AdministradorCombate

Administra los encuentros entre mutantes enemigos.

- `radioEncuentro: double`

**Metodos**

- `AdministradorCombate(radioEncuentro: double)`
- `detectarEncuentro(mutante1: Mutante, mutante2: Mutante): boolean`
- `ejecutarEncuentro(mutante1: Mutante, mutante2: Mutante): void`
- `decidirDefensa(): boolean`
- `calcularDanio(atacante: Mutante, defensor: Mutante, defiende: boolean): double`
- `aplicarDanio(defensor: Mutante, danio: double): void`
- `aumentarDanioPoder(atacante: Mutante): void`

---

# Interfaz

### Observer

**Metodos**

- `actualizar(): void`

---

### VerCampoBatalla

Representa visualmente el campo de batalla.

- `campoBatalla: CampoBatalla`

**Metodos**

- `VerCampoBatalla(campoBatalla: CampoBatalla)`
- `mostrarCampo(): void`
- `dibujarMutantes(): void`
- `mostrarMarcador(): void`
- `mostrarGanador(): void`
- `actualizar(): void`

usa `Observer`.

---

### BatallaUI

Controla la interacción del usuario con la interfaz.

- `controladorBatalla: ControladorBatalla`
- `verCampoBatalla: VerCampoBatalla`

**Metodos**

- `BatallaUI(controladorBatalla: ControladorBatalla, verCampoBatalla: VerCampoBatalla)`
- `recibirCantidadEquipo(): int`
- `iniciarBatalla(): void`
- `nuevaBatalla(): void`

---

# Constantes

Valores configurables del juego.

- `INICIAL_ENERGIA: int`
- `MIN_TAMANO_EQUIPO: int`
- `MAX_TAMANO_EQUIPO: int`
- `MIN_DEFENSA: int`
- `MAX_DEFENSA: int`
- `MIN_PODER_DANIO: int`
- `MAX_PODER_DANIO: int`
- `DANIO_AUMENTA: int`
- `MISMA_VELOCIDAD: double`
- `ENCUENTRO_RADIO: double`
- `ALTO_CAMPOBATALLA: int`
- `ANCHO_CAMPOBATALLA: int`

---
#UML

@startuml

skinparam classAttributeIconSize 0

title Mutant Battle - UML

package "Model" {

    class Persona {
        - nombre: String
        - edad: int

        + Persona(nombre: String, edad: int)
        + getNombre(): String
        + setNombre(nombre: String): void
        + getEdad(): int
        + setEdad(edad: int): void
    }

    class Mutante {
        - energia: double
        - defensa: int
        - posicionX: double
        - posicionY: double
        - poder: IPower

        + Mutante(nombre: String, edad: int, energia: double, defensa: int, posicionX: double, posicionY: double, poder: IPower)
        + getEnergia(): double
        + setEnergia(energia: double): void
        + getDefensa(): int
        + setDefensa(defensa: int): void
        + getPosicionX(): double
        + setPosicionX(posicionX: double): void
        + getPosicionY(): double
        + setPosicionY(posicionY: double): void
        + getPoder(): IPower
        + setPoder(poder: IPower): void
        + estaVivo(): boolean
        + recibirDanio(danio: double): void
        + mover(ancho: int, alto: int): void
    }

    interface IPower {
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderHielo {
        - danio: int

        + PoderHielo(danio: int)
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderRayos {
        - danio: int

        + PoderRayos(danio: int)
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderTelepatia {
        - danio: int

        + PoderTelepatia(danio: int)
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderFuego {
        - danio: int

        + PoderFuego(danio: int)
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    class PoderRegeneracion {
        - danio: int

        + PoderRegeneracion(danio: int)
        + dispararPoder(): void
        + getDanio(): int
        + aumentarDanio(): void
    }

    Persona <|-- Mutante

    IPower <|.. PoderHielo
    IPower <|.. PoderRayos
    IPower <|.. PoderTelepatia
    IPower <|.. PoderFuego
    IPower <|.. PoderRegeneracion

    Mutante --> IPower
}

package "Game" {

    class Equipo {
        - color: String
        - simbolo: String
        - mutantes: List<Mutante>

        + Equipo(color: String, simbolo: String)
        + agregarMutante(mutante: Mutante): void
        + getMutantes(): List<Mutante>
        + contarVivos(): int
        + contarMuertos(): int
        + estaEliminado(): boolean
    }

    class Marcador {
        - vivosEquipo1: int
        - muertosEquipo1: int
        - vivosEquipo2: int
        - muertosEquipo2: int

        + actualizar(equipo1: Equipo, equipo2: Equipo): void
        + getVivosEquipo1(): int
        + getMuertosEquipo1(): int
        + getVivosEquipo2(): int
        + getMuertosEquipo2(): int
    }

    class CampoBatalla {
        - equipo1: Equipo
        - equipo2: Equipo
        - marcador: Marcador

        + crearEquipos(cantidad: int): void
        + getEquipo1(): Equipo
        + getEquipo2(): Equipo
        + getMarcador(): Marcador
        + batallaTerminada(): boolean
        + obtenerGanador(): Equipo
    }

    Equipo "1" *-- "0..*" Mutante

    CampoBatalla "1" *-- "1" Equipo : equipo1
    CampoBatalla "1" *-- "1" Equipo : equipo2
    CampoBatalla "1" *-- "1" Marcador

    Marcador --> Equipo
}

package "Control" {

    class ControladorBatalla {
        - campoBatalla: CampoBatalla
        - administradorCombate: AdministradorCombate
        - hiloMutante: List<HiloMutante>

        + ControladorBatalla(campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate)
        + iniciarJuego(cantidad: int): void
        + generarEquipos(cantidad: int): void
        + iniciarMovimiento(): void
        + controlarBatalla(): void
        + finalizarJuego(): void
        + nuevaBatalla(): void
    }

    class HiloMutante {
        - mutante: Mutante
        - campoBatalla: CampoBatalla
        - administradorCombate: AdministradorCombate

        + HiloMutante(mutante: Mutante, campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate)
        + correr(): void
        + detener(): void
        + detectarEnemigos(): List<Mutante>
    }

    class AdministradorCombate {
        - radioEncuentro: double

        + AdministradorCombate(radioEncuentro: double)
        + detectarEncuentro(mutante1: Mutante, mutante2: Mutante): boolean
        + ejecutarEncuentro(mutante1: Mutante, mutante2: Mutante): void
        + decidirDefensa(): boolean
        + calcularDanio(atacante: Mutante, defensor: Mutante, defiende: boolean): double
        + aplicarDanio(defensor: Mutante, danio: double): void
        + aumentarDanioPoder(atacante: Mutante): void
    }

    ControladorBatalla --> CampoBatalla
    ControladorBatalla --> AdministradorCombate
    ControladorBatalla "1" o-- "0..*" HiloMutante

    HiloMutante --> Mutante
    HiloMutante --> CampoBatalla
    HiloMutante --> AdministradorCombate

    AdministradorCombate --> Mutante
}

package "UI" {

    interface Observer {
        + actualizar(): void
    }

    class VerCampoBatalla {
        - campoBatalla: CampoBatalla

        + VerCampoBatalla(campoBatalla: CampoBatalla)
        + mostrarCampo(): void
        + dibujarMutantes(): void
        + mostrarMarcador(): void
        + mostrarGanador(): void
        + actualizar(): void
    }

    class BatallaUI {
        - controladorBatalla: ControladorBatalla
        - verCampoBatalla: VerCampoBatalla

        + BatallaUI(controladorBatalla: ControladorBatalla, verCampoBatalla: VerCampoBatalla)
        + recibirCantidadEquipo(): int
        + iniciarBatalla(): void
        + nuevaBatalla(): void
    }

    Observer <|.. VerCampoBatalla

    VerCampoBatalla --> CampoBatalla
    BatallaUI --> ControladorBatalla
    BatallaUI --> VerCampoBatalla
}

class Constantes <<utility>> {
    {static} + INICIAL_ENERGIA: int
    {static} + MIN_TAMANO_EQUIPO: int
    {static} + MAX_TAMANO_EQUIPO: int
    {static} + MIN_DEFENSA: int
    {static} + MAX_DEFENSA: int
    {static} + MIN_PODER_DANIO: int
    {static} + MAX_PODER_DANIO: int
    {static} + DANIO_AUMENTA: int
    {static} + MISMA_VELOCIDAD: double
    {static} + ENCUENTRO_RADIO: double
    {static} + ALTO_CAMPOBATALLA: int
    {static} + ANCHO_CAMPOBATALLA: int
}

@enduml
