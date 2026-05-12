# Ghost Net Fishing 🌊

Web-Anwendung zum Melden und Bergen von Geisternetzen.
Entwickelt im Rahmen der Fallstudie für den Kurs IPWA02-01.

## Technologien
- Spring Boot 3.5
- Spring Data JPA + Hibernate
- MySQL Datenbank
- Thymeleaf Templates
- Leaflet.js (Weltkarte)

## Voraussetzungen
- Java 21
- MySQL Server

## Setup

### 1. Datenbank anlegen
```sql
CREATE DATABASE ghostnetdb;
```

### 2. Datenbankverbindung konfigurieren
In `src/main/resources/application.properties` die Zugangsdaten anpassen:
```properties
spring.datasource.username=root
spring.datasource.password=dein_passwort
```

### 3. Anwendung starten
./mvnw spring-boot:run

### 4. Browser öffnen
http://localhost:8080

Beim ersten Start werden automatisch Beispieldaten geladen (via `data.sql`).

## Funktionen
- Geisternetze anonym melden (MUST 1)
- Für Bergung eintragen (MUST 2)
- Offene Netze anzeigen (MUST 3)
- Netze als geborgen melden (MUST 4)
- Weltkarte mit offenen Netzen (COULD 5)
- Koordinationsübersicht (COULD 6)
- Netze als verschollen melden (COULD 7)