# Friend System
### Einleitung

Das Friend System ist ein System für Bungeecord, das Spielern erlaubt, auf dem Netzwerk befreundet zu sein. 
Freunde können unter sich private Nachrichten versenden und dem gleichen Server nachjoinen.

<details>
<summary>Commands</summary>

- **/friend list** listet alle Freunde auf.

- **/friend add <Spieler>** sendet eine Freundschaftsanfrage an einen Spieler.

- **/friend remove <Spieler>** enfernt einen Spieler aus seiner Freundesliste.

- **/friend requests** listet alle Freundschaftsanfragen auf.

- **/friend accept <Spieler>** akzeptiert eine Freundschaftsanfrage.

- **/friend deny <Spieler>** lehnt eine Freundschaftsanfrage ab.

- **/friend jump <Spieler>** tritt dem Server eines Freundes bei.

- **/friend togglerequests** schaltet das Attribut um, ob man Freundschaftsanfragen erhalten möchte.

- **/friend togglenotify** schaltet das Attribut um, ob man bei Join oder Leave eines Freundes benachrichtigt wird.

- **/friend togglemessages** schaltet das Attribut um, ob man private Nachrichten erhalten möchte.

- **/friend togglejump** schaltet das Attribut um, ob Freunde dem Spieler nachjoinen können.

- **/msg <Player> <Message>** sendet eine private Nachricht an einen Spieler.

</details>

### Installation

Für die Nutzung dieses Plugins ist ein Bungeecord 1.11 Netzwerk erforderlich. 
Man erstellt die Datenbank Struktur mit der Query in `tables.sql`.
Danach kopiert man das Plugin in das Plugin Verzeichnis und startet das Netzwerk.
