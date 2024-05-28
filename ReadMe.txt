Skak Program til Kunstig intelligens F24.

Guide til at køre programmmet:

Kør Jar fil:
Det er muligt at køre programmet ved at køre jar filen.
Download filen og åben en terminal og skriv: java -jar ChessComputerV1.jar
På windows skal man bruge powershell.
På mac og linux skal man bruge terminalen.

Programmet vil nu starte og bede dig om at vælge om du vil starte spillet fra en FEN streng.
Hvis du vælger at starte fra en FEN streng, vil programmet bede dig om at indtaste en FEN streng.
Hvis det er en FEN streng hvor det er sorts tur, vil computeren spørge om den skal tage det træk eller du selv vil tage det.
Når det træk er taget vil den bede dig vælge en side og så starter spillet fra den position.

Hvis du vælger at starte fra bunden, vil programmet starte et nyt spil.
Den vil bede dig vælge om du vil spille som hvid eller sort.
Hvis du vælger at spille som sort, vil computeren starte spillet.

Når du skal indtaste et træk skal du skrive det i formen: a2, a4
Det første felt er det felt brikken står på og det andet felt er det felt brikken skal flyttes til.

Hvis du på noget tidspunt vil afslutte spillet, kan du skrive "exit" og spillet vil afslutte.
Og hvis du vil have en FEN string fra en position kan du skrive "fen" og programmet vil printe FEN strengen og slutte.

Rokade er ikke muligt at lave. Medmindre at man gider at tage fen strengen og ændre den manuelt og
så starte spillet op igen fra den ændrede fen streng.

En passant vil ikke blive taget i betragtning.

Når der sker en promotion vil du blive bedt om at indtaste hvilken brik du vil promovere til.
Du skal skrive en af de Char som bliver vist i terminalen.



Andre gamemodes:
Det er også muligt at få computeren til at spille mod sig selv.
Dette kan gøres ved at man åbner src mappen og åbner Main.java filen.
I main metoden er der to udkommenterede linjer som starter et spil mellem to computere.
Den ene er computer vs computer med fast dybde.
Den anden er computer med fast dybde vs computer hvor den tænker på tid.


Ekstra ting:
Som standard er computeren sat til at tænke i 10 sekunder.
Dette kan ændres ved at ændre i linje 185 i Board.java filen. Hvis man ændrer 10 tallet til noget andet, vil computeren tænke i det antal sekunder.
