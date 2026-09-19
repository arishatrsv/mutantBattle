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
- `edad: byte`

**Metodos**

- `Persona(nombre: String, edad: byte)`
- `getNombre(): String`
- `setNombre(nombre: String): void`
- `getEdad(): byte`
- `setEdad(edad: byte): void`

---

### Mutante

Hereda de `Persona` y representa a un personaje de la batalla.

- `energia: double`
- `defensa: int`
- `posicionX: int`
- `posicionY: int`
- `poder: IPower`

**Metodos**

- `Mutante(nombre: String, edad: byte, energia: double, defensa: int, posicionX: int, posicionY: int, poder: IPower)`
- `getEnergia(): double`
- `setEnergia(energia: double): void`
- `getDefensa(): int`
- `setDefensa(defensa: int): void`
- `getPosicionX(): int`
- `setPosicionX(posicionX: int): void`
- `getPosicionY(): int`
- `setPosicionY(posicionY: int): void`
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
- `simbolo: Image`
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

- `estadisticas: int[]`

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

Utiliza las clases que nos da java

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

### IConstantes

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
- `VIVOS_EQUIPO1: int`
- `MUERTOS_EQUIPO1: int`
- `VIVOS_EQUIPO2: int`
- `MUERTOS_EQUIPO2: int`

---
# UML

<img width="2095" height="2532" alt="image" src="https://github.com/user-attachments/assets/23755df4-8d72-45fa-a1c6-f59dc5d62470" />


```
@startuml

title Mutant Battle - UML Caso #1

skinparam classAttributeIconSize 0

'========================================
' CAPA MODEL
'========================================

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
        - posicionX: int
        - posicionY: int
        - poder: IPower

        + Mutante(nombre: String, edad: int, energia: double, defensa: int, posicionX: int, posicionY: int, poder: IPower)
        + getEnergia(): double
        + setEnergia(energia: double): void
        + getDefensa(): int
        + setDefensa(defensa: int): void
        + getPosicionX(): int
        + setPosicionX(posicionX: int): void
        + getPosicionY(): int
        + setPosicionY(posicionY: int): void
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
}

'========================================
' CAPA GAME
'========================================

package "Game" {

    class Equipo {
        - color: String
        - simbolo: Image
        - mutantes: List<Mutante>

        + Equipo(color: String, simbolo: Image)
        + agregarMutante(mutante: Mutante): void
        + getMutantes(): List<Mutante>
        + contarVivos(): int
        + contarMuertos(): int
        + estaEliminado(): boolean
    }

    class Marcador {
        - estadisticas: int[]

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
}

'========================================
' CAPA CONTROL
'========================================

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
}

'========================================
' CAPA UI
'========================================

package "UI" {

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
}

'========================================
' CONSTANTES
'========================================

package "Constantes" {

    interface IConstants {
        INICIAL_ENERGIA: int
        MIN_TAMANO_EQUIPO: int
        MAX_TAMANO_EQUIPO: int
        MIN_DEFENSA: int
        MAX_DEFENSA: int
        MIN_PODER_DANIO: int
        MAX_PODER_DANIO: int
        DANIO_AUMENTA: int
        MISMA_VELOCIDAD: double
        ENCUENTRO_RADIO: double
        ALTO_CAMPOBATALLA: int
        ANCHO_CAMPOBATALLA: int
        VIVOS_EQUIPO1: int
        MUERTOS_EQUIPO1: int
        VIVOS_EQUIPO2: int
        MUERTOS_EQUIPO2: int
    }
}

'========================================
' HERENCIA
'========================================

Persona <|-- Mutante

'========================================
' POLIMORFISMO
'========================================

IPower <|.. PoderHielo
IPower <|.. PoderRayos
IPower <|.. PoderTelepatia
IPower <|.. PoderFuego
IPower <|.. PoderRegeneracion

Mutante --> IPower : utiliza

'========================================
' RELACIONES GAME
'========================================

Equipo "1" o-- "0..*" Mutante : contiene

CampoBatalla "1" *-- "1" Equipo : equipo1
CampoBatalla "1" *-- "1" Equipo : equipo2
CampoBatalla "1" *-- "1" Marcador : marcador

Marcador --> Equipo : actualiza

'========================================
' RELACIONES CONTROL
'========================================

ControladorBatalla --> CampoBatalla
ControladorBatalla --> AdministradorCombate
ControladorBatalla o-- HiloMutante

HiloMutante --> Mutante
HiloMutante --> CampoBatalla
HiloMutante --> AdministradorCombate

AdministradorCombate --> Mutante : combate

'========================================
' RELACIONES UI
'========================================

VerCampoBatalla --> CampoBatalla
BatallaUI --> ControladorBatalla
BatallaUI --> VerCampoBatalla

'========================================
' CONSTANTES
'========================================

IConstants ..> Marcador : índices
IConstants ..> CampoBatalla : dimensiones
IConstants ..> Equipo : límites
IConstants ..> Mutante : valores
IConstants ..> IPower : daño

@enduml

```


