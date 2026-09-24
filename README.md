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

- `energia: int`
- `defensa: int`
- `posicionX: int`
- `posicionY: int`
- `direccionX: int`
- `direccionY: int`
- `poder: IPower`

**Metodos**

- `Mutante(nombre: String, edad: byte, energia: int, defensa: int, posicionX: int, posicionY: int, poder: IPower)`
- `getEnergia(): int`
- `setEnergia(energia: int): void`
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

- `Equipo(color: String, simbolo: Image)`
- `getColor(): String`
- `getSimbolo(): Image`
- `setSimbolo(simbolo: Image): void`
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

- `Marcador()`
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

- `CampoBatalla()`
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
- `getCampoBatalla(): CampoBatalla`
- `iniciarJuego(cantidad: int): void`
- `generarEquipos(cantidad: int): void`
- `iniciarMovimiento(ancho: int, alto: int): void`
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
- `activo: boolean`
- `ancho: int`
- `alto: int`

**Metodos**

- `HiloMutante(mutante: Mutante, campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate, ancho: int, alto: int)`
- `correr(): void`
- `detener(): void`
- `detectarEnemigos(): List<Mutante>`
- `run(): void`

---

### AdministradorCombate

Administra los encuentros entre mutantes enemigos.

- `radioEncuentro: double`

**Metodos**

- `AdministradorCombate()`
- `detectarEncuentro(mutante1: Mutante, mutante2: Mutante): boolean`
- `ejecutarEncuentro(mutante1: Mutante, mutante2: Mutante): void`
- `decidirDefensa(): boolean`
- `calcularDanio(atacante: Mutante, defensor: Mutante, defiende: boolean): double`
- `aplicarDanio(defensor: Mutante, danio: double): void`
- `aumentarDanioPoder(atacante: Mutante): void`

---

# Interfaz

### Observer

Define el método de actualización de la vista.

**Metodos**

- `actualizar(): void`

---


### VerCampoBatalla

Implementa la interfaz `Observer`.

- `campoBatalla: CampoBatalla`

**Metodos**

- `VerCampoBatalla(campoBatalla: CampoBatalla)`
- `actualizar(): void`
- `getCampoBatalla(): CampoBatalla`
- `getAncho(): int`
- `getAlto(): int`

---

### BatallaUI

Controla la interacción del usuario con la interfaz.

- `vista: VerCampoBatalla`
- `temporizador: Timer`
- `controlador: ControladorBatalla`
- `botonNuevaBatalla: JButton`
- `batallaFinalizada: boolean`
**Metodos**

- `BatallaUI(campoBatalla: CampoBatalla, controladorBatalla: ControladorBatalla)`
- `actualizar(): void`
- `recibirCantidadEquipo(): int`
- `iniciarBatalla(): void`
- `iniciarBatalla(cantidad: int): void`
- `nuevaBatalla(cantidad: int): void`

---

# Constantes

Valores configurables del juego.

### IConstantes

- `INICIAL_ENERGIA: int`
- `MIN_TAMANO_EQUIPO: int`
- `MAX_TAMANO_EQUIPO: int`
- `MIN_DEFENSA: int`
- `MAX_DEFENSA: int`
- `PROBABILIDAD_DEFENSA: double`
- `MIN_PODER_DANIO: int`
- `MAX_PODER_DANIO: int`
- `DANIO_AUMENTA: int`
- `MAX_DANIO_PODER: int`
- `CANTIDAD_PODERES: int`
- `MIN_EDAD: int`
- `MAX_EDAD: int`
- `MISMA_VELOCIDAD: int`
- `ENCUENTRO_RADIO: double`
- `ANCHO_VENTANA: int`
- `ALTO_VENTANA: int`
- `ALTO_CAMPOBATALLA: int`
- `ANCHO_CAMPOBATALLA: int`
- `VIVOS_EQUIPO1: int`
- `MUERTOS_EQUIPO1: int`
- `VIVOS_EQUIPO2: int`
- `MUERTOS_EQUIPO2: int`
- `ACTUALIZACION_UI: int`
- `ALTO_ESTADISTICAS: int`
- `TAMANO_MUTANTE: int`

---

# Pruebas por capa

El proyecto incluye un `main` para probar cada capa de forma independiente:

- `MainModelo`: prueba los elementos de la capa Modelo.
- `MainGame`: prueba la creación de equipos, mutantes y marcador.
- `MainControl`: prueba el movimiento, los encuentros y el combate.
- `MainUI`: ejecuta el juego completo mediante la interfaz gráfica.
---
# UML

<img width="2095" height="2532" alt="image" src="https://github.com/user-attachments/assets/23755df4-8d72-45fa-a1c6-f59dc5d62470" />


```
@startuml
title Mutant Battle - UML Caso #1

skinparam classAttributeIconSize 0
skinparam packageStyle rectangle

'========================================
' CAPA MODEL
'========================================

package "Model" {

    class Persona {
        # nombre: String
        - edad: byte

        + Persona(nombre: String, edad: byte)
        + getNombre(): String
        + setNombre(nombre: String): void
        + getEdad(): byte
        + setEdad(edad: byte): void
    }

    class Mutante {
        - energia: int
        - defensa: int
        - posicionX: int
        - posicionY: int
        - poder: IPower
        - direccionX: int
        - direccionY: int

        + Mutante(nombre: String, edad: byte, energia: int, defensa: int, posicionX: int, posicionY: int, poder: IPower)
        + getEnergia(): int
        + getDefensa(): int
        + getPosicionX(): int
        + getPosicionY(): int
        + getPoder(): IPower
        + setEnergia(energia: int): void
        + setDefensa(defensa: int): void
        + setPosicionX(posicionX: int): void
        + setPosicionY(posicionY: int): void
        + setPoder(poder: IPower): void
        + estaVivo(): boolean
        + recibirDanio(danio: double): void
        + mover(ancho: int, alto: int): void
        - obtenerDireccion(): int
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
        + getColor(): String
        + getSimbolo(): Image
        + setSimbolo(simbolo: Image): void
        + agregarMutante(mutante: Mutante): void
        + getMutantes(): List<Mutante>
        + contarVivos(): int
        + contarMuertos(): int
        + estaEliminado(): boolean
    }

    class Marcador {
        - estadisticas: int[]

        + Marcador()
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

        + CampoBatalla()
        + crearEquipos(cantidad: int): void
        + getEquipo1(): Equipo
        + getEquipo2(): Equipo
        + getMarcador(): Marcador
        + batallaTerminada(): boolean
        + obtenerGanador(): Equipo
        - crearMutante(nombre: String): Mutante
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
        + iniciarMovimiento(ancho: int, alto: int): void
        + controlarBatalla(): void
        + finalizarJuego(): void
        + nuevaBatalla(): void
        + getCampoBatalla(): CampoBatalla
    }

    class HiloMutante {
        - mutante: Mutante
        - campoBatalla: CampoBatalla
        - administradorCombate: AdministradorCombate
        - activo: boolean
        - ancho: int
        - alto: int

        + HiloMutante(mutante: Mutante, campoBatalla: CampoBatalla, administradorCombate: AdministradorCombate, ancho: int, alto: int)
        + run(): void
        + correr(): void
        + detener(): void
        + detectarEnemigos(): List<Mutante>
    }

    class AdministradorCombate {
        - radioEncuentro: double

        + AdministradorCombate()
        + detectarEncuentro(mutante1: Mutante, mutante2: Mutante): boolean
        + decidirDefensa(): boolean
        + calcularDanio(atacante: Mutante, defensor: Mutante, defiende: boolean): double
        + aplicarDanio(defensor: Mutante, danio: double): void
        + aumentarDanioPoder(atacante: Mutante): void
        + ejecutarEncuentro(mutante1: Mutante, mutante2: Mutante): void
        - atacar(atacante: Mutante, defensor: Mutante, defiende: boolean): void
    }
}

'========================================
' CAPA UI
'========================================

package "UI" {

    interface Observer {
        + actualizar(): void
    }

    class VerCampoBatalla {
        - campoBatalla: CampoBatalla

        + VerCampoBatalla(campoBatalla: CampoBatalla)
        # paintComponent(graphics: Graphics): void
        + actualizar(): void
        + getCampoBatalla(): CampoBatalla
        + getAncho(): int
        + getAlto(): int
        - dibujarEquipo(graphics: Graphics, equipo: Equipo, color: Color): void
        - dibujarBarraSuperior(graphics: Graphics): void
        - dibujarSimbolos(graphics: Graphics): void
        - dibujarGanador(graphics: Graphics): void
    }

    class BatallaUI {
        - vista: VerCampoBatalla
        - temporizador: Timer
        - controlador: ControladorBatalla
        - botonNuevaBatalla: JButton
        - batallaFinalizada: boolean

        + BatallaUI(campoBatalla: CampoBatalla, controladorBatalla: ControladorBatalla)
        + actualizar(): void
        + iniciarBatalla(): void
        + iniciarBatalla(cantidad: int): void
        + nuevaBatalla(cantidad: int): void
        - cambiarVista(): void
        - iniciarNuevaBatalla(): void
        - recibirCantidadEquipo(): int
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
        PROBABILIDAD_DEFENSA: double
        MIN_PODER_DANIO: int
        MAX_PODER_DANIO: int
        DANIO_AUMENTA: int
        MAX_DANIO_PODER: int
        CANTIDAD_PODERES: int
        MIN_EDAD: int
        MAX_EDAD: int
        MISMA_VELOCIDAD: int
        ENCUENTRO_RADIO: double
        ALTO_VENTANA: int
        ANCHO_VENTANA: int
        ALTO_CAMPOBATALLA: int
        ANCHO_CAMPOBATALLA: int
        VIVOS_EQUIPO1: int
        MUERTOS_EQUIPO1: int
        VIVOS_EQUIPO2: int
        MUERTOS_EQUIPO2: int
        ACTUALIZACION_UI: int
        ALTO_ESTADISTICAS: int
        TAMANO_MUTANTE: int
    }
}

'========================================
' HERENCIA Y POLIMORFISMO
'========================================

Persona <|-- Mutante

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

VerCampoBatalla ..|> Observer
VerCampoBatalla --> CampoBatalla
VerCampoBatalla --> Equipo

BatallaUI --> ControladorBatalla
BatallaUI --> VerCampoBatalla
BatallaUI --> CampoBatalla

'========================================
' RELACIONES CONSTANTES
'========================================

Mutante ..> IConstants
PoderHielo ..> IConstants
PoderRayos ..> IConstants
PoderTelepatia ..> IConstants
PoderFuego ..> IConstants
PoderRegeneracion ..> IConstants

CampoBatalla ..> IConstants
Marcador ..> IConstants
AdministradorCombate ..> IConstants
HiloMutante ..> IConstants
BatallaUI ..> IConstants
VerCampoBatalla ..> IConstants
@enduml
```


